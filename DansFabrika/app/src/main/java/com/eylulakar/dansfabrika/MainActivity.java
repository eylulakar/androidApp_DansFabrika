package com.eylulakar.dansfabrika;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        if (findViewById(android.R.id.home) != null)
        { findViewById(android.R.id.home).setVisibility(View.GONE); }

        LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.action_bar_main_layout, null);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
        ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER );

        getActionBar().setDisplayShowCustomEnabled(true);
        getActionBar().setCustomView(view, params);



        setRelativeLayoutListeners();
        setLayoutVisibilities();
    }

    public void setRelativeLayoutListeners()
    {

        ((RelativeLayout)findViewById(R.id.rlMainNews)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnNewsList = (ImageButton)findViewById(R.id.btnNewsList);
                btnNewsList.performClick();
            }
        });
        ((RelativeLayout)findViewById(R.id.rlMainFind)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnFind = (ImageButton)findViewById(R.id.btnFind);
                btnFind.performClick();
            }
        });
        ((RelativeLayout)findViewById(R.id.rlMainContact)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnContact = (ImageButton)findViewById(R.id.btnContact);
                btnContact.performClick();
            }
        });

        ((RelativeLayout)findViewById(R.id.rlMainVideos)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnVideosList = (ImageButton)findViewById(R.id.btnVideosList);
                btnVideosList.performClick();
            }
        });
        ((RelativeLayout)findViewById(R.id.rlMainStudioPhotos)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnStudioPhotoList = (ImageButton)findViewById(R.id.btnStudioPhotoList);
                btnStudioPhotoList.performClick();
            }
        });

        ((RelativeLayout)findViewById(R.id.rlMainCall)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnCall = (ImageButton)findViewById(R.id.btnCall);
                btnCall.performClick();
            }
        });
        ((RelativeLayout)findViewById(R.id.rlMainContactForm)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnContactForm = (ImageButton)findViewById(R.id.btnContactForm);
                btnContactForm.performClick();
            }
        });
        ((RelativeLayout)findViewById(R.id.rlMainProducts)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnProductsList = (ImageButton)findViewById(R.id.btnProductsList);
                btnProductsList.performClick();
            }
        });
        ((RelativeLayout)findViewById(R.id.rlMainStudioVideos)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnStudioVideosList = (ImageButton)findViewById(R.id.btnStudioVideosList);
                btnStudioVideosList.performClick();
            }
        });
        ((RelativeLayout)findViewById(R.id.rlMainArtistList)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnArtistList = (ImageButton)findViewById(R.id.btnArtistList);
                btnArtistList.performClick();
            }
        });
        ((RelativeLayout)findViewById(R.id.rlMaiReferenceList)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnReferenceList = (ImageButton)findViewById(R.id.btnReferenceList);
                btnReferenceList.performClick();
            }
        });
        ((RelativeLayout)findViewById(R.id.rlMainLocationPhotos)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnLocationPhotoList = (ImageButton)findViewById(R.id.btnLocationPhotoList);
                btnLocationPhotoList.performClick();
            }
        });
        ((RelativeLayout)findViewById(R.id.rlMainDancers)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnDancersList = (ImageButton)findViewById(R.id.btnDancersList);
                btnDancersList.performClick();
            }
        });
        ((RelativeLayout)findViewById(R.id.rlCastReferenceList)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnCastReferenceList = (ImageButton)findViewById(R.id.btnCastReferenceList);
                btnCastReferenceList.performClick();
            }
        });
        ((RelativeLayout)findViewById(R.id.rlMainShare)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnShare = (ImageButton)findViewById(R.id.btnShare);
                btnShare.performClick();
            }
        });

        ((RelativeLayout)findViewById(R.id.rlMainAbout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnAbout = (ImageButton)findViewById(R.id.btnAbout);
                btnAbout.performClick();
            }
        });
    }

    public  void setLayoutVisibilities(){
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

        //float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        if(dpWidth < 360)
        {
            RelativeLayout rlMainContactForm=(RelativeLayout)findViewById(R.id.rlMainContactForm);
            rlMainContactForm.setVisibility(View.INVISIBLE);
        }
    }


    public void  onbtnFacebookClick(View view)
    {
        Intent openIntent;
        try {
             getPackageManager().getPackageInfo("com.facebook.katana", 0);
             openIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/787407697955021"));
         } catch (Exception e) {
            openIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/dansfabrika"));
         }

        startActivity(openIntent);
    }
    public void  onbtnInstagramClick(View view)
    {
        Uri uri = Uri.parse("http://instagram.com/dansfabrika");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void  onbtnYoutubeClick(View view)
    {
        Uri uri = Uri.parse("http://www.youtube.com/user/dansfabrika");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void  onbtnTwitterClick(View view)
    {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + "dansfabrika")));
        }catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/" + "dansfabrika")));
        }
    }

    public void  onbtnDancersListClick(View view)
    {
        Intent getStaffIntent = new Intent(this, Staff.class);
        getStaffIntent.putExtra("callingActivity", "StaffActivity");
        startActivity(getStaffIntent);
    }

    public void  onbtnLocationPhotoListClick(View view)
    {
        Intent getIntent = new Intent(this, LocationAndProgram.class);
        getIntent.putExtra("callingActivity", "LocationAndProgramActivity");
        startActivity(getIntent);
    }
    public void  onbtnStudioPhotoListClick(View view)
    {
        Intent getIntent = new Intent(this, StudioPhotoList.class);
        getIntent.putExtra("callingActivity", "StudioPhotoListActivity");
        startActivity(getIntent);
    }

    public void  onbtnCastReferenceListClick(View view)
    {
        Intent getIntent = new Intent(this, CastReferenceList.class);
        getIntent.putExtra("callingActivity", "CastReferenceListActivity");
        startActivity(getIntent);
    }

    public void  onbtnArtistListClick(View view)
    {
        Intent getIntent = new Intent(this, ArtistList.class);
        getIntent.putExtra("callingActivity", "ArtistListActivity");
        startActivity(getIntent);
    }

    public void  onbtnReferenceListClick(View view)
    {
        Intent getIntent = new Intent(this, ReferenceList.class);
        getIntent.putExtra("callingActivity", "ReferenceListActivity");
        startActivity(getIntent);
    }

    public void  onbtnStudioVideosListClick(View view)
    {
        Intent getIntent = new Intent(this, StudioVideoList.class);
        getIntent.putExtra("callingActivity", "StudioVideoListActivity");
        startActivity(getIntent);
    }
    public void  onbtnVideosListClick(View view)
    {
        Intent getIntent = new Intent(this, VideoList.class);
        getIntent.putExtra("callingActivity", "VideoListActivity");
        startActivity(getIntent);
    }

    public void  onbtnContactFormClick(View view)
    {
        Intent getIntent = new Intent(this, ContactForm.class);
        getIntent.putExtra("callingActivity", "ContactFormActivity");
        startActivity(getIntent);
    }
    public void  onbtnFindClick(View view)
    {
        Intent getIntent = new Intent(this, Map.class);
        getIntent.putExtra("callingActivity", "FindActivity");
        startActivity(getIntent);
    }
    public void  onbtnContactClick(View view)
    {
       Intent getIntent = new Intent(this, Contact.class);
       getIntent.putExtra("callingActivity", "ContactActivity");
       startActivity(getIntent);
    }

    public void  onbtnNewsListClick(View view)
    {
        Intent getIntent = new Intent(this, NewsList.class);
        getIntent.putExtra("callingActivity", "NewsListActivity");
        startActivity(getIntent);
    }

    public void  onbtnCallClick(View view)
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

    public void  onbtnShareClick(View view)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.eylulakar.dansfabrika");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Dans Fabrika Android Uygulaması");
        startActivity(Intent.createChooser(intent, "Share"));
    }
    public void  onbtnAboutClick(View view)
    {
        Intent getIntent = new Intent(this, About.class);
        getIntent.putExtra("callingActivity", "AboutActivity");
        startActivity(getIntent);
    }

    public void  onbtnProductsListClick(View view)
    {
        Intent getIntent = new Intent(this, ProductList.class);
        getIntent.putExtra("callingActivity", "ProductListActivity");
        startActivity(getIntent);
    }

}
