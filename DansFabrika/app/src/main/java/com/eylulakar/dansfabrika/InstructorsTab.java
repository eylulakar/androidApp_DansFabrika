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
public class InstructorsTab extends Fragment {
    ListView listInstructors;
    View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        view = inflater.inflate(R.layout.instructor_list_layout, container, false);
        new getList().execute("");


        return view;
    }

    public void bindInstructors(ArrayList dataList)  {
        listInstructors = (ListView) view.findViewById(R.id.lvInstructors);
        InstructorListAdapter adp = new InstructorListAdapter(view.getContext(), dataList);
        listInstructors.setAdapter(adp);

        listInstructors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object selectedItem = listInstructors.getItemAtPosition(position);
                Instructor instructorItem = (Instructor)selectedItem;

                Intent sendInstructor = new Intent(getActivity(), InstructorDetail.class);
                sendInstructor.putExtra("instructorItem", instructorItem);
                startActivity(sendInstructor);
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
            String serviceParams = "GetInstructors";
            String responseText = CommonFunctions.GetServiceResponse(CommonFunctions.ServiceUrl + serviceParams);

            if(responseText!= "")
            {
                try{

                    final Gson gson = new Gson();
                    Type typeList = new TypeToken<List<Instructor>>(){}.getType();
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
                bindInstructors(dataList);
            }
        }
    }


}
