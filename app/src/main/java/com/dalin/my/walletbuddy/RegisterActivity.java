package com.dalin.my.walletbuddy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.ParseObject;
import com.parse.SignUpCallback;


public class RegisterActivity extends ActionBarActivity {

    private EditText userNameView;
    private EditText userEmailView;
    private EditText userPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userNameView = (EditText)findViewById(R.id.registerName);
        userEmailView = (EditText)findViewById(R.id.registerEmail);
        userPasswordView = (EditText)findViewById(R.id.registerPassword);

        Button button1 = (Button)findViewById(R.id.Register);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                boolean validationError = false;
                final StringBuilder validationErrorMessage = new StringBuilder("Please ");
                if(isEmpty(userNameView)){
                    validationError = true;
                    validationErrorMessage.append("enter a username");
                }
                if(isEmpty(userEmailView))
                {
                    if(validationError) {
                       validationErrorMessage.append(", and ");
                    }
                    validationError = true;
                    validationErrorMessage.append("enter an email address");
                }
                if(isEmpty(userPasswordView)) {
                    if(validationError) {
                        validationErrorMessage.append(", and ");
                    }
                    validationError = true;
                    validationErrorMessage.append("enter a password");
                }
                validationErrorMessage.append(".");
                if(validationError)
                {
                    Toast.makeText(RegisterActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                final ProgressDialog dialogBox = new ProgressDialog(RegisterActivity.this);
                dialogBox.setTitle("Please wait...");
                dialogBox.setMessage("Signing up. Please wait.");
                dialogBox.show();


                ParseUser user = new ParseUser();
                user.setUsername(userNameView.getText().toString());
                user.setPassword(userPasswordView.getText().toString());
                user.setEmail(userEmailView.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        dialogBox.dismiss();
                        if(e != null)
                        {
                            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });

            }
        });


    }

    private boolean isEmpty(EditText aText)
    {
        if(aText.getText().toString().trim().length() > 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
