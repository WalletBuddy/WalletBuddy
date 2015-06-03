package com.dalin.mywallet.walletbuddy.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.dalin.mywallet.walletbuddy.data.CategoryExpenses;
import com.dalin.mywallet.walletbuddy.R;

public class CategoryExpensesAdapter extends ArrayAdapter<CategoryExpenses>
{
    Context context;
    List<CategoryExpenses> expensesList;

    public CategoryExpensesAdapter(Context context, List<CategoryExpenses> expensesList)
    {
        super(context, R.layout.list_item_expenses, expensesList);
        this.context = context;
        this.expensesList = expensesList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_expenses, parent, false);

        TextView vendorName = (TextView)view.findViewById(R.id.itemVendor);
        vendorName.setText(expensesList.get(position).getPlace());

        TextView transactionDate = (TextView)view.findViewById(R.id.itemDate);
        transactionDate.setText(expensesList.get(position).getUpdatedAt().toString());

        TextView cost = (TextView)view.findViewById(R.id.itemCost);
        cost.setText(String.format("$%.2f", expensesList.get(position).getCost()));

        return view;
    }
}

