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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;

public class SuperviserItemActivity extends AppCompatActivity{

    private static final String TAG = "SuperviserItemActivity";

    //vars
    private Button btnPrint, btnSave;
    private ImageView mImageView;
    private Bitmap mCameraBitmap;
    private Spinner etZakazchik, etOtpravitel, etPoluchatel, etUpakovka;
    private EditText etKolichestvo, etVes, etVolume;
    private TextView etNomer, tvData;
    private CheckBox cbPovrezhden;
    private FirebaseAuth firebaseAuth;

    private DatabaseReference reference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superviser);

        reference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        //getIncomingIntent();

        btnPrint = (Button) findViewById(R.id.btnSuperviserSend);
        btnSave = (Button) findViewById(R.id.btnSuperviserSave);
        mImageView = (ImageView) findViewById(R.id.imageViewSuperviser);
        etNomer = (TextView) findViewById(R.id.field_nomer);
        tvData = (TextView) findViewById(R.id.field_data);
        etZakazchik = (Spinner) findViewById(R.id.field_zakazchik);
        etOtpravitel = (Spinner) findViewById(R.id.field_otpravitel);
        etPoluchatel = (Spinner) findViewById(R.id.field_poluchatel);
        etUpakovka = (Spinner) findViewById(R.id.field_upakovka);
        etKolichestvo = (EditText) findViewById(R.id.field_kolichestvo_fact);
        etVes = (EditText) findViewById(R.id.field_ves_fact);
        etVolume = (EditText) findViewById(R.id.field_volume);
        cbPovrezhden = (CheckBox) findViewById(R.id.txt_povrezhden);

        isStringFilled(etKolichestvo);
        isStringFilled(etVes);
        isStringFilled(etVolume);

        ArrayAdapter<String> zAdapter = new ArrayAdapter<String>(
                SuperviserItemActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.zakazchik));

        zAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etZakazchik.setAdapter(zAdapter);

        ArrayAdapter<String> oAdapter = new ArrayAdapter<String>(
                SuperviserItemActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.otpravitel));

        oAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etOtpravitel.setAdapter(oAdapter);

        ArrayAdapter<String> pAdapter = new ArrayAdapter<String>(
                SuperviserItemActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.poluchatel));

        pAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etPoluchatel.setAdapter(pAdapter);

        ArrayAdapter<String> uAdapter = new ArrayAdapter<String>(
                SuperviserItemActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.upakovka));

        uAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etUpakovka.setAdapter(uAdapter);

        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrintHelper photoPrinter = new PrintHelper(SuperviserItemActivity.this);
                photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                        R.drawable.etc);
                photoPrinter.printBitmap("test print", bitmap);

                // Create a PrintDocumentAdapter
                /*PrintShopPrintDocumentAdapter adapter = new PrintShopPrintDocumentAdapter(imageAndTextContainer, SuperviserItemActivity.this);
                // Get the print manager from the context
                PrintManager printManager = (PrintManager)SuperviserItemActivity.this.getSystemService(Context.PRINT_SERVICE);
                // And print the document
                printManager.print("PrintShop", adapter, null);*/
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInfo();
            }
        });
    }

    public void saveInfo(){
        String nomer = "2".trim();
        String data = "11.05.18".trim();
        String name = "gruz".trim();
        String zakaz = etZakazchik.getBaseline()+"".trim();
        String otprav = etOtpravitel.getBaseline() +"".trim();
        String poluch = etPoluchatel.getBaseline()+"".trim();
        String upakovka = etUpakovka.getBaseline()+"".trim();
        int kolvo = (int) etKolichestvo.getText().length();
        int ves = etVes.getText().length();
        int obem = etVolume.getText().length();
        String povrezh = "Да";

        Zayavka newZayavka = new Zayavka(nomer, data);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        reference.child(user.getUid()).setValue(newZayavka);

        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

    }

    public boolean isStringFilled(EditText editText){

        String string = editText.getText().toString();

        if(TextUtils.isEmpty(string)) {
            editText.setError("Пожалуйста, заполните это поле");
            return false;
        } else {
            return true;
        }
    }

    /*private void getIncomingIntent(){
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
    }*/

}
