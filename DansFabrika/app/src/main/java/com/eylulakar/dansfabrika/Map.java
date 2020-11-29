package com.eylulakar.dansfabrika;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Eylul on 06.11.2014.
 */
public class Map extends Activity {
    private Menu optionsMenu;
    ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);

        ab = getActionBar();
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
            bindMap();
        }
    }

    public void bindMap()  {


         final LatLng locationDansFabrika = new LatLng(41.097351, 29.005635);
         GoogleMap mMap;
         mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
         Marker markerDansFabrika = mMap.addMarker(new MarkerOptions()
                 .position(locationDansFabrika)
                 .title("Menderes Cd No:9, Huzur Mh., 34398 4. Levent")
                 .snippet("Şişli/İstanbul, Türkiye"));
         markerDansFabrika.showInfoWindow();
         mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(locationDansFabrika, 12.0f) );
    }





    public void onBackPressed() {
        super.onBackPressed();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
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



}
