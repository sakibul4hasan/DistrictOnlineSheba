package com.example.hatiyaonlinesheba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Doner_Details extends AppCompatActivity {

    TextView dName, dBlood, dLBD, dMobile, dEmail, dAddress;
    LinearLayout call, msg;
    public static String name = "";
    public static String blood = "";
    public static String lbd = "";
    public static String mobile = "";
    public static String email = "";
    public static String address = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doner_details);
        dName = findViewById(R.id.dName);
        dBlood = findViewById(R.id.dBlood);
        dLBD = findViewById(R.id.dLBD);
        dMobile = findViewById(R.id.dMobile);
        dEmail = findViewById(R.id.dEmail);
        dAddress = findViewById(R.id.dAddress);
        call = findViewById(R.id.call);
        msg = findViewById(R.id.msg);

        ///---------------------------------------------------------------
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(Doner_Details.this,R.color.white));// set status background white
        ///---------------------------------------------------------------




        dName.setText("Name: "+name);
        dBlood.setText("Blood Group: "+blood);
        dLBD.setText("Last Blood Donate: "+lbd);
        dMobile.setText("Mobile: +88"+mobile);
        dEmail.setText("Email: "+email);
        dAddress.setText("Address: "+address);


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //---------------------
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+mobile));
                startActivity(intent);
            }
        });

        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //----------------------
                Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:" + mobile));
                intent.putExtra( "sms_body", "Hi "+name+",\n" );
                startActivity(intent);
            }
        });








    }
}