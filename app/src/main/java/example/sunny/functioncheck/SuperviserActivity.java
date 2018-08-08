package example.sunny.functioncheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SuperviserActivity extends AppCompatActivity {

    private static final String TAG = "SuperviserActivity";

    //vars
    private ArrayList<Zayavka> mZayavkas = new ArrayList<>();
    private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poisk_superviser);

        Log.d(TAG, "onCreate: started");

        jsonArrayBuilder();
        initRecyclerView();
        createSuperviser();
    }

    private void createSuperviser(){
        create = (Button) findViewById(R.id.btnDobavitSuperviser);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuperviserActivity.this, SuperviserItemActivity.class);
                startActivity(intent);
            }
        });
    }

    private void jsonArrayBuilder(){
        try {
            //String json = jsonStringBuilder.toString();

            Log.d(TAG, "jsonArrayBuilder: started");

            String json = "{\"zayavki\":\"{\"date\": \"31.01.18\", \"nomer\": \"1\"}," +
                    "{\"date\": \"31.02.18\", \"nomer\": \"2\"}," +
                    "{\"date\": \"31.03.18\", \"nomer\": \"3\"}]}";

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("zayavki");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonItem = (JSONObject) jsonArray.get(i);
                mZayavkas.add(new Zayavka(jsonItem.getString("date"), jsonItem.getString("nomer")));
                Log.d(TAG, "jsonArrayBuilder: " + mZayavkas.get(i).toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.recycler_superviser);
        SuperviserRecyclerAdapter mAdapter = new SuperviserRecyclerAdapter(mZayavkas);
        mAdapter.setCallback(new SuperviserRecyclerAdapter.Callback() {
            @Override
            public void onClickItem(int position) {
                Intent intent = new Intent(SuperviserActivity.this, SuperviserItemActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
