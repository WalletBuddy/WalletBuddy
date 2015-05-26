package com.dalin.my.walletbuddy.adapter;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import com.dalin.my.walletbuddy.BudgetCategoryActivity;
import com.dalin.my.walletbuddy.R;
import com.dalin.my.walletbuddy.TransactionAddition;
import com.dalin.my.walletbuddy.data.CategoryData;
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>
{
    private List<CategoryData> categoryList;

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
    public void onBindViewHolder(CategoryViewHolder categoryViewHolder, int i)
    {
        CategoryData cd = categoryList.get(i);
        categoryViewHolder.vCategoryTitle.setText(cd.getCategory());



    }

    @Override
    public int getItemCount()
    {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        protected TextView vCategoryTitle;
        protected TextView addTransaction;
        public CategoryViewHolder(View v)
        {
            super(v);
            vCategoryTitle = (TextView)v.findViewById(R.id.categoryTitle);
            addTransaction = (TextView)v.findViewById(R.id.addDirectlyToCategory);
            addTransaction.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(v.getContext(), TransactionAddition.class);
            intent.putExtra("key", vCategoryTitle.getText().toString());
            v.getContext().startActivity(intent);
        }
    }


}
