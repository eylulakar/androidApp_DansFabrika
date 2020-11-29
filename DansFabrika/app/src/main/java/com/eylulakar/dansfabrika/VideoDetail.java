package com.eylulakar.dansfabrika;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * Created by Eylul on 15.10.2014.
 */
public class VideoDetail extends Activity {
    ActionBar ab;
    String Url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_detail_layout);

        ab = getActionBar();
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent activityThatCalled = getIntent();
        Video VideoItem = (Video) activityThatCalled.getSerializableExtra("VideoItem");

        setVideoDetails(VideoItem);
    }

    private  void setVideoDetails(Video VideoItem){
        ImageView imgDetail = (ImageView) findViewById(R.id.imgVideoDetailImage);
        Picasso.with(this).load(VideoItem.getImageUrl()).into(imgDetail);

        TextView txtTitle = (TextView) findViewById(R.id.txtVideoDetailTitle);
        txtTitle.setText(VideoItem.getTitle());
        ab.setTitle(VideoItem.getTitle());

        TextView txtOther = (TextView) findViewById(R.id.txtVideoDetailOther);
        txtOther.setText(VideoItem.getOther());

        Url = VideoItem.getUrl();
    }



    public void onimgVideoDetailImageClick(View view) {
       // Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + Url));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + Url));

        startActivity(intent);
    }

    private Menu optionsMenu;
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
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.optionsMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main2, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
