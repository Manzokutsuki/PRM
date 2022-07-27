package com.example.popup_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;

public class MainActivity extends AppCompatActivity {
    Button btnMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMenu = (Button) findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(v -> {
            ShowMenu();
        });
    }
    private void ShowMenu(){
        PopupMenu popupMenu = new PopupMenu(this, btnMenu);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuAdd:
                        btnMenu.setText("Menu Add");
                        break;
                    case R.id.menuUpdate:
                        btnMenu.setText("Menu Update");
                        break;
                    case R.id.menuDelete:
                        btnMenu.setText("Menu Delete");
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
}