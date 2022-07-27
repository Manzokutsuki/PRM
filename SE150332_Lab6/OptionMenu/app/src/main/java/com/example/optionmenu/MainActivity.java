package com.example.optionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnGoodBye;
    Button btnShowDemoDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGoodBye = findViewById(R.id.btnGoodBye);
        btnShowDemoDialog = findViewById(R.id.btnShowDemoDialog);
        btnGoodBye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodBye(v);
            }
        });
        btnShowDemoDialog.setOnClickListener(v -> {
            openFeedbackDialog(Gravity.CENTER);
        });

    }

    public void goodBye(@NonNull View view){
        switch(view.getId()){
            /*case R.id.btnShowCommentDialog:
                DialogFragment commentDialog = CommentDialog.newInstance();
                commentDialog.show(getSupportFragmentManager(),"commentDialog");
                break;*/
            case R.id.btnGoodBye:
                Toast.makeText(this, "Goodbye", Toast.LENGTH_SHORT).show();
                break;
            /*case R.id.btnShowDemoDialog:
                DialogFragment demoDialog = DemoDialog.newInstance();
                demoDialog.show(getSupportFragmentManager(), "demoDialog");
                break;*/
        }
    }

    private void openFeedbackDialog(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_feedback);
        Window window = dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttribues = window.getAttributes();
        windowAttribues.gravity = gravity;
        window.setAttributes(windowAttribues);

        //Cancel when click outside of box event
        /*if(Gravity.BOTTOM == gravity){*/
            dialog.setCancelable(true);
        /*}else{
            dialog.setCancelable(false);
        }*/
        EditText edtFeedback = dialog.findViewById(R.id.edtFeedback);
        Button btnNoTks = dialog.findViewById(R.id.btnNoThanks);
        Button btnSend = dialog.findViewById(R.id.btnSend);

        btnNoTks.setOnClickListener(v -> {
            dialog.dismiss();
        });

        btnSend.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Your feedback is sent", Toast.LENGTH_SHORT).show();
        });

        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mExit:
                finishAffinity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}