package com.dalin.mywallet.walletbuddy.adapter;
import com.dalin.mywallet.walletbuddy.data.BudgetData;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dalin.mywallet.walletbuddy.R;
public class BudgetDataAdapter extends ArrayAdapter<BudgetData>
{
    private Context context;
    private List<BudgetData> budgetDatas;

    public BudgetDataAdapter(Context context, List<BudgetData> objects)
    {
        super(context, R.layout.activity_home, objects);
        this.context = context;
        this.budgetDatas = objects;

    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.activity_home, null);
        }
        BudgetData budget = budgetDatas.get(position);
        TextView budgetHolder = (TextView)convertView.findViewById(R.id.StartingBudget);
        String initBudget = Double.toString(budget.getBudget());
        budgetHolder.setText(initBudget);

        return convertView;

    }


}
