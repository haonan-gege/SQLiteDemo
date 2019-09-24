package com.example.sqlitedemo.service;

import android.content.Context;
import android.database.Cursor;

import com.example.sqlitedemo.dao.StudentDao;
import com.example.sqlitedemo.dao.StudentDaoImpl;
import com.example.sqlitedemo.model.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    public StudentServiceImpl(Context context){
        studentDao=new StudentDaoImpl(context);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.selectAllStudents();
    }

    @Override
    public void insert(Student student) {
        studentDao.insert(student);
    }

    @Override
    public void modifyRealNumber(Student student) {
        studentDao.update(student);
    }

    @Override
    public void delete(String studentName) {
        studentDao.delete(studentName);
    }
}
