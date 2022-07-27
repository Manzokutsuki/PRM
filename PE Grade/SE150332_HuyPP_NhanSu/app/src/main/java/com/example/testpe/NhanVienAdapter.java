package com.example.testpe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NhanVienAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<Employee> nhanVienList;

    public NhanVienAdapter(MainActivity context, int layout, List<Employee> nhanVienList) {
        this.context = context;
        this.layout = layout;
        this.nhanVienList = nhanVienList;
    }

    @Override
    public int getCount() {
        return nhanVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtId;
        ImageView imgDelete, imgEdit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.txtId = convertView.findViewById(R.id.textviewTen);
            holder.imgDelete = convertView.findViewById(R.id.imageviewDelete);
            holder.imgEdit = convertView.findViewById(R.id.imageviewEdit);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Employee employee = nhanVienList.get(position);
        holder.txtId.setText(""+employee.getID());

        holder.imgEdit.setOnClickListener(v -> {
            context.DialogSuaCongViec(employee);
        });

        holder.imgDelete.setOnClickListener(v -> {
            context.DialogXoaCongViec(employee.getName(), employee.getID());
        });
        return convertView;
    }
}
