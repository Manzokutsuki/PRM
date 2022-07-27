package com.example.sqlandoutput;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database database;
    ListView lvCongViec;
    ArrayList<CongViec> arrayCongViec;
    CongViecAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCongViec = findViewById(R.id.listviewCongViec);
        arrayCongViec = new ArrayList<>();
        adapter = new CongViecAdapter(this, R.layout.dong_cong_viec, arrayCongViec);
        lvCongViec.setAdapter(adapter);

        //Create DB
        database = new Database(this, "GhiChu.sqlite",null, 1);

        //Create Table
        database.QueryData("Create table if not exists CongViec(id Integer Primary Key Autoincrement,"
        + "TenCV nvarchar(200))");

        //Insert data
        /*database.QueryData("Insert into CongViec values(null, 'Project Android')");
        database.QueryData("Insert into CongViec values(null, 'Design App')");*/

        //Select Data
        Cursor dataCongViec = database.GetData("Select * from CongViec");
        while (dataCongViec.moveToNext()){
            String ten = dataCongViec.getString(1);
            /*Toast.makeText(this,ten,Toast.LENGTH_SHORT).show();*/
            int id = dataCongViec.getInt(0);
            arrayCongViec.add(new CongViec(id,ten));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_congviec, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuAdd){
            DialogThem();
        }
        return super.onOptionsItemSelected(item);
    }
    private void DialogThem(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_them_cong_viec);
        EditText edtTen = dialog.findViewById(R.id.editTextTenCV);
        Button btnThem = dialog.findViewById(R.id.buttonXacNhan);
        Button btnHuy = dialog.findViewById(R.id.buttonHuy);
        btnThem.setOnClickListener(v -> {
            String tencv = edtTen.getText().toString();
            if(tencv.equals("")){
                Toast.makeText(MainActivity.this,"Vui lòng nhập tên công việc", Toast.LENGTH_SHORT).show();
            }else{
                database.QueryData("Insert into CongViec values(null, '" +tencv+"')");
                Toast.makeText(MainActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetDataCongViec();
            }
        });
        dialog.show();
    }
    private  void GetDataCongViec(){
        Cursor dataCongViec = database.GetData("Select * from CongViec");
        arrayCongViec.clear();
        while(dataCongViec.moveToNext()){
            String ten = dataCongViec.getString(1);
            int id = dataCongViec.getInt(0);
            arrayCongViec.add(new CongViec(id,ten));
        }
        adapter.notifyDataSetChanged();
    }

    public void DialogSuaCongViec(String ten, int id){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sua);

        EditText edtTenCV = dialog.findViewById(R.id.editTextTenCV);
        Button btnXacNhan = dialog.findViewById(R.id.buttonXacNhan);
        Button btnHuy = dialog.findViewById(R.id.buttonHuy);

        edtTenCV.setText(ten);
        btnXacNhan.setOnClickListener(v -> {
            String tenMoi = edtTenCV.getText().toString().trim();
            database.QueryData("UPDATE CongViec SET TenCV = '"+tenMoi+"' WHERE id = '" + id +"'");
            Toast.makeText(MainActivity.this, "Đã cập nhật", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            GetDataCongViec();
        });
        btnHuy.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();
    }

    public void DialogXoaCongViec(String tencv, int Id){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa công việc " + tencv + " không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QueryData("DELETE FROM CongViec WHERE Id = '" +Id+"'");
                Toast.makeText(MainActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                GetDataCongViec();
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }
}