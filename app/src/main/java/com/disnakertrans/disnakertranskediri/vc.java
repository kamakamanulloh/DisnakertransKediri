package com.disnakertrans.disnakertranskediri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class vc extends AppCompatActivity {
    String id;

    TextView txtid, nm, vc_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vc);
        id = getIntent().getStringExtra(konfigurasi.nik);

        txtid = (TextView) findViewById(R.id.txtid);
        nm = (TextView) findViewById(R.id.txtnm);
        vc_ = (TextView) findViewById(R.id.txtvc);
        txtid.setText(id);
      getEmployee();
    }

    private void getEmployee() {
        class GetEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(vc.this, "Fetching...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            private void showEmployee(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
                    JSONObject c = result.getJSONObject(0);
                    String name = c.getString(konfigurasi.nama);
                    String ID = c.getString(konfigurasi.nik);
                    String ket = c.getString(konfigurasi.TAG_ket);

                    nm.setText(name);
                    vc_.setText(ket);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_EMP, id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }
    public void onBackPressed() {
        Intent intent = new Intent(vc.this, home.class);

        finish();
        startActivity(intent);
    }
}
