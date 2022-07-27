package com.example.openbrowser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import static android.Manifest.permission.CALL_PHONE;

public class MainActivity extends AppCompatActivity {
    ImageView imgBrowser;
    ImageView imgMessage;
    ImageView imgCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgMessage = findViewById(R.id.imageViewMessage);
        imgBrowser = findViewById(R.id.imgBrowser);
        imgCall = findViewById(R.id.imageViewCall);

        Intent intent = new Intent();
        imgBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/"));
                startActivity(intent);
            }
        });

        imgMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setAction(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body", "Chao ban...");
                intent.setData(Uri.parse("sms: 09343434"));
                startActivity(intent);
            }
        });

        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel: 093434343"));
                /*startActivity(intent);*/
                if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{CALL_PHONE}, 1);
                    }
                }
            }
        });
    }
}