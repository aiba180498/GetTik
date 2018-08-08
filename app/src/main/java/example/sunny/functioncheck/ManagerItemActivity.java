package example.sunny.functioncheck;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class ManagerItemActivity extends AppCompatActivity{

    private static final String TAG = "ManagerItemActivity";

    private EditText etNomer, etZakazchik, etOtpravitel, etPoluchatel,
            etKolichestvo, etVes, etVolume;
    private EditText etDocumentOsnovaniya, etUpakovka, etTransport,
            etSoprovoditelnyiDocument, etPochtaZakazchika, etMenedzher,
            etPodrazdelenie, etCommentariy;
    private TextView tvData;
    private CheckBox cbPovrezhden;
    private Button btnSend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menedzher);
        setZayavka();

        btnSend = (Button) findViewById(R.id.btnMenedzherPodtverdit);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveZayavka();
            }
        });
    }

    private void setZayavka(){
        Log.d(TAG, "setZayavka: setting fields");

        tvData = findViewById(R.id.field_data);
        etNomer = findViewById(R.id.field_nomer);
        etNomer = (EditText) findViewById(R.id.field_nomer);
        tvData = (TextView) findViewById(R.id.field_data);
        etZakazchik = (EditText) findViewById(R.id.field_zakazchik);
        etOtpravitel = (EditText) findViewById(R.id.field_otpravitel);
        etPoluchatel = (EditText) findViewById(R.id.field_poluchatel);
        etKolichestvo = (EditText) findViewById(R.id.field_kolichestvo_fact);
        etVes = (EditText) findViewById(R.id.field_ves_fact);
        etVolume = (EditText) findViewById(R.id.field_volume);
        cbPovrezhden = (CheckBox) findViewById(R.id.txt_povrezhden);




        //get data
    }

    private void saveZayavka(){

        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead

        try {

            HttpPost request = new HttpPost("http://yoururl");
            StringEntity params =new StringEntity("details={\"name\":\"myname\",\"age\":\"20\"}");
            request.addHeader("content-type", "application/x-www-form-urlencoded");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            Log.d(TAG, response.getStatusLine().toString());
            finish();
            //handle response here...

        }catch (Exception ex) {

            //handle exception here

        }
    }
}
