package example.sunny.functioncheck;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.print.PrintManager;
import android.support.annotation.Nullable;
import android.support.v4.print.PrintHelper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SuperviserItemActivity extends AppCompatActivity implements ImageAndTextContainer{

    private static final String TAG = "SuperviserItemActivity";

    //vars
    private Button btnPrint, btnSave;
    private ImageView mImageView;
    private Bitmap mCameraBitmap;
    private Spinner etZakazchik, etOtpravitel, etPoluchatel, etUpakovka;
    private EditText etKolichestvo, etVes, etVolume, etEmail;
    private TextView etNomer, tvData;
    private Button cbPovrezhden;
    private FirebaseAuth firebaseAuth;
    private String reciepeint, subject, messageText;
    private Session session = null;
    private ProgressDialog dialog = null;
    private Context context = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superviser);

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
//        etEmail = findViewById(R.id.field_email);
        cbPovrezhden = (Button) findViewById(R.id.txt_povrezhden);

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

        final ImageAndTextContainer imageAndTextContainer = this;

        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                PrintHelper photoPrinter = new PrintHelper(SuperviserItemActivity.this);
//                photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
//                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                        R.drawable.etc);
//                photoPrinter.printBitmap("test print", bitmap);

                // Create a PrintDocumentAdapter
                PrintShopPrintDocumentAdapter adapter = new PrintShopPrintDocumentAdapter(imageAndTextContainer, SuperviserItemActivity.this);
                // Get the print manager from the context
                PrintManager printManager = (PrintManager)SuperviserItemActivity.this.getSystemService(Context.PRINT_SERVICE);
                // And print the document
                printManager.print("PrintShop", adapter, null);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save data
            }
        });

        sendMessage();
    }

    public void sendMessage(){
        context = this;

        cbPovrezhden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reciepeint = etEmail.getText().toString();
                subject = "GLOTUS_Ваш Груз Поврежден";
                messageText = "Ваш груз поврежден";

                Properties properties = new Properties();
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.socketFactory.port", "465");
                properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.port", "465");

                session = Session.getDefaultInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("saadatsunnysmile@gmail.com", "GGGg5555");
                    }
                });

                dialog = ProgressDialog.show(context, "", "Sending message...", true);

                RetreiveFeedTask task = new RetreiveFeedTask();
                task.execute();
            }
        } );

    }

    @Override
    public String getText() {
        EditText textView = (EditText) findViewById(R.id.field_email);
        return textView.getText().toString();
    }

    @Override
    public Bitmap getImage() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.etc);
        return bitmap;
    }

    public class RetreiveFeedTask extends AsyncTask<String,Void,String> {

        @Override
        protected String  doInBackground(String... strings) {
            try{

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("saadatsunnysmile@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reciepeint));
                message.setSubject(subject);
                message.setContent(messageText, "text/html; charset=utf-8");

                Transport.send(message);

            } catch (MessagingException e){
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String aVoid) {
            dialog.dismiss();
            Toast.makeText(context, "Message sent", Toast.LENGTH_SHORT).show();
        }
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
