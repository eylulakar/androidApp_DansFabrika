package com.eylulakar.dansfabrika;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
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
public class DancersMaleTab extends Fragment {
    ListView listDancers;
    View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){



        view = inflater.inflate(R.layout.dancer_list_layout, container, false);
        new getList().execute("");



        return view;
    }

    public void bindDancers(ArrayList dataList)  {
        listDancers = (ListView) view.findViewById(R.id.lvDancers);
        DancerListAdapter adp = new DancerListAdapter(view.getContext(), dataList);
        listDancers.setAdapter(adp);

        listDancers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object selectedItem = listDancers.getItemAtPosition(position);
                Dancer dancerItem = (Dancer)selectedItem;


                Intent sendDancer = new Intent(getActivity(), DancerDetail.class);
                sendDancer.putExtra("dancerItem", dancerItem);
                startActivity(sendDancer);
            }

        });

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
            String serviceParams = "GetDancers?Gender=1";
            String responseText = CommonFunctions.GetServiceResponse(CommonFunctions.ServiceUrl + serviceParams);

            if(responseText!= "")
            {
                try{

                    final Gson gson = new Gson();
                    Type typeList = new TypeToken<List<Dancer>>(){}.getType();
                    dataList = gson.fromJson(responseText, typeList);

                }
                catch (Exception exception)   {
                    //exception.toString();
                }
            }
            return null;
        }

        protected void onProgressUpdate(String... progress) {
            // setRefreshActionButtonState(true);
        }

        @Override
        protected void onPostExecute(String resultParam) {
            // setRefreshActionButtonState(false);
            if(dataList!= null)
            {
                bindDancers(dataList);
            }
        }
    }


}
