package com.eylulakar.dansfabrika;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import com.eylulakar.dansfabrika.R;

/**
 * Created by Eylul on 15.10.2014.
 */
public class NewsDetail extends Activity {
    private Menu optionsMenu;
    ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail_layout);

        ab = getActionBar();
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent activityThatCalled = getIntent();
        News newsItem = (News) activityThatCalled.getSerializableExtra("newsItem");

        setNewsDetails(newsItem);
    }

    private  void setNewsDetails(News newsItem){

        TextView txtTitle = (TextView) findViewById(R.id.txtNewsDetailTitle);
        txtTitle.setText(newsItem.getTitle());
        ab.setTitle(newsItem.getTitle());

        TextView txtDescription = (TextView) findViewById(R.id.txtNewsDetailDescription);
        txtDescription.setText(newsItem.getDescription());

        ImageView imgDetail = (ImageView) findViewById(R.id.imgNewsDetailImage);
        Picasso.with(this).load(newsItem.getImageUrl()).into(imgDetail);
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
