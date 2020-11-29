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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import com.eylulakar.dansfabrika.R;

/**
 * Created by Eylul on 15.10.2014.
 */
public class DancerDetail extends Activity {
    ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dancer_detail_layout);

        ab = getActionBar();
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent activityThatCalled = getIntent();
        Dancer dancerItem = (Dancer) activityThatCalled.getSerializableExtra("dancerItem");


        setDancerDetails(dancerItem);
    }

    private  void setDancerDetails(Dancer dancerItem){
        TextView txtTitle = (TextView) findViewById(R.id.txtDancerDetailTitle);
        txtTitle.setText(dancerItem.getFirstName() + " " + dancerItem.getLastName());
        ab.setTitle(dancerItem.getFirstName() + " " + dancerItem.getLastName());

        TextView txtType = (TextView) findViewById(R.id.txtDancerDetailType);
        txtType.setText(dancerItem.getType());

        TextView txtBranch = (TextView) findViewById(R.id.txtDancerDetailBranch);
        txtBranch.setText(dancerItem.getBranch());

        ImageView imgDetail = (ImageView) findViewById(R.id.imgDancerDetailImage);
        Picasso.with(this).load(dancerItem.getImageUrl()).into(imgDetail);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return true;
    }



}
