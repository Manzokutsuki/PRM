package com.example.customlv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvFruit;
    ArrayList<Fruit> arrayFruit;
    FruitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        adapter = new FruitAdapter(MainActivity.this, R.layout.activity_custom_list_view, arrayFruit);
        lvFruit.setAdapter(adapter);
    }

    private void mapping(){
        lvFruit = findViewById(R.id.lvFruits);
        arrayFruit = new ArrayList<>();
        arrayFruit.add(new Fruit(R.drawable.apple, "Apple", "ipsum is inserted not an apple"));
        arrayFruit.add(new Fruit(R.drawable.banana, "Banana", "ipsum is inserted not an banana"));
        arrayFruit.add(new Fruit(R.drawable.blueberry, "Blueberry", "ipsum is inserted not an blueberry"));
        arrayFruit.add(new Fruit(R.drawable.corn, "Corn", "ipsum is inserted not an corn"));
        arrayFruit.add(new Fruit(R.drawable.grapes, "Grapes", "ipsum is inserted not an grape"));
    }
}