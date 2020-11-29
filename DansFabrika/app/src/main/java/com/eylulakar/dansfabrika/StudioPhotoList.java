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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Eylul on 20.10.2014.
 */
public class StudioPhotoList extends Activity {
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
        setContentView(R.layout.studio_photo_list_layout);
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

    public void bindStudioPhotos(ArrayList dataList) {
        setContentView(R.layout.studio_photo_list_layout);

        final StudioPhotoListAdapter adp = new StudioPhotoListAdapter(this, dataList);

        final GridView gvStudioPhotos = (GridView)findViewById(R.id.gvStudioPhotos);
        gvStudioPhotos.setAdapter(adp);
        gvStudioPhotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                popupView = null;
                if(popupWindow != null)
                {
                    popupWindow.dismiss();
                    popupWindow = null;
                }

                //SquareImageView img = (SquareImageView)v.findViewById(R.id.picture);
                Object selectedItem = gvStudioPhotos.getItemAtPosition(position);
                StudioPhoto studioPhotoItem = (StudioPhoto)selectedItem;

                LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                popupView = layoutInflater.inflate(R.layout.studio_photo_detail, null);
                popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //popupWindow.setAnimationStyle(R.style.PopupAnimation);
                popupWindow.showAtLocation(parent, Gravity.CENTER_HORIZONTAL, 0, 50);

                ImageView imgStudioPhotoDetail = (ImageView) popupView.findViewById(R.id.imgStudioPhotoDetail);
                Picasso.with(popupView.getContext()).load(studioPhotoItem.getImageUrl()).into(imgStudioPhotoDetail);
            }
        });
    }


    public void  onbtnStudioPhotoDetailClose(View view)
    {
        popupView = null;
        popupWindow.dismiss();
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
            String serviceParams = "GetStudioPhotos";
            String responseText = CommonFunctions.GetServiceResponse(CommonFunctions.ServiceUrl + serviceParams);

            if(responseText!= "")
            {
                try{

                    final Gson gson = new Gson();
                    Type typeList = new TypeToken<List<StudioPhoto>>(){}.getType();
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
                bindStudioPhotos(dataList);
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
