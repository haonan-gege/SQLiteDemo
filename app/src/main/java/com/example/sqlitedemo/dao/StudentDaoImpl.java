package com.example.sqlitedemo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.sqlitedemo.model.Student;
import com.example.sqlitedemo.provider.MyContentProvider;
import com.example.sqlitedemo.utils.MyDBHelper;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private MyDBHelper helper;
    private SQLiteDatabase dbs;
    private Context context;


    public StudentDaoImpl(Context context){
        this.context=context;
        helper=new MyDBHelper(context);
    }
    @Override
    public void insert(Student student) {
        //封装数据
        ContentValues values=new ContentValues();
        values.put("name",student.getName());
        values.put("classmate", student.getClassmate());
        values.put("age",student.getAge());

        context.getContentResolver().update(MyContentProvider.STUDENT_URI,values,"id=?",new String[]{String.valueOf(student.getId())});


        Uri uri =context.getContentResolver().insert(MyContentProvider.STUDENT_URI,values);
        Log.i("SQLiteDemo",uri!=null?((Uri) uri).toString():"");


//        dbs = helper.getWritableDatabase();
//        String sql = "insert into student values(null,?,?,?)";
//        dbs.execSQL(sql, new Object[]{
//                student.getName(),
//                student.getClassmate(),
//                student.getAge()});
        dbs.close();
    }

    @Override
    public void update(Student student) {
        dbs = helper.getWritableDatabase();
        String sql = "update student set classmate=? where name=?";
        dbs.execSQL(sql, new Object[]{
                student.getAge(),
                student.getName()
        });
    }

    @Override
    public void delete(String studentName) {
        dbs = helper.getWritableDatabase();

        dbs.delete("student","user_name=?",new String[]{studentName});
//        String sql = "delete from student where name=?";
//        dbs.execSQL(sql, new Object[]{ studentName });

        dbs.close();




    }

    @Override
    public void delete(int id) {
        context.getContentResolver().delete(MyContentProvider.STUDENT_URI,"id=?",
                new String[]{String.valueOf(id)});
    }

    @Override
    public Cursor selectByCursor() {
        dbs=helper.getReadableDatabase();
        Cursor cursor=context.getContentResolver().query(MyContentProvider.STUDENT_URI,null,null,null,null);

        return cursor;
    }

    @Override
    public List<Student> selectAllStudents() {

        String sql = "select * from student";
        List<Student> students = null;
        dbs = helper.getReadableDatabase();
        Cursor cursor = dbs.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0) {
            students = new ArrayList<>();
//            while (cursor.moveToNext()) {
//                Student student = new Student();
//                student.setId(cursor.getInt(cursor.getColumnIndex("id")));
//
//                student.setName(cursor.getString(cursor.getColumnIndex("name")));
//                student.setClassmate(cursor.getString(cursor.getColumnIndex("classmate")));
//                student.setAge(cursor.getInt(cursor.getColumnIndex("age")));
//
//                students.add(student);
//            }
            cursor.close();
        }
        dbs.close();
        return students;
    }

}
