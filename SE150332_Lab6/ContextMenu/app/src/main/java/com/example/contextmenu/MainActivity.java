package com.example.contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnChooseColor;
    ConstraintLayout clScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChooseColor = findViewById(R.id.btnChooseColor);
        clScreen = findViewById(R.id.clScreen);
        registerForContextMenu(btnChooseColor);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuRed:
                clScreen.setBackgroundColor(Color.RED);
                break;
            case R.id.menuYeloow:
                clScreen.setBackgroundColor(Color.YELLOW);
                break;
            case R.id.menuBlue:
                clScreen.setBackgroundColor(Color.BLUE);
                break;
        }
        return super.onContextItemSelected(item);
    }
}