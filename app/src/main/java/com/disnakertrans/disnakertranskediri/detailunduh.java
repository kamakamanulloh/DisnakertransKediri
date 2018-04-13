package com.disnakertrans.disnakertranskediri;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;


public class detailunduh extends AppCompatActivity {
    private static Button download;

    String downloadDocUrl;
    TextView txtnm,txtfile;

    String nm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailunduh);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Download Info");
        download=(Button)findViewById(R.id.btnunduh);
        Intent intent = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Download Info");
        nm = intent.getStringExtra("nama");

        downloadDocUrl = intent.getStringExtra("link");


        txtnm = (TextView) findViewById(R.id.txtnm);
        txtfile = (TextView) findViewById(R.id.txttgl);

        txtnm.setText(nm);
        txtfile.setText(downloadDocUrl);
        download.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {

                if (isConnectingToInternet()){
                    new DownloadTask(detailunduh.this, download, downloadDocUrl);

                }

                else
                    Toast.makeText(detailunduh.this, "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();
            }

        });


    }




    //Check if internet is present or not
    private boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }


    public void onBackPressed() {
        Intent intent = new Intent(detailunduh.this, home.class);

        finish();
        startActivity(intent);
    }


}