package example.sunny.functioncheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText mEmail, mPassword;
    private Button btnLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: started");

        mEmail = (EditText) findViewById(R.id.field_email);
        mPassword = (EditText) findViewById(R.id.field_password);
        btnLogin = (Button) findViewById(R.id.btn_enter);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
            Log.d(TAG, "onCreate: user not null");
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: login");
                loginUser();
            }
        });




    }

    private void loginUser(){
        Log.d(TAG, "loginUser: login");
        final String name = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(name, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, "loginUser: entered");

                    if (name.equals("test@gmail.com")){
                        finish();
                        startActivity(new Intent(MainActivity.this, SuperviserActivity.class));
                    }
                    if (name.equals("voditel@gmail.com")){
                        finish();
                        startActivity(new Intent(MainActivity.this, VoditelActivity.class));
                    }
                    if (name.equals("manager@gmail.com")){
                        finish();
                        startActivity(new Intent(MainActivity.this, MenedzherActivity.class));
                    }

                    Toast.makeText(MainActivity.this, "Вы успешно вошли", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
