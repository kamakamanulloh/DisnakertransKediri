package com.disnakertrans.disnakertranskediri;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static com.disnakertrans.disnakertranskediri.R.id.txtid;

public class home extends AppCompatActivity {
    EditText txtnik;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setBackgroundDrawableResource(R.drawable.kantor);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ImageView daftar=(ImageView)findViewById(R.id.btndaftar);
        ImageView infopel=(ImageView)findViewById(R.id.btninfopel);
        ImageView pengumuman =(ImageView)findViewById(R.id.btninfopeng);
        ImageView infotng=(ImageView)findViewById(R.id.btninfotng);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, daftar.class);



                finish();
                startActivity(intent);
            }
        });

        infopel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(home.this, pelatihan.class);
                finish();
                startActivity(intent);
            }
        });
        infotng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(home.this, tenaga.class);
                finish();
                startActivity(intent);
            }
        });

        pengumuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new AlertDialog.Builder(home.this);
                View dialogView;
                inflater = getLayoutInflater();
                dialogView = inflater.inflate(R.layout.form, null);
                dialog.setView(dialogView);
                dialog.setCancelable(true);
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setTitle("Masukkan NIK");

                txtnik = (EditText) dialogView.findViewById(R.id.txtnik);



                dialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(home.this, vc.class);


                        intent.putExtra(konfigurasi.nik,txtnik.getText().toString());


                        finish();
                        startActivity(intent);


                    }
                });

                dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
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
