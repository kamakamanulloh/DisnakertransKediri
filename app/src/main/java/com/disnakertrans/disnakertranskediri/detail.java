package com.disnakertrans.disnakertranskediri;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class detail extends AppCompatActivity implements View.OnClickListener{

    // private EditText editTextId;
    private EditText editTextName;
    private EditText txtnama;
    private EditText txtalamat;
    private EditText txtnohp;
    private EditText txttl;
    private EditText txttgl;
    private EditText txtpend;
    private EditText txtagama;
    private EditText txtjk;
    private EditText txtnik;
    private RadioGroup radioGroupNb;
    private RadioButton radioButtonNb;
    private Spinner spNamen;

    int selectedId;

    private Button buttonUpdate;
    // private Button buttonDelete;


    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        radioGroupNb = (RadioGroup) findViewById(R.id.radioGroupNb);
        Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasi.TAG_NAMA);


        editTextName = (EditText) findViewById(R.id.editTextName);
        spNamen = (Spinner) findViewById(R.id.spinner);
        txtalamat = (EditText) findViewById(R.id.txtalamat);
        txtnohp = (EditText) findViewById(R.id.txtnohp);
        txtnama = (EditText) findViewById(R.id.txtnama);
        txttl = (EditText) findViewById(R.id.txttl);
        txttgl = (EditText) findViewById(R.id.txttgl);

        txtpend = (EditText) findViewById(R.id.txtpend);

        txtnik=(EditText) findViewById(R.id.txtnik);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);

        buttonUpdate.setOnClickListener(this);//untuk aktifkan fungsi tombol


        editTextName.setText(id);
        editTextName.setEnabled(false);//tidak bisa diedit

    }




    private void updateEmployee(){
        int selectedId = radioGroupNb.getCheckedRadioButtonId();

        // mencari radio button
        radioButtonNb = (RadioButton) findViewById(selectedId);
        final String jenis = editTextName.getText().toString().trim();
        final String nama = txtnama.getText().toString().trim();

        final String pend = txtpend.getText().toString().trim();
        final String nohp = txtnohp.getText().toString().trim();
        final String txtgl = txttgl.getText().toString().trim();

        final String alamat = txtalamat.getText().toString().trim();
        final String txtl = txttl.getText().toString().trim();
        final String nik = txtnik.getText().toString().trim();
        final String jekel = radioButtonNb.getText().toString().trim();
        final String spnagama = spNamen.getSelectedItem().toString().trim();



        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(detail.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(detail.this,s,Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(konfigurasi.jenis,jenis);
                hashMap.put(konfigurasi.nama,nama);
                hashMap.put(konfigurasi.agama,spnagama);
                hashMap.put(konfigurasi.pend,pend);
                hashMap.put(konfigurasi.nohp,nohp);
                hashMap.put(konfigurasi.tgl,txtgl);
                hashMap.put(konfigurasi.tl,txtl);
                hashMap.put(konfigurasi.alamat,alamat);
                hashMap.put(konfigurasi.jk,jekel);
                hashMap.put(konfigurasi.nik,nik);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(konfigurasi.URL_TAMBAH_EMP,hashMap);

                return s;

            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();

    }

    private void kosong() {

        Intent intent = new Intent(detail.this, home.class);

        finish();
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){

            if (editTextName.getText().toString().length() > 0 && txtalamat.getText().toString().length() > 0 && txtpend.getText().toString().length() > 0 && txtnohp.getText().toString().length() > 0 && txttgl.getText().toString().length() > 0 && txttl.getText().toString().length() > 0 && txtnik.getText().toString().length() > 0) {
                updateEmployee();

                }
            else {
                // Prompt user to enter credentials
                Toast.makeText(getApplicationContext() ,"Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
            }
            }


        }


    }

