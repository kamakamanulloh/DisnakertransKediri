package com.disnakertrans.disnakertranskediri;


import android.content.Intent;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    Button btndaftar, btnpengumuman, btnid;
    EditText txtid;

    EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//panggil layout


        btndaftar = (Button) findViewById(R.id.button4);
        btnpengumuman = (Button) findViewById(R.id.button5);
        btnid = (Button) findViewById(R.id.button6);


        txtid = (EditText) findViewById(R.id.editText4);//deklarasi objek

        btnid.setOnClickListener(new View.OnClickListener() {//fungsi tombol cek


            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, vc.class);


                intent.putExtra(konfigurasi.nik,txtid.getText().toString());


                finish();
                startActivity(intent);
            }

        });
        btndaftar.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, daftar.class);



                finish();
                startActivity(intent);
            }

        });

        btnpengumuman.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, info.class);



                finish();
                startActivity(intent);
            }

        });
//memanggil fungsi info
    }//fungsi tombol untuk panggil activity
    boolean doubleBackToExitPressedOnce = false;

    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }//fungsi tekan back 2 kali
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Klik BACK dua kali untuk exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;

            }
        }, 2000);//fungsi panggil jika tekan 1 kali
    }

}
