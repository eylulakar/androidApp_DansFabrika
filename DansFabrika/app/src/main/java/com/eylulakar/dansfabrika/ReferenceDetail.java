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
public class ReferenceDetail extends Activity {
    ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.reference_detail_layout);

        ab = getActionBar();
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent activityThatCalled = getIntent();
        Reference ReferenceItem = (Reference) activityThatCalled.getSerializableExtra("ReferenceItem");

        setReferenceDetails(ReferenceItem);
    }

    private  void setReferenceDetails(Reference ReferenceItem){
        TextView txtTitle = (TextView) findViewById(R.id.txtReferenceDetailTitle);
        txtTitle.setText(ReferenceItem.getTitle());
        ab.setTitle(ReferenceItem.getTitle());

        ImageView imgDetail = (ImageView) findViewById(R.id.imgReferenceDetailImage);
        Picasso.with(this).load(ReferenceItem.getImageUrl()).into(imgDetail);
       // imgDetail.setImageResource(ReferenceItem.getDrawableId());
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
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
