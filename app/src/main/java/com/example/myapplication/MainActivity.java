package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

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



        //THIS IS THE GSON CODE HERE//
        Gson gson = new Gson();

        ArrayList<FamilyMember> family = new ArrayList<>();
        family.add(new FamilyMember("Mother", 99));
        family.add(new FamilyMember("Father", 101));
        family.add(new FamilyMember("Sister", 57));
        family.add(new FamilyMember("Sister", 57));
        family.add(new FamilyMember("Son", 28));

        Employee greg = new Employee("Greg", 68, "greg.mail@gmail.com", new Address("United States", "New York"), family);

        String json = gson.toJson(greg);
        Employee greg2 = gson.fromJson(json, Employee.class);

        String json2 = gson.toJson(family);
        Type familyType = new TypeToken<ArrayList<FamilyMember>>(){}.getType();     //this is for when the app wants to deserialize it back into an arraylist of family members
        ArrayList<FamilyMember> family2 = gson.fromJson(json2, familyType);

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