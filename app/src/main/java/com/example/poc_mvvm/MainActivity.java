package com.example.poc_mvvm;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private EditText name, phone, address, birthday, email;
    private Button submit, seeDetais;

    //database
    public static HashMap<String, Employee> hashMap = new HashMap<>();
    public static ArrayList<String> nameList = new ArrayList<>();
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.main_editText_name);
        phone = findViewById(R.id.main_editText_phone);
        address = findViewById(R.id.main_editText_address);
        birthday = findViewById(R.id.main_editText_bday);
        email = findViewById(R.id.main_editText_email);
        submit = findViewById(R.id.main_button_submit);
        seeDetais = findViewById(R.id.main_button_seeDetails);
    }

    @Override
    protected void onResume() {
        super.onResume();

        birthday.setOnClickListener(view -> {
            final Calendar c = Calendar.getInstance();
            int bYear = c.get(Calendar.YEAR); // current year
            int bMonth = c.get(Calendar.MONTH); // current month
            int bDay = c.get(Calendar.DAY_OF_MONTH); // current day
            // date picker dialog
            datePickerDialog = new DatePickerDialog(MainActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // set day of month , month and year value in the edit text
                            birthday.setText(dayOfMonth + "/"
                                    + (monthOfYear + 1) + "/" + year);

                        }
                    }, bYear, bMonth, bDay);
            datePickerDialog.show();
        });

        submit.setOnClickListener(view -> {
            if (!hashMap.isEmpty() && hashMap.containsKey(name.getText().toString())) {
                Toast.makeText(MainActivity.this, "Error: Duplicate Name", Toast.LENGTH_LONG).show();
                return;
            }

            if (notOneIsEmpty() && phoneIsValid(phone) && emailIsValid(email)
            ) {
                Employee employee = new Employee(name.getText().toString(),
                        phone.getText().toString(),
                        address.getText().toString(),
                        birthday.getText().toString(),
                        email.getText().toString()
                );

                Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_LONG).show();

                nameList.add(employee.getName());
                hashMap.put(employee.getName(), employee);
            }

        });

        seeDetais.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, DetailsActivity.class);
            startActivity(i);
            finish();
        });
    }

    private boolean emailIsValid(EditText emailField) {
        String emailId = emailField.getText().toString().replaceAll("//s", "");

        // Android offers the inbuilt patterns which the entered data from the EditText field needs to be compared with
        // In this case the entered data needs to compared with the EMAIL_ADDRESS, which is implemented same below
        if (Patterns.EMAIL_ADDRESS.matcher(emailId).matches()) {
            return true;
        } else {
            Toast.makeText(this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean phoneIsValid(EditText phoneField) {
        String phoneNo = phoneField.getText().toString().replaceAll("//s", "");
        if (phoneNo.length() == 10) {
            return true;
        } else {
            Toast.makeText(this, "Enter valid phone no !", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean notOneIsEmpty() {
        return isNotEmpty(name) &&
                isNotEmpty(phone) &&
                isNotEmpty(address) &&
                isNotEmpty(birthday) &&
                isNotEmpty(email);
    }

    private boolean isNotEmpty(EditText editText) {
        if (editText.getText().toString().replaceAll("\\s", "").isEmpty()) {
            Toast.makeText(MainActivity.this, editText.getHint() + " cannot be empty", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}