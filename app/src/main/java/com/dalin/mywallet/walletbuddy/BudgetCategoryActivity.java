package com.dalin.mywallet.walletbuddy;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dalin.mywallet.walletbuddy.data.CategoryExpenses;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.dalin.mywallet.walletbuddy.data.CategoryData;
import com.dalin.mywallet.walletbuddy.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;


public class BudgetCategoryActivity extends ActionBarActivity {

    private CategoryAdapter adapter;
    private RecyclerView recList;
    private TextView titleCategory;
    private TextView addTransaction;
    //private CategoryExpensesAdapter adapter2;
    private ImageButton addCategory;
    private List<CategoryData> array;
    private List<CategoryExpenses> array2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_category);





        recList = (RecyclerView)findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);




        ParseQuery<CategoryData> query = ParseQuery.getQuery(CategoryData.class);
        query.findInBackground(new FindCallback<CategoryData>() {
            @Override
            public void done(List<CategoryData> categoryDatas, ParseException e) {
                array = new ArrayList<CategoryData>(categoryDatas);
                adapter = new CategoryAdapter(array);
                recList.setAdapter(adapter);
            }
        });


        addCategory = (ImageButton)findViewById(R.id.addCommand);
        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(BudgetCategoryActivity.this, SetupActivity.class);
                startActivity(intent);
            }
        });


         /**

        ParseQuery<CategoryExpenses> query = ParseQuery.getQuery(CategoryExpenses.class);
        query.findInBackground(new FindCallback<CategoryExpenses>() {
            @Override
            public void done(List<CategoryExpenses> categoryExpensesList, ParseException e) {
                array2 = new ArrayList<CategoryExpenses>(categoryExpensesList);
                adapter = new CategoryAdapter(categoryExpensesList);
                recList.setAdapter(adapter);
            }
        });
        **/

        /**

        ParseQuery<CategoryExpenses> query2 = ParseQuery.getQuery(CategoryExpenses.class);
        query2.findInBackground(new FindCallback<CategoryExpenses>() {
            @Override
            public void done(List<CategoryExpenses> categoryExpenseses, ParseException e) {
                array2 = new ArrayList<CategoryExpenses>(categoryExpenseses);
                //adapter2 = new CategoryExpensesAdapter(array2);
                //recList.setAdapter(adapter2);
            }
        });





        adapter = new CategoryAdapter(array, array2);
        recList.setAdapter(adapter);
        **/
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_budget_category, menu);
        return true;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
