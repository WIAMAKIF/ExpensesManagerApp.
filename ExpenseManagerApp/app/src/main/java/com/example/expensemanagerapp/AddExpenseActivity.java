package com.example.expensemanagerapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;


public class AddExpenseActivity extends AppCompatActivity {
    private Button addingBtn;
    private EditText description;
    private EditText amount;
    private EditText category;
    private EditText date;
    private ProgressDialog mDialog;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        mDialog = new ProgressDialog(this);
        db = FirebaseFirestore.getInstance();
        add();

    }
    private void add(){
        description = findViewById(R.id.description);
        amount = findViewById(R.id.amount);
        category = findViewById(R.id.category);
        date = findViewById(R.id.date);
        addingBtn= findViewById(R.id.AddingBtn);




        addingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desc = description.getText().toString().trim();
                String am = amount.getText().toString().trim();
                String categ = category.getText().toString().trim();
                String dat = date.getText().toString().trim();
                

                mDialog.setMessage("Processing...");
                mDialog.show();

                Double amountValue= Double.parseDouble(am);

                if (desc.isEmpty() || am.isEmpty() || categ.isEmpty() || dat.isEmpty()) {
                    mDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Please fill all the fields!!", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Expense obj= new Expense(desc,amountValue,dat,categ);
                    db.collection("Expenses")
                            .add(obj)
                            .addOnSuccessListener(documentReference -> {
                                mDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Expnse added!",Toast.LENGTH_LONG).show();
                                clearFields();
                            })
                            .addOnFailureListener(e ->{
                                mDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Error adding expense",Toast.LENGTH_LONG).show();

                            });

                }
            }
        });

    }

    private void clearFields(){
        description.setText("");
        amount.setText("");
        category.setText("");
        date.setText("");

    }

}

