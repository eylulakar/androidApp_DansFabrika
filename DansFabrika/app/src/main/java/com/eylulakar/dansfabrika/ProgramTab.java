package com.eylulakar.dansfabrika;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eylul on 17.11.2014.
 */
public class ProgramTab extends Fragment {
    ListView listPrograms;
    View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){


        view = inflater.inflate(R.layout.program_list_layout, container, false);
        new getList().execute("");

        return view;
    }

    public void bindPrograms(ArrayList dataList)  {
        listPrograms = (ListView) view.findViewById(R.id.lvPrograms);
        ProgramListAdapter adp = new ProgramListAdapter(view.getContext(), dataList);
        listPrograms.setAdapter(adp);
    }

    class getList extends AsyncTask<String, String, String> {
        ArrayList dataList;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String serviceParams = "GetPrograms";
            String responseText = CommonFunctions.GetServiceResponse(CommonFunctions.ServiceUrl + serviceParams);

            if(responseText!= "")
            {
                try{
                    final Gson gson = new Gson();
                    Type typeList = new TypeToken<List<Program>>(){}.getType();
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
                bindPrograms(dataList);
            }
        }
    }


}
