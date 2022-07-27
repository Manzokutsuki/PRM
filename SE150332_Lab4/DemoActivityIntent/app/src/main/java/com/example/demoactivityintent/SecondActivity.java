package com.example.demoactivityintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    Button btnBack;
    TextView txtReceiveString;
    TextView txtReceiveInt;
    TextView txtReceiveBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btnBack = findViewById(R.id.btnBack);
        txtReceiveString = findViewById(R.id.txtReceiveString);
        txtReceiveInt = findViewById(R.id.txtReceiveInt);
        txtReceiveBundle = findViewById(R.id.txtReceiveBundle);

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
        });

        Intent intent = getIntent();

        txtReceiveString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noidung = intent.getStringExtra("Dulieu");
                txtReceiveString.setText(noidung);
            }
        });

        txtReceiveInt.setOnClickListener(v -> {
            int noiDung = intent.getIntExtra("Dulieu", 1234);
            txtReceiveInt.setText(""+noiDung);
        });

        txtReceiveBundle.setOnClickListener(v -> {
            Bundle bundle = intent.getBundleExtra("Dulieu");
            if(bundle!= null){
                String name= bundle.getString("chuoi");
                int so = bundle.getInt("dangso", 123);
                String[] arrayName = bundle.getStringArray("MangTen");
                Student hs = (Student) bundle.getSerializable("obj");
                txtReceiveBundle.setText(name + "\n" + so + "\n" + arrayName[0] + "\n" + hs.getName());
            }
        });

        Log.d("AAA", "onCreate SecondActivity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("AAA", "onStart Main");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("AAA", "onRestart Main");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AAA", "onResume Main");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("AAA", "onPause Main");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("AAA", "onStop Main");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("AAA", "onDestroy Main");
    }
}