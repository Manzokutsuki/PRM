package com.example.feedbackmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TraineeListActivity extends AppCompatActivity {

    private TraineeService traineeService;
    private ListView spIdList;
    private Button btnBack;
    NhanVienAdapter empAdapter;
    ArrayList<Trainee> empArray;
    ArrayList<Long> allId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainee_list);

        spIdList = findViewById(R.id.spIdList);
        allId = new ArrayList<>();
        empArray = new ArrayList<>();

        read();

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(TraineeListActivity.this,MainActivity.class);
            startActivity(intent);
        });
        /*spIdList.setOnItemLongClickListener((parent, view, position, id) -> {
            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Delete Trainee")
                    .setMessage("Are you sure to delete this trainee?")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            long id = allId.get(position);
                            delete(id);
                            read();
                        }
                    }).setNegativeButton("Cancel", null).show();

            return false;
        });*/
    }

    public void DialogSuaCongViec(Trainee trainee){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_nhan_vien);

        long id = trainee.getId();

        EditText etname = dialog.findViewById(R.id.updateName);
        EditText etemail = dialog.findViewById(R.id.updateEmail);
        EditText etphone = dialog.findViewById(R.id.updatePhone);
        EditText etgioitinh = dialog.findViewById(R.id.updateGenre);
        Button btnXacNhan = dialog.findViewById(R.id.btnEdit);
        Button btnHuy = dialog.findViewById(R.id.btnCancel);

        etname.setText(trainee.getName());
        etemail.setText(trainee.getEmail());
        etphone.setText(trainee.getPhone());
        etgioitinh.setText(trainee.getGender());

        btnXacNhan.setOnClickListener(v -> {
            //Xac nhan
            String newName = etname.getText().toString();
            String newEmail = etemail.getText().toString();
            String newPhone = etphone.getText().toString();
            String newGenre = etgioitinh.getText().toString();
            Trainee newTrainee = new Trainee(id, newName, newEmail, newPhone, newGenre);
            edit(newTrainee);
            dialog.dismiss();
            read();
        });

        btnHuy.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();
    }

    public void DialogXoaCongViec(String name, long Id){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa công việc với id là " + Id + " không?");

        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete(Id);
                Toast.makeText(TraineeListActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                read();
            }
        });

        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }

    /*private void GetDataCongViec(){
        read();
        empAdapter.notifyDataSetChanged();
    }*/

    /*private void read(){
        traineeService = TraineeRepository.getTraineeService();
        Call<Trainee[]> call = traineeService.getAllTrainees();
        call.enqueue(new Callback<Trainee[]>() {
            @Override
            public void onResponse(Call<Trainee[]> call, Response<Trainee[]> response) {
                Trainee[] trainees = response.body();
                if(trainees == null){
                    return;
                }
                allId.clear();
                for(Trainee trainee :trainees){
                    allId.add(trainee.getId());
                }
                ArrayAdapter adapter = new ArrayAdapter(TraineeListActivity.this,
                        android.R.layout.simple_list_item_1, allId);
                spIdList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Trainee[]> call, Throwable t) {
                Toast.makeText(TraineeListActivity.this, "Can not read", Toast.LENGTH_SHORT).show();
            }
        });
    }*/
    private void read(){
        traineeService = TraineeRepository.getTraineeService();
        Call<Trainee[]> call = traineeService.getAllTrainees();
        call.enqueue(new Callback<Trainee[]>() {
            @Override
            public void onResponse(Call<Trainee[]> call, Response<Trainee[]> response) {
                Trainee[] trainees = response.body();
                if(trainees == null){
                    return;
                }
                empArray.clear();
                for(Trainee trainee :trainees){
                    empArray.add(trainee);
                }
                empAdapter = new NhanVienAdapter(TraineeListActivity.this,
                        R.layout.dong_nhan_vien, empArray);
                spIdList.setAdapter(empAdapter);
            }

            @Override
            public void onFailure(Call<Trainee[]> call, Throwable t) {
                Toast.makeText(TraineeListActivity.this, "Can not read", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void edit(Trainee trainee){
        Call<Trainee> call = traineeService.updateTrainees(trainee.getId(), trainee);
        call.enqueue(new Callback<Trainee>() {
            @Override
            public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                if(response.body() !=null){
                    Toast.makeText(TraineeListActivity.this,"Update sucessfully",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Trainee> call, Throwable t) {
                Toast.makeText(TraineeListActivity.this, "Edit fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void delete(long traineeId){
            Call<Trainee> call = traineeService.deleteTrainees(traineeId);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if(response.body() != null){
                        Toast.makeText(TraineeListActivity.this, "Delete successfully", Toast.LENGTH_SHORT).show();
                        read();
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(TraineeListActivity.this, "Delete fail", Toast.LENGTH_SHORT).show();
                }
            });
    }
}