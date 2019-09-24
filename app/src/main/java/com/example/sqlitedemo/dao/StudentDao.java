package com.example.sqlitedemo.dao;

import android.database.Cursor;

import com.example.sqlitedemo.model.Student;

import java.util.List;

public interface StudentDao {

    List<Student> selectAllStudents();
    //条件查询
    //增删改一个学生
    void insert(Student student);
    void update(Student student);
    void delete(String studentName);
    void delete(int id);
    Cursor selectByCursor();

}
