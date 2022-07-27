package com.example.customlv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FruitAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Fruit> fruits;

    public FruitAdapter(Context context, int layout, List<Fruit> fruits) {
        this.context = context;
        this.layout = layout;
        this.fruits = fruits;
    }

    @Override
    public int getCount() {
        return fruits.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        //Mapping view
        TextView txtName = view.findViewById(R.id.tvName);
        TextView txtDescription = view.findViewById(R.id.tvDescription);
        ImageView imageAvartar = view.findViewById(R.id.ivImage);

        Fruit fruit = fruits.get(position);
        txtName.setText(fruit.getName());
        txtDescription.setText(fruit.getDescription());
        imageAvartar.setImageResource(fruit.getAvatar());
        return view;
    }
}
