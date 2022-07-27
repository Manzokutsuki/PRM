package com.example.testpe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TraineeService traineeService;
    private Button btnGetAll;
    private ListView spIdList;
    NhanVienAdapter empAdapter;
    private ArrayList<Employee> empArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGetAll = findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);
        traineeService = TraineeRepository.getTraineeService();

        spIdList = findViewById(R.id.spIdList);
        spIdList.setOnItemLongClickListener((parent, view, position, id) -> {
            long entityId = empArray.get(position).getID();
            readDetail(entityId);
            return false;
        });
        empArray = new ArrayList<>();
        read();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mAdd:
                DialogThemCongViec();
                break;
            case R.id.mExit:
                finishAffinity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void DialogThemCongViec(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_nhan_vien);

        EditText etname = dialog.findViewById(R.id.updateName);
        EditText etedate = dialog.findViewById(R.id.updateDate);
        EditText etgioitinh = dialog.findViewById(R.id.updateGenre);
        Button btnXacNhan = dialog.findViewById(R.id.btnEdit);
        Button btnHuy = dialog.findViewById(R.id.btnCancel);

        btnXacNhan.setOnClickListener(v -> {
            //Xac nhan
            String newName = etname.getText().toString();
            String newDate = etedate.getText().toString();
            String newGenre = etgioitinh.getText().toString();
            Employee newEmployee = new Employee(newName, newDate, newGenre);
            try{
                Call<Employee> call = traineeService.createTrainees(newEmployee);
                call.enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        if(response.body()!=null){
                            Toast.makeText(MainActivity.this, "Save successfully", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Save fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }catch (Exception e){
                Log.d("Error", e.getMessage());
            }
            dialog.dismiss();
            read();
        });

        btnHuy.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();
    }

    private void read(){
        traineeService = TraineeRepository.getTraineeService();
        Call<Employee[]> call = traineeService.getAllTrainees();
        call.enqueue(new Callback<Employee[]>() {
            @Override
            public void onResponse(Call<Employee[]> call, Response<Employee[]> response) {
                Employee[] employees = response.body();
                if(employees == null){
                    return;
                }
                empArray.clear();
                for(Employee employee : employees){
                    empArray.add(employee);
                }
                empAdapter = new NhanVienAdapter(MainActivity.this,
                        R.layout.dong_nhan_vien, empArray);
                spIdList.setAdapter(empAdapter);
            }

            @Override
            public void onFailure(Call<Employee[]> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Can not read", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void readDetail(long id){

        Call<Employee> call = traineeService.getAllTrainees(id);


        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Employee employee = response.body();
                if(employee == null){
                    return;
                }
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.detail_nhan_vien);

                EditText etname = dialog.findViewById(R.id.updateName);
                EditText etemail = dialog.findViewById(R.id.updateDate);
                EditText etgioitinh = dialog.findViewById(R.id.updateGenre);
                Button btnHuy = dialog.findViewById(R.id.btnCancelRead);

                etname.setText(employee.getName());
                etemail.setText(employee.getDate());
                etgioitinh.setText(employee.getGender());

                btnHuy.setOnClickListener(v -> {
                    dialog.dismiss();
                });

                dialog.show();
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Can not read detail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void DialogSuaCongViec(Employee employee){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.update_nhan_vien);

        long id = employee.getID();

        EditText etname = dialog.findViewById(R.id.updateName);
        EditText etedate = dialog.findViewById(R.id.updateDate);
        EditText etgioitinh = dialog.findViewById(R.id.updateGenre);
        Button btnXacNhan = dialog.findViewById(R.id.btnEdit);
        Button btnHuy = dialog.findViewById(R.id.btnCancel);

        etname.setText(employee.getName());
        etedate.setText(employee.getDate());
        etgioitinh.setText(employee.getGender());

        btnXacNhan.setOnClickListener(v -> {
            //Xac nhan
            String newName = etname.getText().toString();
            String newDate = etedate.getText().toString();
            String newGenre = etgioitinh.getText().toString();
            Employee newEmployee = new Employee(id, newName, newDate, newGenre);

            //Edit
            Call<Employee> call = traineeService.updateTrainees(id, newEmployee);
            call.enqueue(new Callback<Employee>() {
                @Override
                public void onResponse(Call<Employee> call, Response<Employee> response) {
                    if(response.body() !=null){
                        Toast.makeText(MainActivity.this,"Update sucessfully",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Employee> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Edit fail", Toast.LENGTH_SHORT).show();
                }
            });

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
                //Delete
                Call<Employee> call = traineeService.deleteTrainees(Id);
                call.enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        if(response.body() != null){
                            Toast.makeText(MainActivity.this, "Delete successfully", Toast.LENGTH_SHORT).show();
                            read();
                        }
                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Delete fail", Toast.LENGTH_SHORT).show();
                    }
                });


                Toast.makeText(MainActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGetAll:
                read();
                break;
        }
    }
}