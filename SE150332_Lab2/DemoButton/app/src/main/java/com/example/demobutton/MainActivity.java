package com.example.demobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtNoidung;
    TextView txtMin;
    TextView txtMax;
    Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calculatorLayout();
    }
    
    void calculatorLayout(){
        setContentView(R.layout.calculator_layout);
        txtNoidung = findViewById(R.id.textView7);
        EditText edtSoThuNhat = findViewById(R.id.editTextNumber4);
        EditText edtSoThuHai = findViewById(R.id.editTextNumber5);
        Button btnCong = findViewById(R.id.button3);
        Button btnTru = findViewById(R.id.button4);
        Button btnNhan = findViewById(R.id.button5);
        Button btnChia = findViewById(R.id.button6);
        btnCong.setOnClickListener(v -> {
            int soThuNhat = Integer.parseInt(edtSoThuNhat.getText().toString());
            int soThuHai = Integer.parseInt(edtSoThuHai.getText().toString());
            txtNoidung.setText(String.valueOf(soThuNhat + soThuHai));
        });
        btnTru.setOnClickListener(v -> {
            int soThuNhat = Integer.parseInt(edtSoThuNhat.getText().toString());
            int soThuHai = Integer.parseInt(edtSoThuHai.getText().toString());
            txtNoidung.setText(String.valueOf(soThuNhat - soThuHai));
        });
        btnNhan.setOnClickListener(v -> {
            int soThuNhat = Integer.parseInt(edtSoThuNhat.getText().toString());
            int soThuHai = Integer.parseInt(edtSoThuHai.getText().toString());
            txtNoidung.setText(String.valueOf(soThuNhat * soThuHai));
        });
        btnChia.setOnClickListener(v -> {
            float soThuNhat = Integer.parseInt(edtSoThuNhat.getText().toString());
            float soThuHai = Integer.parseInt(edtSoThuHai.getText().toString());
            txtNoidung.setText(String.valueOf(soThuNhat / soThuHai));
        });
    }


    void generatorLayout(){
        setContentView(R.layout.generator_layout);
        txtNoidung = findViewById(R.id.editTextNumber3);
        txtMin = findViewById(R.id.editTextNumber);
        txtMax = findViewById(R.id.editTextNumber2);
        btnClick = findViewById(R.id.button2);

        //Su kien
        btnClick.setOnClickListener(v -> {
            int min = Integer.parseInt(txtMin.getText().toString());
            int max = Integer.parseInt(txtMax.getText().toString());

            //Tao ngau nhien tu min toi max
            int number = (int) ((Math.random() * ( (max + 1)  - min)) + min);
            txtNoidung.setText(number+"");
        });
    }

    void randomNumberActivityMain(){
        setContentView(R.layout.activity_main);
        //Anh xa
        txtNoidung = (TextView) findViewById(R.id.textView);
        btnClick = (Button) findViewById(R.id.btnSignIn);

        //Su kien
        btnClick.setOnClickListener(v ->{
            txtNoidung.setText("Lap trinh Android 2022");
            Random random = new Random();
            int number = random.nextInt();
            txtNoidung.setText(number+"");
        } );
    }


}