package example.sunny.functioncheck;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private EditText mEmail, mPassword;
    private Button btnLogin;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: started");

        mEmail = (EditText) findViewById(R.id.field_email);
        mPassword = (EditText) findViewById(R.id.field_password);
        btnLogin = (Button) findViewById(R.id.btn_enter);

        myRef = FirebaseDatabase.getInstance().getReference();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                HashMap<String, String> mMap = new HashMap<String, String>();
                mMap.put("Name", name);
                mMap.put("Password", password);

                myRef.push().setValue(mMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(MainActivity.this, Menu.class);
                        startActivity(intent);

                    }
                });
            }
        });

    }
}
