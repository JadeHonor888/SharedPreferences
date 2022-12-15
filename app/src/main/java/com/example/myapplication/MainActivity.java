package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieving the value using its keys the file name
        // must be same in both saving and retrieving the data
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String name;
        int age;
        boolean enrolled;

        EditText enterName = (EditText) findViewById(R.id.enterName);
        EditText enterAge = (EditText) findViewById(R.id.enterAge);
        CheckBox isEnrolled = (CheckBox) findViewById(R.id.checkBox);

        // The value will be default as empty string because for
        // the very first time when the app is opened, there is nothing to show
        name = sh.getString("name", "");
        age = sh.getInt("age", 0);
        enrolled = sh.getBoolean("enrolled",false);

        // We can then use the data
        enterName.setText(name);
        enterAge.setText(String.valueOf(age));
        isEnrolled.setChecked(enrolled);
    }

    public void save (View view)
    {

        EditText enterName = (EditText) findViewById(R.id.enterName);
        EditText enterAge = (EditText) findViewById(R.id.enterAge);
        CheckBox isEnrolled = (CheckBox) findViewById(R.id.checkBox);

        // Storing data into SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        // Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // Storing the key and its value as the data fetched from edittext
        myEdit.putString("name", enterName.getText().toString());
        myEdit.putInt("age", Integer.parseInt(enterAge.getText().toString()));
        myEdit.putBoolean("enrolled", isEnrolled.isChecked());

        // Once the changes have been made,
        // we need to commit to apply those changes made,
        // otherwise, it will throw an error
        myEdit.commit();
    }
}