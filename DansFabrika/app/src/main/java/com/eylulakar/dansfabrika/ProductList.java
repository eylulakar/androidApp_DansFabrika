package com.eylulakar.dansfabrika;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Eylul on 20.10.2014.
 */
public class ProductList extends Activity {
    View popupView;
    PopupWindow popupWindow;
    private Menu optionsMenu;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_layout);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        ((RelativeLayout)findViewById(R.id.rlProductListCall)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnProductDescriptionCall = (ImageButton)findViewById(R.id.btnProductDescriptionCall);
                btnProductDescriptionCall.performClick();
            }
        });

        getContent();
    }


    public void getContent() {
        if (CommonFunctions.isNetworkAvailable(this) == false)
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Lütfen internet bağlantınızın açık olduğundan emin olunuz.");
            builder1.setCancelable(true);
            builder1.setPositiveButton("Tekrar Dene",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            getContent();
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
        else
        {
            new getDescriptionText().execute("");
            new getList().execute("");
        }
    }
    public void  onbtnProductDescriptionCallClick(View view)
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

    public void bindProducts(ArrayList dataList) {

        final ProductListAdapter adp = new ProductListAdapter(this, dataList);

        final GridView gvProducts = (GridView)findViewById(R.id.gvProducts);
        gvProducts.setAdapter(adp);
        gvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                popupView = null;
                if(popupWindow != null)
                {
                    popupWindow.dismiss();
                    popupWindow = null;
                }

                //SquareImageView img = (SquareImageView)v.findViewById(R.id.picture);
                Object selectedItem = gvProducts.getItemAtPosition(position);
                Product productItem = (Product)selectedItem;

                LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                popupView = layoutInflater.inflate(R.layout.product_detail, null);
                popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(parent, Gravity.CENTER_HORIZONTAL, 0, 50);

                ImageView fullImageView = (ImageView) popupView.findViewById(R.id.imgProductDetail);
                Picasso.with(popupView.getContext()).load(productItem.getImageUrl()).into(fullImageView);

            }
        });
    }

    public void  onbtnProductDetailClose(View view)
    {
        popupView = null;
        popupWindow.dismiss();
    }

    class getList extends AsyncTask<String, String, String> {
        ArrayList dataList;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String serviceParams = "GetProducts";
            String responseText = CommonFunctions.GetServiceResponse(CommonFunctions.ServiceUrl + serviceParams);

            if(responseText!= "")
            {
                try{

                    final Gson gson = new Gson();
                    Type typeList = new TypeToken<List<Product>>(){}.getType();
                    dataList = gson.fromJson(responseText, typeList);

                }
                catch (Exception exception)   {
                    //exception.toString();
                }
            }
            return null;
        }

        protected void onProgressUpdate(String... progress) {
        }

        @Override
        protected void onPostExecute(String resultParam) {
            if(dataList!= null)
            {
                bindProducts(dataList);
            }
        }
    }

    class getDescriptionText extends AsyncTask<String, String, String> {
        String responseText = "";
        ArrayList dataList;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String serviceParams = "GetText";
            responseText = CommonFunctions.GetServiceResponseAsSingleItem("ProductPageDescription");

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
                final TextView txtProductPageDescription = (TextView)findViewById(R.id.txtProductPageDescription);
                txtProductPageDescription.setText(item.getTextValue().toString());
            }

        }
    }

    public void onBackPressed() {
        if (popupView != null)
        {
            popupView = null;
            popupWindow.dismiss();
        }

        super.onBackPressed();
        //this.finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                this.finish();
                return true;
            case R.id.action_refresh:
                getContent();
                return true;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.optionsMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void setRefreshActionButtonState(final boolean refreshing) {
        if (optionsMenu != null) {
            final MenuItem refreshItem = optionsMenu
                    .findItem(R.id.action_refresh);
            if (refreshItem != null) {
                if (refreshing) {
                    refreshItem.setActionView(R.layout.actionbar_indeterminate_progress);
                } else {
                    refreshItem.setActionView(null);
                }
            }
        }
    }

}
