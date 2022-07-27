package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String fruitList[] = {"Apple", "Banana", "Blueberry", "Corn", "Grapes"};
    int fruitImages[] ={R.drawable.apple, R.drawable.banana, R.drawable.blueberry, R.drawable.corn, R.drawable.grapes };
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.customListView);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(),fruitList, fruitImages);
        listView.setAdapter(customBaseAdapter);
    }
}