package example.sunny.functioncheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenedzherActivity extends AppCompatActivity {

    private static final String TAG = "MenedzherActivity";

    //vars
    private List<Zayavka> mZayavkas;
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

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    String urlString = "http://192.168.43.155:8000/api/Blog/?format=json";
//                    Log.d(TAG, "1");
//                    URL url = new URL(urlString);
//                    URLConnection con = url.openConnection();
//                    InputStream is =con.getInputStream();
//                    Log.d(TAG, "2");
//                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
//                    String line = null;
//                    Log.d(TAG, "3");
//                    while ((line = br.readLine()) != null) {
//                        jsonStringBuilder.append(line);
//                    }
//                    Log.d(TAG, "run: " + jsonStringBuilder.toString());
//                } catch (IOException e) {
//                    Log.e(TAG, e.getMessage());
//                    e.printStackTrace();
//                }
//            }
//        }).start();

//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("http://192.168.43.155:8000/api/Blog/")
//                .get()
//                .addHeader("Cache-Control", "no-cache")
//                .addHeader("Postman-Token", "f04a3d72-0496-465e-8db1-4ec282370f2e")
//                .build();
//
//        Log.d(TAG, "onCreate: request is built");
//
//        client.newCall(request).enqueue(new Callback(){
//            @Override
//            public void onFailure(Request request, IOException e) {
//                Log.d("HTTPRequestTest", "Shit happened");
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                if(!response.isSuccessful()){
//                    throw new IOException("Blablabla" + response);
//                }else{
//                    Log.d("HTTPRequestTest", "Request has been completed successfully");
//                    Log.d("HTTPRequestTest", response.body().string());
//
//
//
//
//                }
//            }
//        });

        jsonArrayBuilder();
        initRecyclerView();
        createMenedzher();
    }

    private void jsonArrayBuilder(){
        mZayavkas = new ArrayList<>();
        mZayavkas.add(new Zayavka("J", "df"));
        mZayavkas.add(new Zayavka("sfds", "fsd"));

//        try {
//            //String json = jsonStringBuilder.toString();
//
//             String json = "{\"zayavki\":\"{\"date\": \"31.01.18\", \"nomer\": \"1\"}," +
//                        "{\"date\": \"31.02.18\", \"nomer\": \"2\"}," +
//                     "{\"date\": \"31.03.18\", \"nomer\": \"3\"}]}";
//
//            JSONObject jsonObject = new JSONObject(json);
//            JSONArray jsonArray = jsonObject.getJSONArray("zayavki");
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonItem = (JSONObject) jsonArray.get(i);
//                mZayavkas.add(new Zayavka(jsonItem.getString("date"), jsonItem.getString("nomer")));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
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
