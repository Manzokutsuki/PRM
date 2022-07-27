package com.example.helloworld;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.behavior.SwipeDismissBehavior;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button showButton;
    private TextView nameText;
    private EditText enterName;
    ListView lvCategory;
    ArrayList<String> arrayCategory;
    Button btnAdd;
    Button btnUpdate;
    TextView ptAdd;
    int vitri;
    String updateCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lvLayout();
    }
    void lvLayout(){
        setContentView(R.layout.listview_layout);
        lvCategory = (ListView) findViewById(R.id.lvCategory);
        btnAdd = findViewById(R.id.btnAdd);
        ptAdd = findViewById(R.id.ptAdd);
        enterName = findViewById(R.id.editTextTextPersonName);
        btnUpdate = findViewById(R.id.btnUpdate);
        arrayCategory = new ArrayList<>();
        arrayCategory.add("Android");
        arrayCategory.add("PHP");
        arrayCategory.add("iOS");
        arrayCategory.add("Unity");
        arrayCategory.add("ASP.NET");
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayCategory);
        lvCategory.setAdapter(adapter);
        btnAdd.setOnClickListener(v -> {
            String courseName = ptAdd.getText().toString();
            arrayCategory.add(courseName);
            adapter.notifyDataSetChanged();
        });
        lvCategory.setOnItemClickListener((parent, view, position, id) -> {
            
            vitri = position;
            Toast.makeText(MainActivity.this, arrayCategory.get(position), Toast.LENGTH_LONG).show();
            ptAdd.setText(arrayCategory.get(position));

        });
        lvCategory.setOnItemLongClickListener((parent, view, position, id) -> {
            new AlertDialog.Builder(this)
                    .setTitle("Delete Course")
                    .setMessage("Are you sure to delete this course?")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            arrayCategory.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    }).setNegativeButton("Cancel", null).show();

            return false;
        });
        btnUpdate.setOnClickListener(v -> {
            arrayCategory.set(vitri, ptAdd.getText().toString());
            adapter.notifyDataSetChanged();
        });
    }

    void oldCode(){
        setContentView(R.layout.activity_main);
        showButton = findViewById(R.id.button);
        nameText = findViewById(R.id.textView3);
        nameText.setText("Hello from Java!");
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = enterName.getText().toString();
                if(name.isEmpty()){
                    nameText.setText("Hello everyone!");
                }else{
                    nameText.setText("Hello, " + name);
                }
            }
        });
    }
}