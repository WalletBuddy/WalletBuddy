package com.dalin.mywallet.walletbuddy;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import android.app.ProgressDialog;

import com.dalin.mywallet.walletbuddy.data.CategoryData;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;


import com.dalin.mywallet.walletbuddy.adapter.CategoryExpensesAdapter;
import com.dalin.mywallet.walletbuddy.data.CategoryExpenses;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    private ImageButton add;

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
        add = (ImageButton)findViewById(R.id.addCommand);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransactionListActivity.this, TransactionAddition.class);
                intent.putExtra("key", holder);
                startActivity(intent);
                finish();
            }
        });

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
                finish();

            }
        });





    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(this, BudgetCategoryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void loadAllItems(String keyword, String input)
    {

        showProgressDialog();
        ParseQuery<CategoryData> query1 = ParseQuery.getQuery(CategoryData.class);
        query1.whereEqualTo("Category", input);
        query1.getFirstInBackground(new GetCallback<CategoryData>() {
            @Override
            public void done(CategoryData categoryData, ParseException e)
            {
                //String holder = Double.toString(categoryData.getTotalCost());
                textView.setText(String.format("Total: $%.2f", categoryData.getTotalCost()));
                //textView.setText(Double.toString(categoryData.getTotalCost()));
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

        getMenuInflater().inflate(R.menu.menu_transaction_list, menu);
        return true;
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
