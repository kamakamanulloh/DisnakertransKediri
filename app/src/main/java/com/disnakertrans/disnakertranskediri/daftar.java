package com.disnakertrans.disnakertranskediri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;


import android.widget.AdapterView;

import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class daftar extends AppCompatActivity implements ListView.OnItemClickListener{

    private ListView listView;//deklarasi listview

    private String JSON_STRING;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        listView = (ListView) findViewById(R.id.listView);//deklarasi 1objek lv
        listView.setOnItemClickListener(this);//fungsi klik listview

        getJSON();//memanggil method json
    }


    private void showEmployee(){//mensetting data dari json ke listview
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(konfigurasi.TAG_ID);
                String name = jo.getString(konfigurasi.TAG_NAMA);


                HashMap<String,String> employees = new HashMap<>();
                employees.put(konfigurasi.TAG_ID,id);
                employees.put(konfigurasi.TAG_NAMA,name);

                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                daftar.this, list, R.layout.list_mynews,
                new String[]{konfigurasi.TAG_NAMA},
                new int[]{R.id.name});

        listView.setAdapter(adapter);
    }

    private void getJSON(){//ambil data dari json
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(daftar.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(konfigurasi.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
    public void onBackPressed() {
        Intent intent = new Intent(daftar.this, home.class);

        finish();
        startActivity(intent);
    }//fungsi back
    @Override
    public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {//fungsi klik lv
        Intent intent = new Intent(this, detail.class);//tujuan aktivity
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(konfigurasi.TAG_ID).toString();
        String jns = map.get(konfigurasi.TAG_NAMA).toString();//mengambil data dari json
        intent.putExtra(konfigurasi.EMP_ID,empId);
        intent.putExtra(konfigurasi.TAG_NAMA,jns);//fungsi seperti session
        startActivity(intent);
    }
}


