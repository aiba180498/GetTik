package example.sunny.functioncheck;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.print.PrintManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.print.PrintHelper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;

public class SuperviserItemActivity extends AppCompatActivity implements ImageAndTextContainer{

    private static final String TAG = "SuperviserItemActivity";

    //vars
    private Button btnSend;
    private CheckBox cbPhoto;
    private ImageView mImageView;
    private Bitmap mCameraBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superviser);

        btnSend = (Button) findViewById(R.id.btnSuperviserSend);
        mImageView = (ImageView) findViewById(R.id.imageViewSuperviser);
        final ImageAndTextContainer imageAndTextContainer = this;

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a PrintDocumentAdapter
                PrintShopPrintDocumentAdapter adapter = new PrintShopPrintDocumentAdapter(imageAndTextContainer, SuperviserItemActivity.this);
                // Get the print manager from the context
                PrintManager printManager = (PrintManager)SuperviserItemActivity.this.getSystemService(Context.PRINT_SERVICE);
                // And print the document
                printManager.print("PrintShop", adapter, null);
            }
        });

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents");
        if(getIntent().hasExtra("nom") && getIntent().hasExtra("dat")){

            String date = getIntent().getStringExtra("dat");
            String nomer = getIntent().getStringExtra("nom");

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

    @Override
    public String getText() {
        TextView textView = (TextView) findViewById(R.id.field_zakazchik);
        return textView.getText().toString();
    }

    @Override
    public Bitmap getImage() {
        ImageView imageView = (ImageView) findViewById(R.id.imageViewSuperviser);
        Bitmap image = null;
        // Get the image
        if ((imageView.getDrawable()) != null) {
            // Send it to the print helper
            image = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        }
        return image;
    }
}

