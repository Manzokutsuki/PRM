package com.example.foodanddrink;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomBookingFoodAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<Food> foodList;
    private final LayoutInflater inflater;
    private RadioButton rbFood;
    private ImageView ivFoodImage;
    private TextView tvFoodName;
    private int selectedFood = -1;

    public CustomBookingFoodAdapter(Context context, ArrayList<Food> foodList, int selectedFood) {
        this.context = context;
        this.foodList = foodList;
        if (selectedFood > -1){
            this.selectedFood = selectedFood;
        }
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_list_booking_food_item, null);

        setData(position, convertView);

        rbFood.setOnClickListener(this::itemCheckChanged);

        ivFoodImage.setOnClickListener(this::itemCheckChanged);

        tvFoodName.setOnClickListener(this::itemCheckChanged);
        return convertView;
    }

    private void setData(int position, View view){
        rbFood = view.findViewById(R.id.rbFood);
        ivFoodImage = view.findViewById(R.id.ivFoodImage);
        tvFoodName = view.findViewById(R.id.tvFoodName);

        Food food = foodList.get(position);
        ivFoodImage.setImageResource(food.getImage());
        tvFoodName.setText(food.getName());

        rbFood.setChecked(false);
        if (this.selectedFood > -1){
            rbFood.setChecked(position == selectedFood);
        }
        rbFood.setTag(position);
    }

    private void itemCheckChanged(View v) {
        selectedFood = (Integer) v.getTag();
        notifyDataSetChanged();
    }

    public Food getSelectedItem() {
        if (selectedFood != -1) {
            return foodList.get(selectedFood);
        }
        return null;
    }
}
