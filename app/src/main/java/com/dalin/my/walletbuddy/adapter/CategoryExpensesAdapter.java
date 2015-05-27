package com.dalin.my.walletbuddy.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import com.dalin.my.walletbuddy.data.CategoryExpenses;
import com.dalin.my.walletbuddy.R;

public class CategoryExpensesAdapter extends RecyclerView.Adapter<CategoryExpensesAdapter.CategoryExpensesViewHolder>
{
    private List<CategoryExpenses> expensesList;

    public CategoryExpensesAdapter(List<CategoryExpenses> expensesList)
    {
        this.expensesList = expensesList;
    }

    @Override
    public CategoryExpensesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_card_layout, viewGroup, false);
        return new CategoryExpensesViewHolder(itemView);
        //Log.i("TEST", "THISIS A TEST");
    }

    @Override
    public void onBindViewHolder(CategoryExpensesViewHolder categoryExpensesViewHolder, int i)
    {
        String holder = categoryExpensesViewHolder.vCategoryTitle.getText().toString();


    }

    @Override
    public int getItemCount() {
        return expensesList.size();
    }


    public class CategoryExpensesViewHolder extends RecyclerView.ViewHolder
    {
        protected TextView vTotalExpenses;
        protected TextView vCategoryTitle;
        protected TextView vTotalTransactions;
        public CategoryExpensesViewHolder(View v)
        {
            super(v);
            vTotalExpenses = (TextView)v.findViewById(R.id.totalExpenses);
            vCategoryTitle = (TextView)v.findViewById(R.id.categoryTitle);
            vTotalTransactions = (TextView)v.findViewById(R.id.totalTransactions);
        }
    }
}
