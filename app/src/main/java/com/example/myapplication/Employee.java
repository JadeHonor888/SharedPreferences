package com.example.myapplication;

import java.lang.reflect.Array;
import java.util.List;

public class Employee
{
    private String name;
    private int age;
    private String mail;
    private Address address;
    private List<FamilyMember> family;

    public Employee(String fn, int a, String m, Address add, List<FamilyMember> f)
    {
        name = fn;
        age = a;
        mail = m;
        address = add;
        family = f;
    }



}
