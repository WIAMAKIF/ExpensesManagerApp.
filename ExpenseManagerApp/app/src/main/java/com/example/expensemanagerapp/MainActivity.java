package com.example.expensemanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button addButton;
    private TextView totalExpenses;
    private RecyclerView expensesRecycleView;
    private AdapterListExpensesActivity expensesAdapter;

    List<Expense> expenseList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton=findViewById(R.id.AddButton);
        totalExpenses=findViewById(R.id.TotalExpenses);
        expensesRecycleView=findViewById(R.id.Listexpenses);

        // Initialisation de la liste des dépenses
        expenseList = new ArrayList<>();
        expensesAdapter= new AdapterListExpensesActivity(expenseList);
        expensesRecycleView.setLayoutManager(new LinearLayoutManager(this));


        setupRecyclerView();
            Total();
            addBtn();

    }
    private void addBtn(){


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddExpenseActivity.class));
            }
        });
    }

    private void setupRecyclerView() {
        expensesRecycleView.setLayoutManager(new LinearLayoutManager(this));
        AdapterListExpensesActivity adapter = new AdapterListExpensesActivity(expenseList);
        expensesRecycleView.setAdapter(adapter);
    }

    private void Total(){

        double total =0;
        for(Expense expense : expenseList){
            total+=expense.getAmount();
        }
        totalExpenses.setText(String.format("Total des Dépenses: %.2f", total));


    }

    private void addExpense(Expense expense) {
        expenseList.add(expense);
        expensesAdapter.notifyDataSetChanged();
        Total();
    }



}