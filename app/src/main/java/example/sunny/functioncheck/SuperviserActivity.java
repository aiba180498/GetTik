package example.sunny.functioncheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class SuperviserActivity extends AppCompatActivity {

    private static final String TAG = "SuperviserActivity";

    //vars
    private ArrayList<String> mDates = new ArrayList<>();
    private ArrayList<String> mNomera = new ArrayList<>();
    private Button createSuperviser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poisk_superviser);

        Log.d(TAG, "onCreate: started");

        initZayavki();

        createSuperviser = (Button) findViewById(R.id.btnDobavitSuperviser);

        createSuperviser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuperviserActivity.this, SuperviserItemActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initZayavki(){

        for (int i = 0; i < 20; i++){
            mDates.add((i + 4)+ ".12.18");
            mNomera.add("N" + (i + 1));
        }

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.recycler_superviser);
        SuperviserRecyclerAdapter mAdapter = new SuperviserRecyclerAdapter(SuperviserActivity.this, mDates, mNomera);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
