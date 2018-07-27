package example.sunny.functioncheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MenedzherActivity extends AppCompatActivity {

    private Button btnSend, btnCamera;
    private EditText etNomer, etZakazchik, etOtpravitel, etPoluchatel,
            etKolichestvo, etVes, etVolume;
    private EditText etDocumentOsnovaniya, etUpakovka, etTransport,
            etSoprovoditelnyiDocument, etPochtaZakazchika, etMenedzher,
            etPodrazdelenie, etCommentariy;
    private TextView tvData;
    private CheckBox cbPovrezhden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menedzher);

        etNomer = (EditText) findViewById(R.id.field_nomer);
        tvData = (TextView) findViewById(R.id.field_data);
        etZakazchik = (EditText) findViewById(R.id.field_zakazchik);
        etOtpravitel = (EditText) findViewById(R.id.field_otpravitel);
        etPoluchatel = (EditText) findViewById(R.id.field_poluchatel);
        etKolichestvo = (EditText) findViewById(R.id.field_kolichestvo_fact);
        etVes = (EditText) findViewById(R.id.field_ves_fact);
        etVolume = (EditText) findViewById(R.id.field_volume);
        cbPovrezhden = (CheckBox) findViewById(R.id.txt_povrezhden);
        btnSend = (Button) findViewById(R.id.btnMenedzherPodtverdit);

        sohranitDannye();

    }

    public void sohranitDannye(){
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenedzherActivity.this, "Отправлено", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
