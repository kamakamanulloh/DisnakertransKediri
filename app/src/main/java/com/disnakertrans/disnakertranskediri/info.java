package com.disnakertrans.disnakertranskediri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class info extends AppCompatActivity {
    Button btnpel,btntenaga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        btnpel=(Button)findViewById(R.id.btntenaga);
        btntenaga=(Button)findViewById(R.id.btninfo);
        btntenaga.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(info.this, tenaga.class);
                finish();
                startActivity(intent);
            }
        });
//tombol tenaga
        btnpel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(info.this, pelatihan.class);
                finish();
                startActivity(intent);
            }
        });
    }//tombol pl
    public void onBackPressed() {
        Intent intent = new Intent(info.this, MainActivity.class);

        finish();
        startActivity(intent);
    }
}
