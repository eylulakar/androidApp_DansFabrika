package com.eylulakar.dansfabrika;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Eylul on 20.10.2014.
 */
public class LocationPhotoList extends Activity {
    ListView listLocationPhotos;
    private Menu optionsMenu;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_photo_list_layout);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        getContent();

    }
    public void getContent() {

        if (CommonFunctions.isNetworkAvailable(this) == false)
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Lütfen internet bağlantınızın açık olduğundan emin olunuz.");
            builder1.setCancelable(true);
            builder1.setPositiveButton("Tekrar Dene",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            new getDescriptionText().execute("");
                            new getList().execute("");
                        }
                    });
            builder1.setNegativeButton("İptal",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        else
        {
            new getDescriptionText().execute("");
            new getList().execute("");
        }
    }
    public void bindLocationPhotos(ArrayList dataList) {
        listLocationPhotos = (ListView) findViewById(R.id.lvLocationPhotos);
        LocationPhotoListAdapter adp = new LocationPhotoListAdapter(this, dataList);
        listLocationPhotos.setAdapter(adp);
    }


    class getList extends AsyncTask<String, String, String> {
        ArrayList dataList;
        String responseText = "";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setRefreshActionButtonState(true);
        }

        @Override
        protected String doInBackground(String... strings) {
            String serviceParams = "GetLocationPhotos";
            responseText = CommonFunctions.GetServiceResponse(CommonFunctions.ServiceUrl + serviceParams);

            if(responseText!= "")
            {

               try{

                  final Gson gson = new Gson();
                  Type typeList = new TypeToken<List<LocationPhoto>>(){}.getType();
                  dataList = gson.fromJson(responseText, typeList);

              }
              catch (Exception exception)   {
                //  responseText = "gson covnert hata:" +  exception.toString();
              }
            }
            return null;
        }

        protected void onProgressUpdate(String... progress) {
            setRefreshActionButtonState(true);
        }

        @Override
        protected void onPostExecute(String resultParam) {
             setRefreshActionButtonState(false);
             if(dataList!= null)
             {
                bindLocationPhotos(dataList);
             }
        }
    }

    class getDescriptionText extends AsyncTask<String, String, String> {
        String responseText = "";
        ArrayList dataList;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Shows Progress Bar Dialog and then call doInBackground method
        }

        @Override
        protected String doInBackground(String... strings) {
            String serviceParams = "GetText";
            responseText = CommonFunctions.GetServiceResponseAsSingleItem("LocationPhotoDescriptionShort");

            if(responseText!= "")
            {
                try{

                    final Gson gson = new Gson();
                    Type typeList = new TypeToken<List<SingleItem>>(){}.getType();
                    dataList = gson.fromJson(responseText, typeList);

                }
                catch (Exception exception)   {
                    //  responseText = "gson covnert hata:" +  exception.toString();
                }
            }
            return null;
        }

        protected void onProgressUpdate(String... progress) {
        }

        @Override
        protected void onPostExecute(String resultParam) {
            if (dataList != null)
            {
               SingleItem item = (SingleItem)dataList.get(0);
               final TextView txtLocationPhotoDescription = (TextView)findViewById(R.id.txtLocationPhotoDescription);
               String myString = item.getTextValue().toString();

               int i1 = myString.indexOf("[");
               int i2 = myString.indexOf("]")+1;

               SpannableString ss = new SpannableString(myString);
               ClickableSpan clickableSpan = new ClickableSpan() {
                   @Override
                   public void onClick(View textView) {
                       Intent getIntent = new Intent(LocationPhotoList.this, LocationPhotoDescription.class);
                       getIntent.putExtra("callingActivity", "LocationPhotoDescriptionActivity");
                       startActivity(getIntent);
                   }
               };


               ss.setSpan(clickableSpan, i1, i2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

               txtLocationPhotoDescription.setText(ss);
               txtLocationPhotoDescription.setMovementMethod(LinkMovementMethod.getInstance());


            }

        }
    }


    public void onBackPressed() {
        super.onBackPressed();
        //this.finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                this.finish();
                return true;
            case R.id.action_refresh:
                getContent();
                return true;
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.optionsMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void setRefreshActionButtonState(final boolean refreshing) {
        if (optionsMenu != null) {
            final MenuItem refreshItem = optionsMenu
                    .findItem(R.id.action_refresh);
            if (refreshItem != null) {
                if (refreshing) {
                    refreshItem.setActionView(R.layout.actionbar_indeterminate_progress);
                } else {
                    refreshItem.setActionView(null);
                }
            }
        }
    }


}
