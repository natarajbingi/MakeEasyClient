package com.makein.client.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.makein.client.R;
import com.makein.client.controller.Controller;
import com.makein.client.controller.Sessions;

public class LoginActivity extends AppCompatActivity {

    Context context;
    EditText userId, password;
    TextView goTosignUp;
    AppCompatButton login;
    AppCompatCheckBox remember;
    String userIdStr, passwordStr;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        dialog = new ProgressDialog(LoginActivity.this);
        dialog.setMessage("Doing something, please wait.");

        userId = findViewById(R.id.input_user);
        password = findViewById(R.id.input_password);
        login = findViewById(R.id.btn_login);
        remember = findViewById(R.id.remember_me);
        goTosignUp = findViewById(R.id.signup_text);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        goTosignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean Validation() {
        boolean valid = true;

        userIdStr = userId.getText().toString();
        passwordStr = password.getText().toString();

        if (userIdStr.isEmpty()) {

            userId.setError("enter a valid User id");
            valid = false;
        } else {
            userId.setError(null);
            if (remember.isChecked()) {
                Sessions.setUserObject(context, userIdStr, Controller.userId);
            }
        }

        if (passwordStr.isEmpty() || password.length() < 4 || password.length() > 10) {

            password.setError("enter between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
            if (remember.isChecked()) {
                Sessions.setUserObject(context, passwordStr, Controller.password);
            }
        }

        return valid;
    }

    public void login() {
        Log.d("TAG", "Login");

        if (!Validation()) {
            Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

            login.setEnabled(true);
            return;
        } else {
           // Login(userIdStr, passwordStr);
            if (remember.isChecked()) {
                Toast.makeText(context, "UserID: " + Sessions.getUserObject(context, Controller.userId)
                        + " Password: " + Sessions.getUserObject(context, Controller.password), Toast.LENGTH_LONG).show();
            }
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);

        }

        login.setEnabled(false);
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }
}
