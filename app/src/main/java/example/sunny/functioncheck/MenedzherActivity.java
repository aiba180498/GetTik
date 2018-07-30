package example.sunny.functioncheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenedzherActivity extends AppCompatActivity {

    private static final String TAG = "MenedzherActivity";

    //vars
    private ArrayList<String> mDates = new ArrayList<>();
    private ArrayList<String> mNomera = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poisk_manager);

        Log.d(TAG, "onCreate: started");

        initZayavki();
    }

    private void initZayavki(){

        for (int i = 0; i < 20; i++){
            mDates.add((i + 3)+ ".07.18");
            mNomera.add("N" + (i + 1));
        }

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.recycler_manager);
        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(MenedzherActivity.this, mDates, mNomera);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
