package example.sunny.functioncheck;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private EditText mEmail, mPassword;
    private Button btnLogin;
    private CheckBox mCheckbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mEmail = (EditText) findViewById(R.id.field_email);
        //mPassword = (EditText) findViewById(R.id.field_password);
        btnLogin = (Button) findViewById(R.id.btn_enter);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Menu.class);
                startActivity(intent);
            }
        });
        //mCheckbox = (CheckBox) findViewById(R.id.remember_me);

        /*mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //mPreferences = getSharedPreferences("mydatabase", Context.MODE_PRIVATE);
        //store shared preferences
        mEditor = mPreferences.edit();

        checkSharedPreferences();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save the checkbox preference
                if(mCheckbox.isChecked()){
                    //set a checkbox when the application starts
                    mEditor.putString(getString(R.string.checkbox), "True");
                    mEditor.commit();

                    //save the email
                    String email = mEmail.getText().toString();
                    mEditor.putString(getString(R.string.email), email);
                    mEditor.commit();

                    //save the password
                    String password = mPassword.getText().toString();
                    mEditor.putString(getString(R.string.password), password);
                    mEditor.commit();

                    Intent intent = new Intent(MainActivity.this, Menu.class);
                    intent.putExtra("name",email);
                    startActivity(intent);
                } else {
                    //set a checkbox when the application starts
                    mEditor.putString(getString(R.string.checkbox), "False");
                    mEditor.commit();

                    //save the email
                    mEditor.putString(getString(R.string.email), "");
                    mEditor.commit();

                    //save the password
                    mEditor.putString(getString(R.string.password), "");
                    mEditor.commit();
                }
            }
        });*/


    }

    /**
     * check the shared preferences and set the time accordingly
     */
   /* private void checkSharedPreferences(){
        String checkbox = mPreferences.getString(getString(R.string.checkbox),"False");
        String email = mPreferences.getString(getString(R.string.email),"");
        String password = mPreferences.getString(getString(R.string.password), "");

        mEmail.setText(email);
        mPassword.setText(password);

        if(checkbox.equals("True")){
            mCheckbox.setChecked(true);
        } else {
            mCheckbox.setChecked(false);
        }
    }*/


}
