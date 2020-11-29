package com.eylulakar.dansfabrika;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eylul on 17.11.2014.
 */
public class LocationTab extends Fragment {
    ListView listLocationPhotos;
    View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        view = inflater.inflate(R.layout.location_photo_list_layout, container, false);
        new getDescriptionText().execute("");
        new getList().execute("");


        return view;
    }

    public void bindLocationPhotos(ArrayList dataList) {
        listLocationPhotos = (ListView) view.findViewById(R.id.lvLocationPhotos);
        LocationPhotoListAdapter adp = new LocationPhotoListAdapter(view.getContext(), dataList);
        listLocationPhotos.setAdapter(adp);
    }

    class getList extends AsyncTask<String, String, String> {
        ArrayList dataList;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Shows Progress Bar Dialog and then call doInBackground method
            // setRefreshActionButtonState(true);
        }

        @Override
        protected String doInBackground(String... strings) {
            String serviceParams = "GetLocationPhotos";
            String responseText = CommonFunctions.GetServiceResponse(CommonFunctions.ServiceUrl + serviceParams);

            if(responseText!= "")
            {

                try{

                    final Gson gson = new Gson();
                    Type typeList = new TypeToken<List<LocationPhoto>>(){}.getType();
                    dataList = gson.fromJson(responseText, typeList);

                }
                catch (Exception exception)   {
                    //  responseText = "gson covnert hata:" +  exception.toString();
                }
            }
            return null;
        }

        protected void onProgressUpdate(String... progress) {
            // setRefreshActionButtonState(true);
        }

        @Override
        protected void onPostExecute(String resultParam) {
            if(dataList!= null)
            {
                bindLocationPhotos(dataList);
            }
        }
    }


    class getDescriptionText extends AsyncTask<String, String, String> {
        String responseText = "";
        ArrayList dataList;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Shows Progress Bar Dialog and then call doInBackground method
        }

        @Override
        protected String doInBackground(String... strings) {
            String serviceParams = "GetText";
            responseText = CommonFunctions.GetServiceResponseAsSingleItem("LocationPhotoDescriptionShort");

            if(responseText!= "")
            {
                try{

                    final Gson gson = new Gson();
                    Type typeList = new TypeToken<List<SingleItem>>(){}.getType();
                    dataList = gson.fromJson(responseText, typeList);

                }
                catch (Exception exception)   {
                    //  responseText = "gson covnert hata:" +  exception.toString();
                }
            }
            return null;
        }

        protected void onProgressUpdate(String... progress) {
        }

        @Override
        protected void onPostExecute(String resultParam) {
            if (dataList != null)
            {
                SingleItem item = (SingleItem)dataList.get(0);
                final TextView txtLocationPhotoDescription = (TextView) view.findViewById(R.id.txtLocationPhotoDescription);
                String myString = item.getTextValue().toString();

                int i1 = myString.indexOf("[");
                int i2 = myString.indexOf("]")+1;

                SpannableString ss = new SpannableString(myString);
                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View textView) {
                        Intent getIntent = new Intent(view.getContext(), LocationPhotoDescription.class);
                        getIntent.putExtra("callingActivity", "LocationPhotoDescriptionActivity");
                        startActivity(getIntent);
                    }
                };


                ss.setSpan(clickableSpan, i1, i2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                txtLocationPhotoDescription.setText(ss);
                txtLocationPhotoDescription.setMovementMethod(LinkMovementMethod.getInstance());


            }

        }
    }




}
