package com.dalin.my.walletbuddy;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.TintEditText;
import android.view.Menu;
import android.view.MenuItem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;


import com.dalin.my.walletbuddy.data.CategoryExpenses;
import com.dalin.my.walletbuddy.data.CategoryData;


public class UpdateTransactionActivity extends ActionBarActivity
{
    private Button saveButton;
    private Button deleteButton;
    private EditText vendorName;
    private EditText cost;
    private TextView catName;
    boolean isUpdate;
    CategoryExpenses loadedData;
    private String deletedCost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_transaction);


        saveButton = (Button)findViewById(R.id.save);
        deleteButton = (Button)findViewById(R.id.deleteButton);
        vendorName = (EditText)findViewById(R.id.vendor);
        cost =(EditText)findViewById(R.id.transactionCost);
        catName = (TextView)findViewById(R.id.categoryHeader);

        final String objectId = getIntent().getStringExtra("parseObjectId");
        if(objectId != null)
        {
            isUpdate = true;
            ParseQuery<CategoryExpenses> query = ParseQuery.getQuery(CategoryExpenses.class);
            query.getInBackground(objectId, new GetCallback<CategoryExpenses>() {
                @Override
                public void done(CategoryExpenses categoryExpenses, ParseException e)
                {
                    loadedData = categoryExpenses;
                    vendorName.setText(categoryExpenses.getPlace());
                    catName.setText(categoryExpenses.getCategory());
                    cost.setText(Double.toString(categoryExpenses.getCost()));
                }
            });
            saveButton.setText("Update Transaction");
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpensesObjectFromForm();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteExpensesFromTable(catName.getText().toString(), objectId);
            }
        });

    }

    private void deleteExpensesFromTable(final String name, String objectId)
    {
        ParseQuery<CategoryExpenses> query = ParseQuery.getQuery(CategoryExpenses.class);
        query.getInBackground(objectId, new GetCallback<CategoryExpenses>() {
            @Override
            public void done(CategoryExpenses categoryExpenses, ParseException e)
            {
                deletedCost = Double.toString(categoryExpenses.getCost());

                ParseQuery<CategoryData> query1 = ParseQuery.getQuery(CategoryData.class);
                query1.whereEqualTo("Category", name);
                query1.getFirstInBackground(new GetCallback<CategoryData>() {
                    @Override
                    public void done(CategoryData categoryData, ParseException e) {
                        Double costHolder = categoryData.getTotalCost();
                        costHolder = costHolder - Double.parseDouble(deletedCost);
                        categoryData.setTotalCost(costHolder);
                        int totalTransactions = categoryData.getTotalTransactions();
                        totalTransactions = totalTransactions - 1;
                        categoryData.setTotalTransactions(totalTransactions);
                        categoryData.saveInBackground();
                        showDeletion(name);

                    }
                });
                categoryExpenses.deleteInBackground();
            }
        });


    }
    private void showDeletion(final String name)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateTransactionActivity.this);
        builder.setMessage("Entry successfully deleted").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent intent = new Intent(UpdateTransactionActivity.this, TransactionListActivity.class);
                intent.putExtra("key", name);
                startActivity(intent);
                finish();

            }
        })
        .setTitle("Transaction Deleted");
        builder.create().show();
    }
    private void addExpensesObjectFromForm()
    {
        String name = vendorName.getText().toString();
        String cat = catName.getText().toString();
        String total = cost.getText().toString();
        Double realTotal = Double.parseDouble(total);


        CategoryExpenses c = isUpdate ? loadedData : new CategoryExpenses();
        c.setCategory(cat);
        c.setPlace(name);
        c.setCost(realTotal);


        c.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                showConfirmation();
            }
        });

    }

    private void showConfirmation()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateTransactionActivity.this);
        builder.setMessage(isUpdate ?  "Transaction updated successfully." : "Transaction added").setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        })
        .setTitle(isUpdate ? "Updated Transaction" : "Add Transaction");
        builder.create().show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_transaction, menu);
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
