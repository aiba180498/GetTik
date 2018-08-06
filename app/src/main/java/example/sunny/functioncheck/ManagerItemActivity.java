package example.sunny.functioncheck;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ManagerItemActivity extends AppCompatActivity{

    private static final String TAG = "ManagerItemActivity";

    private ImageView iwZayavka;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menedzher);

        getIncomingIntent();

    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents");
        if(getIntent().hasExtra("nomer") && getIntent().hasExtra("data")){

            String date = getIntent().getStringExtra("data");
            String nomer = getIntent().getStringExtra("nomer");

            setZayavka(date, nomer);
        }
    }

    private void setZayavka(String mDate, String mNomer){
        Log.d(TAG, "setZayavka: setting fields");

        TextView etData = findViewById(R.id.field_data);
        EditText etNomer = findViewById(R.id.field_nomer);

        etData.setText(mDate);
        etNomer.setText(mNomer);
    }
}
