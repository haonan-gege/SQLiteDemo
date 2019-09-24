package com.example.sqlitedemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.model.Student;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
private List<Student> students;

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }





    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null) {
            view = LayoutInflater.from(view.getContext()).inflate(R.layout.item, viewGroup, false);
            holder = new ViewHolder();
            holder.name = view.findViewById(R.id.name);
            holder.classmate = view.findViewById(R.id.classmate);
            holder.age = view.findViewById(R.id.age);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Student student = students.get(i);
        holder.name.setText(student.getName());
        holder.classmate.setText(String.valueOf(student.getClassmate()));
        holder.age.setText(String.valueOf(student.getAge()));
        return view;
    }
    static class ViewHolder {
        TextView name;
        TextView classmate;
        TextView age;
    }

}
