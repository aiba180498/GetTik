package example.sunny.functioncheck;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class VoditelItemActivity extends AppCompatActivity{

    private static final String TAG = "VoditelItemActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private Button dostavleno;
    static final int CAM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_voditel);

        Log.d(TAG, "onCreate: adasda");

        dostavleno = (Button) findViewById(R.id.btnGruzDostavlenVoditel);
        dostavleno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_intent, CAM_REQUEST);
            }
        });

        if (isServicesOk()){
            init();
        }

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents");
        if(getIntent().hasExtra("no") && getIntent().hasExtra("da")){

            String date = getIntent().getStringExtra("da");
            String nomer = getIntent().getStringExtra("no");

            setZayavka(date, nomer);
        }
    }

    private void setZayavka(String mDate, String mNomer){
        Log.d(TAG, "setZayavka: setting fields");

        TextView etData = findViewById(R.id.id2);
        TextView etNomer = findViewById(R.id.id4);

        etData.setText(mDate);
        etNomer.setText(mNomer);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
        }
    }


    private void init(){
        Button BtnMap = (Button) findViewById(R.id.btnPokazatMarshrut);

        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VoditelItemActivity.this, MapVoditelActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean isServicesOk() {
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(VoditelItemActivity.this);

        if (available == ConnectionResult.SUCCESS){
            //everything is fine and user can make map requests
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can fix it
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(VoditelItemActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(VoditelItemActivity.this, "You can't make request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
