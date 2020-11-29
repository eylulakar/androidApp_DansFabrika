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
 * Created by Eylul on 15.10.2014.
 */
public class InstructorDetail extends Activity {

    ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_detail_layout);

        ab = getActionBar();
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent activityThatCalled = getIntent();
        Instructor instructorItem = (Instructor) activityThatCalled.getSerializableExtra("instructorItem");

        setInstructorDetails(instructorItem);
    }

    private  void setInstructorDetails(Instructor instructorItem){
        TextView txtInstructorDetailTitle = (TextView) findViewById(R.id.txtInstructorDetailTitle);
        txtInstructorDetailTitle.setText(instructorItem.getFirstName() + " " + instructorItem.getLastName());
        ab.setTitle(instructorItem.getFirstName() + " " + instructorItem.getLastName());

        TextView txtInstructorDetailBranch = (TextView) findViewById(R.id.txtInstructorDetailBranch);
        txtInstructorDetailBranch.setText(instructorItem.getBranch());

        TextView txtInstructorDetailDescription = (TextView) findViewById(R.id.txtInstructorDetailDescription);
        txtInstructorDetailDescription.setText(instructorItem.getDescription());

        ImageView imgDetail = (ImageView) findViewById(R.id.imgInstructorDetailImage);
        Picasso.with(this).load(instructorItem.getImageUrl()).into(imgDetail);

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
