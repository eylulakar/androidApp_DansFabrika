package com.eylulakar.dansfabrika;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import com.eylulakar.dansfabrika.R;

/**
 * Created by Eylul on 20.10.2014.
 */
public class ArtistDetail extends Activity {
    ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.artist_detail_layout);

        ab = getActionBar();
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent activityThatCalled = getIntent();
        Artist ArtistItem = (Artist) activityThatCalled.getSerializableExtra("ArtistItem");

        setArtistDetails(ArtistItem);
    }

    private  void setArtistDetails(Artist ArtistItem){
        TextView txtTitle = (TextView) findViewById(R.id.txtArtistDetailTitle);
        txtTitle.setText(ArtistItem.getTitle());
        ab.setTitle(ArtistItem.getTitle());

        ImageView imgDetail = (ImageView) findViewById(R.id.imgArtistDetailImage);
        Picasso.with(this).load(ArtistItem.getImageUrl()).into(imgDetail);
       // imgDetail.setImageResource(ArtistItem.getDrawableId());
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                this.finish();
                return true;
            case R.id.action_settings:

                return true;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //Toast.makeText(MyActivity.this, "aaa", Toast.LENGTH_SHORT).show();
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
