package example.sunny.functioncheck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MenedzherActivity extends AppCompatActivity {

    private static final String TAG = "MenedzherActivity";

    //vars
    private ArrayList<Zayavka> mZayavkas = new ArrayList<>();
    private Button createManager;
    private FirebaseAuth firebaseAuth;
    private StringBuilder jsonStringBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poisk_manager);

        Log.d(TAG, "onCreate: started");

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(MenedzherActivity.this, MainActivity.class));
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String urlString = "http://127.0.0.1:8000/api/Blog/?format=json";
                    Log.d(TAG, "1");
                    URL url = new URL(urlString);
                    URLConnection con = url.openConnection();
                    InputStream is =con.getInputStream();
                    Log.d(TAG, "2");
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line = null;
                    Log.d(TAG, "3");
                    while ((line = br.readLine()) != null) {
                        jsonStringBuilder.append(line);
                    }
                    Log.d(TAG, "run: " + jsonStringBuilder.toString());
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();

        jsonArrayBuilder();
        initRecyclerView();
        createMenedzher();
    }

    private void jsonArrayBuilder(){
        try {
            String json = jsonStringBuilder.toString();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("zayavki");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonItem = (JSONObject) jsonArray.get(i);
                mZayavkas.add(new Zayavka(jsonItem.getString("date"), jsonItem.getString("nomer")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void createMenedzher(){
        createManager = (Button) findViewById(R.id.btnDobavitManager);

        createManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenedzherActivity.this, ManagerItemActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.recycler_manager);
        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(mZayavkas);
        mAdapter.setCallback(new RecyclerViewAdapter.Callback() {
            @Override
            public void onClickItem(int position) {
                Intent intent = new Intent(MenedzherActivity.this, ManagerItemActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
