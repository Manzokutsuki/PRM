package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        RecyclerView rvUser = findViewById(R.id.rvUser);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvUser.getContext(),
                DividerItemDecoration.VERTICAL);
        rvUser.addItemDecoration(dividerItemDecoration);

        userList = new ArrayList<>();
        userList.add(new User("Mr Bear", "Greaty Bear", "bear@zoo.gmail.com", R.drawable.mr_bear));
        userList.add(new User("Mr Cat", "Greaty Cat", "cat@zoo.gmail.com", R.drawable.mr_cat));
        userList.add(new User("Mr Otter", "Greaty Otter", "otter@zoo.gmail.com", R.drawable.mr_otter));
        userList.add(new User("Mr Tiger", "Greaty Tiger", "tiger@zoo.gmail.com", R.drawable.mr_tiger));
        userList.add(new User("Mr Turtle", "Greaty Turtle", "turtle@zoo.gmail.com", R.drawable.mr_turtle));
        userList.add(new User("Mr Wolf", "Greaty Wolf", "wolf@zoo.gmail.com", R.drawable.mr_wolf));

        UserAdapter adapter = new UserAdapter(userList);
        rvUser.setAdapter(adapter);
        rvUser.setLayoutManager(new LinearLayoutManager(this));
    }
}