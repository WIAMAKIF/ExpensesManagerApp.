package com.example.expensemanagerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterListExpensesActivity extends RecyclerView.Adapter<AdapterListExpensesActivity.ExpenseViewHolder> {

    private List<Expense> expenseList;

    // Constructeur
    public AdapterListExpensesActivity(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Crée une vue pour chaque élément
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_list_expenses, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        // Lier les données de chaque élément à la vue
        Expense expense = expenseList.get(position);
        holder.expenseName.setText(expense.getDescription());
        holder.expenseAmount.setText(String.format("%.2f", expense.getAmount()));
        holder.expenseCategory.setText(expense.getCategory());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        holder.expenseDate.setText(dateFormat.format(expense.getDate()));
    }

    @Override
    public int getItemCount() {
        return expenseList.size(); // Retourne le nombre d'éléments dans la liste
    }

    // Classe interne pour représenter un élément de vue
    public static class ExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView expenseName, expenseAmount, expenseCategory, expenseDate;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            // Lier les vues
            expenseName = itemView.findViewById(R.id.expenseDescription);
            expenseAmount = itemView.findViewById(R.id.expenseAmount);
            expenseCategory = itemView.findViewById(R.id.expenseCategory);
            expenseDate = itemView.findViewById(R.id.expenseDate);
        }
    }
}
