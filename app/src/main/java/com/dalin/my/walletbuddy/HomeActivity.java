package com.dalin.my.walletbuddy;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.dalin.my.walletbuddy.adapter.CategoryExpensesAdapter;
import com.dalin.my.walletbuddy.data.CategoryData;
import com.dalin.my.walletbuddy.data.CategoryExpenses;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.dalin.my.walletbuddy.data.BudgetData;

import java.util.List;

public class HomeActivity extends ActionBarActivity {

    BudgetData saveData;
    boolean isUpdate;
    private double totalCost;
    private double remainHolder;
    private double maxHolder;
    private ListView listView;
    private CategoryExpensesAdapter expensesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadAllItems();
        listView = (ListView)findViewById(R.id.recentList);

        final TextView initBudget = (TextView)findViewById(R.id.StartingBudget);

        TextView transactionList = (TextView)findViewById(R.id.RecentTransactions);
        transactionList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TransactionListActivity.class);
                startActivity(intent);
            }
        });

        final ParseQuery<CategoryData> query1 = ParseQuery.getQuery(CategoryData.class);
        query1.findInBackground(new FindCallback<CategoryData>()
        {
            @Override
            public void done(List<CategoryData> categoryDatas, ParseException e)
            {
                for (int i = 0; i < categoryDatas.size(); i++)
                {
                    totalCost += categoryDatas.get(i).getTotalCost();
                }

                ParseQuery<BudgetData> query2 = ParseQuery.getQuery(BudgetData.class);
                query2.whereEqualTo("user", ParseUser.getCurrentUser());
                query2.getFirstInBackground(new GetCallback<BudgetData>() {
                    @Override
                    public void done(BudgetData budgetData, ParseException e)
                    {
                        if(e == null) {
                            maxHolder = budgetData.getBudget();
                            remainHolder = maxHolder - totalCost;
                            budgetData.setRemaining(remainHolder);
                            budgetData.saveInBackground();
                            Log.i("SFOSWIfWSOEFWEF", Double.toString(totalCost));
                            Log.i("FFFFFFFFFF", Double.toString(remainHolder));
                            CircleDisplay cd = (CircleDisplay) findViewById(R.id.circleDisplay);
                            cd.setAnimDuration(2000);
                            cd.setFormatDigits(2);
                            cd.setValueWidthPercent(55f);
                            cd.setTextSize(18f);
                            cd.setDrawText(true);
                            cd.setDrawInnerCircle(true);
                            cd.setTouchEnabled(false);
                            cd.setUnit("");
                            cd.setStepSize(0.5f);
                            // cd.setCustomText(...); // sets a custom array of text
                            cd.showValue((float) totalCost, (float) maxHolder, true);
                            Log.i("---------------", Double.toString(maxHolder));
                            cd.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(HomeActivity.this, BudgetCategoryActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
            }
        });



    }

        //


        /**

        ParseQuery<BudgetData> query = ParseQuery.getQuery(BudgetData.class);
        query.findInBackground(new FindCallback<BudgetData>() {
            @Override
            public void done(List<BudgetData> budgetData, ParseException e) {
                if(e == null)
                {
                    if(budgetData.size() > 0) {
                        saveData = budgetData.get(0);
                        TextView startBudget = (TextView) findViewById(R.id.StartingBudget);
                        String budgetHolder = Double.toString(saveData.getBudget());
                        startBudget.setText(budgetHolder);
                    }

                }
                else
                {
                    //Do nothing
                }

            }
        });

        //
        **/

    private void loadAllItems()
    {
        ParseQuery<CategoryExpenses> query = ParseQuery.getQuery(CategoryExpenses.class);
        query.setLimit(3);
        query.findInBackground(new FindCallback<CategoryExpenses>() {
            @Override
            public void done(List<CategoryExpenses> categoryExpensesList, ParseException e) {
                expensesAdapter = new CategoryExpensesAdapter(HomeActivity.this, categoryExpensesList);
                listView.setAdapter(expensesAdapter);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        else if(id == R.id.action_Setup)
        {
            Intent intent = new Intent(HomeActivity.this, SetupActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
