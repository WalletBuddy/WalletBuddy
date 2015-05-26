package com.dalin.my.walletbuddy;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import com.dalin.my.walletbuddy.data.CategoryExpenses;
import com.parse.LogInCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;




public class TransactionAddition extends ActionBarActivity
{
    private TextView categoryName;
    private TextView categoryNameHolder;
    private EditText merchantName;
    private EditText transactionCost;
    private Button transactionSave;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_addition);

        Intent categoryIntent = getIntent();
        String holder = categoryIntent.getStringExtra("key");


        categoryName = (TextView)findViewById(R.id.CategoryName);
        //String holder = categoryNameHolder.getText().toString();
        categoryName.setText(holder);

        merchantName = (EditText)findViewById(R.id.MerchantName);

        transactionCost = (EditText)findViewById(R.id.totalCost);

        transactionSave = (Button)findViewById(R.id.saveTransaction);
        transactionSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                CategoryExpenses data = new CategoryExpenses();
                data.setACL(new ParseACL(ParseUser.getCurrentUser()));
                data.setUser(ParseUser.getCurrentUser());
                data.setCategory(categoryName.getText().toString());
                data.setCost(Double.parseDouble(transactionCost.getText().toString()));
                data.setPlace(merchantName.getText().toString());
                data.saveInBackground();
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transaction_addition, menu);
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
