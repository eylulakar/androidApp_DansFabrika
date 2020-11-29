package com.eylulakar.dansfabrika;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eylul on 05.11.2014.
 */
public class LocationPhotoDescription extends Activity {
    private Menu optionsMenu;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLoadingLayout();

        getActionBar().setDisplayHomeAsUpEnabled(true);

        getContent();
    }
    public void setLoadingLayout() {
        setContentView(R.layout.loading_layout);
    }

    public void getContent() {
        setContentView(R.layout.location_photo_description_layout);

        if (CommonFunctions.isNetworkAvailable(this) == false)
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Lütfen internet bağlantınızın açık olduğundan emin olunuz.");
            builder1.setCancelable(true);
            builder1.setPositiveButton("Tekrar Dene",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            getContent();
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
            new getDescriptionTextLong().execute("");
        }
    }

    class getDescriptionTextLong extends AsyncTask<String, String, String> {
        String responseText = "";
        ArrayList dataList;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setLoadingLayout();
        }

        @Override
        protected String doInBackground(String... strings) {
            String serviceParams = "GetText";
            responseText = CommonFunctions.GetServiceResponseAsSingleItem("LocationPhotoDescriptionLong");

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
            setRefreshActionButtonState(true);
        }

        @Override
        protected void onPostExecute(String resultParam) {
            if (dataList != null)
            {
                setContentView(R.layout.location_photo_description_layout);
                SingleItem item = (SingleItem)dataList.get(0);
                TextView txtLocationPhotoDescriptionLong = (TextView)findViewById(R.id.txtLocationPhotoDescriptionLong);
                txtLocationPhotoDescriptionLong.setText(item.getTextValue().toString());
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
