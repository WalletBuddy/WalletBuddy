package com.dalin.my.walletbuddy;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.app.ProgressDialog;

import com.dalin.my.walletbuddy.data.CategoryData;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;


import com.dalin.my.walletbuddy.adapter.CategoryExpensesAdapter;
import com.dalin.my.walletbuddy.data.CategoryExpenses;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;
import android.content.Intent;




public class TransactionListActivity extends ActionBarActivity {

    private EditText editText;
    private ListView listView;
    private Button searchButton;
    private ProgressDialog progress;
    private CategoryExpensesAdapter expensesAdapter;
    private Intent categoryIntent;
    private String holder;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);

        categoryIntent = getIntent();
        holder = categoryIntent.getStringExtra("key");
        loadAllItems(null, holder);
        textView = (TextView)findViewById(R.id.categoryBudget);
        editText = (EditText)findViewById(R.id.keywordName);
        listView = (ListView)findViewById(R.id.expensesList);
        searchButton = (Button)findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = editText.getText().toString();
                loadAllItems(keyword, holder);
            }
        });




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TransactionListActivity.this, UpdateTransactionActivity.class);
                intent.putExtra("parseObjectId", expensesAdapter.getItem(position).getObjectId());
                startActivity(intent);
            }
        });


    }


    private void loadAllItems(String keyword, String input)
    {

        showProgressDialog();
        ParseQuery<CategoryData> query1 = ParseQuery.getQuery(CategoryData.class);
        query1.whereEqualTo("Category", input);
        query1.getFirstInBackground(new GetCallback<CategoryData>() {
            @Override
            public void done(CategoryData categoryData, ParseException e) {
                textView.setText(Double.toString(categoryData.getTotalCost()));
            }
        });

        final ParseQuery<CategoryExpenses> query = ParseQuery.getQuery(CategoryExpenses.class);
        if(keyword != null)
        {
            query.whereContains("Name", keyword);
        }
        else
        {
            query.whereEqualTo("Category", input);
        }

        query.findInBackground(new FindCallback<CategoryExpenses>()
        {
            @Override
            public void done(List<CategoryExpenses> categoryExpensesList, ParseException e) {
                hideProgressDialog();
                expensesAdapter = new CategoryExpensesAdapter(TransactionListActivity.this, categoryExpensesList);
                listView.setAdapter(expensesAdapter);
            }
        });


    }

    private void showProgressDialog()
    {
        progress = ProgressDialog.show(this, "Searching transactions ...", "Please wait...", true);

    }
    private void hideProgressDialog()
    {
        progress.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_transaction_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_add)
        {
            Intent intent = new Intent(TransactionListActivity.this, SetupActivity.class);
            startActivity(intent);
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
