package com.example.poc_mvvm;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.EmployeeViewHolder> {
    private HashMap<String, Employee> hashMap;
    private ArrayList<String> nameList;
    private Context context;

    public RecyclerAdapter(HashMap<String, Employee> hashMap, ArrayList<String> nameList, Context context) {
        this.hashMap = hashMap;
        this.nameList = nameList;
        this.context = context;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_design, viewGroup, false);

        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder employeeViewHolder, int position) {
        Employee employee = hashMap.get(nameList.get(position));

        employeeViewHolder.sno.setText(position + "");
        employeeViewHolder.name.setText(employee.getName());

        employeeViewHolder.plus.setOnClickListener(view -> {
            //Opens a dialog that shows all details of that employee wrt name

            showDialog(employee);
        });
    }

    private void showDialog(Employee employee) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage("Name: "+ employee.getName() + "\n" +
                "Phone: " + employee.getPhoneNo() + "\n" +
                "Address: " + employee.getAddress() + "\n" +
                "Birthday: " + employee.getBirthday()+ "\n"+
                "Email: " + employee.getEmail() + "\n");
        dialog.show();
        dialog.create();
    }


    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private TextView sno;
        private TextView name;
        private Button plus;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            sno = itemView.findViewById(R.id.card_text_sno);
            name = itemView.findViewById(R.id.card_text_name);
            plus = itemView.findViewById(R.id.card_button_plus);
        }

    }
}
