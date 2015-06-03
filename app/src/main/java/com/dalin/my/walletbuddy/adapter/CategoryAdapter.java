package com.dalin.my.walletbuddy.adapter;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import com.dalin.my.walletbuddy.TransactionListActivity;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import com.dalin.my.walletbuddy.BudgetCategoryActivity;
import com.dalin.my.walletbuddy.R;
import com.dalin.my.walletbuddy.TransactionAddition;
import com.dalin.my.walletbuddy.data.CategoryData;
import com.dalin.my.walletbuddy.data.CategoryExpenses;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>
{
    private List<CategoryData> categoryList;
    private List<CategoryExpenses> expensesList;
    private double total;
    private double totalCost;
    String holder;
    private List<String> categories = new ArrayList<String>();
    public CategoryAdapter(List<CategoryData> categoryList)
    {
        this.categoryList = categoryList;
    }


    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_card_layout, viewGroup, false);

        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder categoryViewHolder, int i)
    {


        CategoryData cd = categoryList.get(i);
        categoryViewHolder.vCategoryTitle.setText(cd.getCategory());
        //categoryViewHolder.vTotalExpenses.setText(Double.toString(cd.getTotalCost()));
        categoryViewHolder.vTotalExpenses.setText(String.format("$%.2f", cd.getTotalCost()));
        categoryViewHolder.vTotalTransactions.setText(Integer.toString(cd.getTotalTransactions()));
        String holder = categoryViewHolder.vCategoryTitle.getText().toString();
        Log.i("String", holder);


        /**
        CategoryExpenses cd = expensesList.get(i);
        holder = cd.getCategory();
        Log.i("First Read", holder);
        Log.i("Separator", "-------------");
        if(!categories.contains(holder))
        {
            categories.add(holder);
            String test = holder;
            categoryViewHolder.vCategoryTitle.setText(holder);
        }

        for(String s : categories)
        {
            Log.i("Iterating through my String arrayList Holder", s);
        }


        //Creating more than 1 card because i have 2 objects in my list that I passed over to the adapter. So even if i don't set the text it'll just make it a default category because I had a default layout in my XML
        **/
    }

    @Override
    public int getItemCount() {
        //Log.i("TEST", Double.toString(categoryList.size()));
        //return categoryList.size();

        return categoryList.size();
    }


    public class CategoryViewHolder extends RecyclerView.ViewHolder
    {
        protected TextView vCategoryTitle;
        protected TextView addTransaction;
        protected TextView vTotalExpenses;
        protected TextView vTotalTransactions;

        public CategoryViewHolder(View v)
        {
            super(v);
            vCategoryTitle = (TextView)v.findViewById(R.id.categoryTitle);
            vTotalExpenses = (TextView)v.findViewById(R.id.totalExpenses);
            vTotalTransactions = (TextView)v.findViewById(R.id.totalTransactions);
            addTransaction = (TextView)v.findViewById(R.id.addDirectlyToCategory);

            addTransaction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), TransactionAddition.class);
                    intent.putExtra("key", vCategoryTitle.getText().toString());
                    v.getContext().startActivity(intent);
                }
            });

            vCategoryTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), TransactionListActivity.class);
                    intent.putExtra("key", vCategoryTitle.getText().toString());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }


}
