package com.eylulakar.dansfabrika;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Eylul on 30.10.2014.
 */
public class CommonFunctions {

    public static final String DomainString = "test.eylulakar.com";
   // public static final String ServiceUrl = "http://test.eylulakar.com/service.asmx/";
   public static final String ServiceUrl = "http://swe599api.eylulakar.com/service.asmx/";

    public static String GetServiceResponse(String Url) {
        String responseText = "";

        HttpClient hc = new DefaultHttpClient();
        HttpGet get = new HttpGet(Url);

        try {

            HttpResponse rp = hc.execute(get);
            HttpEntity entity = rp.getEntity();

            InputStream inputStream = entity.getContent();

            responseText = IOUtils.toString(inputStream, "UTF-8");
            inputStream.close();
        }
        catch (Exception exception)   {
            responseText = "serviceerror:" + exception.toString();
        }

        Log.i(".callServiceClient()", "responsecode: " + responseText);

        return responseText;
    }

    public static String callServiceClient(String urlString) {

        String json = null;

        HttpParams httpParams = new BasicHttpParams();
        int connection_Timeout = 5000;
        HttpConnectionParams.setConnectionTimeout(httpParams, connection_Timeout);
        HttpConnectionParams.setSoTimeout(httpParams, connection_Timeout);

        DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
        //httpClient.getCredentialsProvider().setCredentials(new AuthScope(null, -1),
                //new UsernamePasswordCredentials(user, password));

        HttpGet httpget = new HttpGet(urlString);

        // Execute the request
        HttpResponse response;
        try {
            response = httpClient.execute(httpget);
            // Examine the response status
            StatusLine responseCode = response.getStatusLine();
            Log.i(".callServiceClient()", "responsecode: " + responseCode);
            if (responseCode.getStatusCode() != HttpStatus.SC_OK) {
                return json;
            }

            // Get hold of the response entity
            HttpEntity entity = response.getEntity();
            // If the response does not enclose an entity, there is no need
            // to worry about connection release

            if (entity != null) {

                // A Simple JSON Response Read
                InputStream instream = entity.getContent();
                json = IOUtils.toString(instream, "UTF-8");
                instream.close();

            }
        } catch (ClientProtocolException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static String GetServiceResponseAsSingleItem(String KeyValue) {
        String responseText = "";

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority(DomainString)
                .appendPath("service.asmx")
                .appendPath("GetText")
                .appendQueryParameter("KeyValue", KeyValue);
        String Url = builder.build().toString();


        HttpClient hc = new DefaultHttpClient();
        HttpGet get = new HttpGet(Url);

        try {
            HttpResponse rp = hc.execute(get);
            HttpEntity entity = rp.getEntity();

            InputStream inputStream = entity.getContent();

            responseText = IOUtils.toString(inputStream, "UTF-8");
            inputStream.close();

        }
        catch (Exception exception)   {
            responseText = "serviceerror:" + Url;
        }

        return responseText;
    }


    public static String SendContactForm(String FullName, String Email, String Message) {
        String responseText = "";

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority(DomainString)
                .appendPath("service.asmx")
                .appendPath("SendContactForm")
                .appendQueryParameter("FullName", FullName)
                .appendQueryParameter("Email", Email)
                .appendQueryParameter("Message", Message);
        String Url = builder.build().toString();


        HttpClient hc = new DefaultHttpClient();
        HttpGet get = new HttpGet(Url);

        try {
            HttpResponse rp = hc.execute(get);
            HttpEntity entity = rp.getEntity();

            InputStream inputStream = entity.getContent();

            responseText = IOUtils.toString(inputStream, "UTF-8");
            inputStream.close();

        }
        catch (Exception exception)   {
            responseText = "serviceerror:" + Url;
        }

        return responseText;
    }


    public static boolean isNetworkAvailable(Context context)
    {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }


}
