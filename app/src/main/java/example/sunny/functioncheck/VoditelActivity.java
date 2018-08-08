package example.sunny.functioncheck;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class VoditelActivity extends AppCompatActivity {

    private static final String TAG = "VoditelActivity";

    //vars
    private ArrayList<String> mDates = new ArrayList<>();
    private ArrayList<String> mNomera = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poisk_voditel);

        Log.d(TAG, "onCreate: started");

        initZayavki();
    }

    private void initZayavki(){

        for (int i = 0; i < 20; i++){
            mDates.add((i + 2)+ ".09.18");
            mNomera.add("N" + (i + 1));
        }

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.recycler_voditel);
        ZayavkiAdapter mAdapter = new ZayavkiAdapter(VoditelActivity.this, mDates, mNomera);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}