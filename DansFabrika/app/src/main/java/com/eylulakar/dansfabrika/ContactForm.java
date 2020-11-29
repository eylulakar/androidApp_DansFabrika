package com.eylulakar.dansfabrika;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by Eylul on 21.10.2014.
 */
public class ContactForm extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_form_layout);

        getActionBar().setDisplayHomeAsUpEnabled(true);


        ((EditText) findViewById(R.id.etxtContactFormFullName)).getBackground().setColorFilter(Color.rgb(52, 52, 52), PorterDuff.Mode.MULTIPLY);
        ((EditText) findViewById(R.id.etxtContactFormEmail)).getBackground().setColorFilter(Color.rgb(52, 52, 52), PorterDuff.Mode.MULTIPLY);
        ((EditText) findViewById(R.id.etxtContactFormMessage)).getBackground().setColorFilter(Color.rgb(52, 52, 52), PorterDuff.Mode.MULTIPLY);

    }

    public void onbtnSendMessageClick(View view)
    {
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
                            sendFormProcess();
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
            sendFormProcess();
        }
    }

    public void sendFormProcess()
    {
        EditText txtFullName = (EditText) findViewById(R.id.etxtContactFormFullName);
        EditText txtEmail = (EditText) findViewById(R.id.etxtContactFormEmail);
        EditText txtMessage = (EditText) findViewById(R.id.etxtContactFormMessage);

        String fullName = txtFullName.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        String message = txtMessage.getText().toString().trim();

        if(fullName == null || fullName.isEmpty() || email == null || email.isEmpty() || message == null || message.isEmpty())
        {
            Toast toast = Toast.makeText(ContactForm.this, "Lütfen tüm alanları doldurunuz.", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }
        else
        {
            new sendForm().execute("");
        }
    }


    class sendForm extends AsyncTask<String, String, String> {
        String responseText = "";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Shows Progress Bar Dialog and then call doInBackground method
        }

        @Override
        protected String doInBackground(String... strings) {

            EditText txtFullName = (EditText) findViewById(R.id.etxtContactFormFullName);
            EditText txtEmail = (EditText) findViewById(R.id.etxtContactFormEmail);
            EditText txtMessage = (EditText) findViewById(R.id.etxtContactFormMessage);

            responseText = CommonFunctions.SendContactForm(txtFullName.getText().toString(), txtEmail.getText().toString(), txtMessage.getText().toString());
            return null;
        }

        protected void onProgressUpdate(String... progress) {
        }

        @Override
        protected void onPostExecute(String resultParam) {
            if(responseText!= "")
            {
                Toast toast = Toast.makeText(ContactForm.this, "Mesajınız başarıyla iletildi, en kısa zamanda dönüş yapılacaktır. Teşekkür ederiz.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        }
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
