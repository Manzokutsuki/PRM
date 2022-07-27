package com.example.demoactivityintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    Button btnSendInt;
    Button btnSendString;
    Button btnBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSendInt = findViewById(R.id.btnSendInt);
        btnSendString = findViewById(R.id.btnSendString);
        btnBundle = findViewById(R.id.btnBundle);

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        btnSendInt.setOnClickListener(v -> {
            intent.putExtra("Dulieu", 2022);
            startActivity(intent);
        });

        //Send Data
        btnSendString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("Dulieu", "Day la noi dung chuoi");
                startActivity(intent);
            }
        });

        //Bundle Code

        btnBundle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] arrName ={"Sai Gon", "Ha Noi", "Da Nang", "Can Tho"};
                Student hs = new Student("Tran Van Nam", 2000);
                Bundle bundle = new Bundle();
                bundle.putString("chuoi", "Truyen data voi Bundle");
                bundle.putInt("dangso", 225);
                bundle.putStringArray("MangTen", arrName);
                bundle.putSerializable("obj", hs);
                intent.putExtra("Dulieu", bundle);
                startActivity(intent);
            }
        });
        Log.d("AAA", "onCreate Main");
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