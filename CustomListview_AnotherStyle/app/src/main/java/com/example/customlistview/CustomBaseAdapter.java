package com.example.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    String listFruit[];
    int listImages[];
    LayoutInflater inflater;

    public CustomBaseAdapter(Context context, String[] fruitList, int[] images){
        this.context =context;
        this.listFruit = fruitList;
        this.listImages = images;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return listFruit.length;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_custom_list_view,null);
        TextView txtName = (TextView) convertView.findViewById(R.id.tvName);
        TextView txtDescription = (TextView) convertView.findViewById(R.id.tvDescription);
        ImageView fruitImg = (ImageView) convertView.findViewById(R.id.ivImage);
        txtName.setText(listFruit[position]);
        fruitImg.setImageResource(listImages[position]);
        return convertView;
    }
}
