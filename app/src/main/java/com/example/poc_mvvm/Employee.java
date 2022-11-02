package com.example.poc_mvvm;

public class Employee {
    private String name, phoneNo, address, birthday, email;

    public Employee(String name, String phoneNo, String address, String birthday, String email) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }
}
