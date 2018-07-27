package example.sunny.functioncheck;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.Map;

public class VoditelActivity extends AppCompatActivity {

    private static final int ERROR_DIALOG_REQUEST = 9001;

    private EditText searchRequest;
    private Button dostavleno;
    private ImageButton search;
    private ImageView imageView;

    static final int CAM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voditel);

        search = (ImageButton) findViewById(R.id.btnSearchVoditel);
        dostavleno = (Button) findViewById(R.id.btnGruzDostavlenVoditel);
        imageView = (ImageView) findViewById(R.id.imageViewVoditel);


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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }


    private void init(){
        Button BtnMap = (Button) findViewById(R.id.btnPokazatMarshrut);

        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VoditelActivity.this, MapVoditelActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean isServicesOk() {
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(VoditelActivity.this);

        if (available == ConnectionResult.SUCCESS){
            //everything is fine and user can make map requests
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can fix it
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(VoditelActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(VoditelActivity.this, "You can't make request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}
