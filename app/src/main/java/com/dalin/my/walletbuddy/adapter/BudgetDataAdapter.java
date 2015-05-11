package com.dalin.my.walletbuddy.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import com.dalin.my.walletbuddy.R;
import com.dalin.my.walletbuddy.data.BudgetData;



public class BudgetDataAdapter
{
    private Context context;
    private List<BudgetData> budgetData;

    public BudgetDataAdapter(Context context, List<BudgetData> budgetData)
    {
        super(context. R.layout.item_list_view, budgetData);
        this.context = context;
        this.budgetData = budgetData;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_BudgetData, parent, false);

        TextView textView = (TextView)view.findViewById(R.id.StartingBudget);
        String textHolder = Double.toString(budgetData.getBudget());
        textView.setText(textHolder);

    }

}
