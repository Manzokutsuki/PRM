package com.example.feedbackmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TraineeService traineeService;
    private EditText etname, etemail, etphone, etgioitinh;
    private Button btnSave, btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etname = findViewById(R.id.updateName);
        etemail = findViewById(R.id.updateEmail);
        etphone = findViewById(R.id.updatePhone);
        etgioitinh = findViewById(R.id.updateGenre);
        btnSave = findViewById(R.id.btnSave);
        btnRead = findViewById(R.id.btnEdit);
        btnSave.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        traineeService = TraineeRepository.getTraineeService();
    }

    private void read(){
        Intent intent = new Intent(MainActivity.this, TraineeListActivity.class);
        startActivity(intent);
    }




    private void save(){
        String name = etname.getText().toString();
        String email = etemail.getText().toString();
        String phone = etphone.getText().toString();
        String gender = etgioitinh.getText().toString();

        Trainee trainee = new Trainee(name, email, phone, gender);
        try{
            Call<Trainee> call = traineeService.createTrainees(trainee);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if(response.body()!=null){
                        Toast.makeText(MainActivity.this, "Save successfully", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Save fail", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Log.d("Error", e.getMessage());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                save();
                break;
            case R.id.btnEdit:
                read();
                break;
        }
    }
}