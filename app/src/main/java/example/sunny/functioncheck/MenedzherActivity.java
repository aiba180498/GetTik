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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenedzherActivity extends AppCompatActivity {

    private static final String TAG = "MenedzherActivity";

    //vars
    private ArrayList<Zayavka> mZayavkas = new ArrayList<>();
    private Button createManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poisk_manager);

        Log.d(TAG, "onCreate: started");

        initZayavki();

        createManager = (Button) findViewById(R.id.btnDobavitManager);

        createManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenedzherActivity.this, ManagerItemActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initZayavki() {


        /*String json = "{\"zayavki\":[" +
                "{\"date\": \"31.01.18\", \"nomer\": \"1\"}," +
                "{\"date\": \"31.02.18\", \"nomer\": \"2\"}," +
                "{\"date\": \"31.03.18\", \"nomer\": \"3\"}]}";
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("zayavki");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonItem = (JSONObject) jsonArray.get(i);
                mZayavkas.add(new Zayavka(jsonItem.getString("date"), jsonItem.getString("nomer")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.recycler_manager);
        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(mZayavkas);
        mAdapter.setCallback(new RecyclerViewAdapter.Callback() {
            @Override
            public void onClickItem(int position) {
                Toast.makeText(MenedzherActivity.this, mZayavkas.get(position).toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MenedzherActivity.this, ManagerItemActivity.class);
                intent.putExtra("data", mZayavkas.get(position));
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
