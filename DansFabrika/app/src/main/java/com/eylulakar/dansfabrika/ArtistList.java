package com.eylulakar.dansfabrika;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
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
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eylul on 20.10.2014.
 */
public class ArtistList extends Activity {
    View popupView;
    PopupWindow popupWindow;
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
        setContentView(R.layout.artist_list_layout);
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
            new getList().execute("");
        }
    }
    public void  onbtnArtistDetailClose(View view)
    {
        popupView = null;
        popupWindow.dismiss();
    }

    public void bindArtists(ArrayList dataList) {
            setContentView(R.layout.artist_list_layout);

            final ArtistListAdapter adp = new ArtistListAdapter(this, dataList);

            final GridView gvArtists = (GridView)findViewById(R.id.gvArtists);
            gvArtists.setAdapter(adp);

            gvArtists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    popupView = null;
                    if(popupWindow != null)
                    {  popupWindow.dismiss();
                        popupWindow = null;
                    }

                    //Intent intentSendArtist = new Intent(ArtistList.this, ArtistDetail.class);
                    //intentSendArtist.putExtra("ArtistItem", ArtistItem);
                    //startActivity(intentSendArtist);

                    Object selectedItem = gvArtists.getItemAtPosition(position);
                    Artist ArtistItem = (Artist)selectedItem;

                    LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    popupView = layoutInflater.inflate(R.layout.artist_detail_layout, null);
                    popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    popupWindow.showAtLocation(parent, Gravity.CENTER_HORIZONTAL, 0, 50);

                    TextView txtArtistDetailTitle = (TextView) popupView.findViewById(R.id.txtArtistDetailTitle);
                    txtArtistDetailTitle.setText(ArtistItem.getTitle());
                    ImageView Artist = (ImageView) popupView.findViewById(R.id.imgArtistDetailImage);
                    Picasso.with(popupView.getContext()).load(ArtistItem.getImageUrl()).into(Artist);
                }
            });

    }

    class getList extends AsyncTask<String, String, String> {
        ArrayList dataList;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setLoadingLayout();
            setRefreshActionButtonState(true);
        }

        @Override
        protected String doInBackground(String... strings) {
            String serviceParams = "GetArtists";
            String responseText = CommonFunctions.GetServiceResponse(CommonFunctions.ServiceUrl + serviceParams);

            if(responseText!= "")
            {
                try{

                    final Gson gson = new Gson();
                    Type typeList = new TypeToken<List<Artist>>(){}.getType();
                    dataList = gson.fromJson(responseText, typeList);

                }
                catch (Exception exception)   {
                    //exception.toString();
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
                bindArtists(dataList);
            }
        }
    }


    public void onBackPressed() {
        if (popupView != null)
        {
            popupView = null;
            popupWindow.dismiss();
        }

        super.onBackPressed();
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
