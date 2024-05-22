package ru.mirea.maksimovaok.employeedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db = App.getInstance().getDatabase();
        EmployeeDao employeeDao = db.employeeDao();
        Employee employee = new Employee();
        employee.id = 1;
        employee.name = "One-Punch-Man";
        employee.salary = 10000;
        Employee friend = new Employee();
        friend.id = 100;
        friend.name = "That friend who always helps everyone";
        friend.salary = 0;

        employeeDao.insert(employee);
        employeeDao.insert(friend);

        List<Employee> employees = employeeDao.getAll();

        employee = employeeDao.getById(100);

        friend.salary = 666;
        employeeDao.update(friend);
        Log.d("DB", employee.name + " " + employee.salary);
        Log.d("DB", friend.name + " " + friend.salary);
    }
}