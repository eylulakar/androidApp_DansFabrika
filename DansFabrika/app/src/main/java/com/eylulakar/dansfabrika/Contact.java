package com.eylulakar.dansfabrika;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


/**
 * Created by Eylul on 21.10.2014.
 */
public class Contact  extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_layout);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        setRelativeLayoutListeners();
    }

    public void setRelativeLayoutListeners() {
        ((LinearLayout)findViewById(R.id.llContactCall)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnContactCall = (ImageButton)findViewById(R.id.btnContactCall);
                btnContactCall.performClick();
            }
        });

        ((LinearLayout)findViewById(R.id.llContactMap)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnContactMap = (ImageButton)findViewById(R.id.btnContactMap);
                btnContactMap.performClick();
            }
        });

        ((LinearLayout)findViewById(R.id.llContactMail)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnContactMail = (ImageButton)findViewById(R.id.btnContactMail);
                btnContactMail.performClick();
            }
        });
        ((LinearLayout)findViewById(R.id.llContactWebsite)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnContactWebsite = (ImageButton)findViewById(R.id.btnContactWebsite);
                btnContactWebsite.performClick();
            }
        });
        ((LinearLayout)findViewById(R.id.llContactSendForm)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnContactSendForm = (ImageButton)findViewById(R.id.btnContactSendForm);
                btnContactSendForm.performClick();
            }
        });
    }

    public void  onbtnContactSendFormClick(View view)
    {
        Intent getIntent = new Intent(this, ContactForm.class);
        getIntent.putExtra("callingActivity", "ContactFormActivity");
        startActivity(getIntent);

    }
    public void  onbtnContactWebsiteClick(View view)
    {
        String url = "http://www.dansfabrika.com";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }
    public void  onbtnContactMailClick(View view)
    {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","dansfabrika@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Dans Fabrika | Mesaj");
        startActivity(Intent.createChooser(emailIntent, "Send email"));
    }

    public void  onbtnContactMapClick(View view)
    {
        Intent getIntent = new Intent(this, Map.class);
        getIntent.putExtra("callingActivity", "FindActivity");
        startActivity(getIntent);
    }

    public void  onbtnContactCallClick(View view)
    {
        callNumber();
    }

    public void callNumber() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Dans Fabrika(05369446520)'yı ara?");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Evet",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                        String uri = "tel:" + "05369446520";
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse(uri));
                        startActivity(intent);
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
