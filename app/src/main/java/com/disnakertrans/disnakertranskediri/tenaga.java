package com.disnakertrans.disnakertranskediri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class tenaga extends AppCompatActivity implements ListView.OnItemClickListener{
    private ProgressDialog pDialog;

    private static final int progress_bar_type = 0;

    private ListView listView;

    private String JSON_STRING;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengumuman);
        listView = (ListView) findViewById(R.id.lv);
        listView.setOnItemClickListener(this);

        getJSON();
    }


    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);

                String name = jo.getString(konfigurasi.namaunduh);

                String link = jo.getString(konfigurasi.file);


                HashMap<String,String> employees = new HashMap<>();

                employees.put(konfigurasi.namaunduh,name);

                employees.put(konfigurasi.file,link);

                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                tenaga.this, list, R.layout.list_pengumuman,
                new String[]{konfigurasi.namaunduh,konfigurasi.file},
                new int[]{R.id.name, R.id.txttgl});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(tenaga.this,"Mengambil Data","Mohon Tunggu...",false,false);
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
                String s = rh.sendGetRequest(konfigurasi.URL_tenaga);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {

        Intent intent = new Intent(this, detailunduh.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);

        String jns = map.get(konfigurasi.namaunduh).toString();

        String link = map.get(konfigurasi.file).toString();

        intent.putExtra("nama",jns);

        intent.putExtra("link",link);
        startActivity(intent);
    }
    public void onBackPressed() {
        Intent intent = new Intent(tenaga.this, home.class);

        finish();
        startActivity(intent);
    }
}




