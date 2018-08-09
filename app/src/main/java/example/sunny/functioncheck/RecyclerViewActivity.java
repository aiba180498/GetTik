package example.sunny.functioncheck;//package example.sunny.functioncheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.common.api.Api;

import example.sunny.functioncheck.Models.DummyModel;

import android.annotation.SuppressLint;

import android.app.ProgressDialog;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.widget.RelativeLayout;

import android.widget.Toast;


import org.json.JSONObject;

import java.util.List;


import example.sunny.functioncheck.Models.ServerApi;
import retrofit2.Call;

import retrofit2.Callback;

import retrofit2.Response;



public class RecyclerViewActivity extends AppCompatActivity {

    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    private  ServerApi serverapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        progressDoalog = new ProgressDialog(RecyclerViewActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
        serverapi = ApiUtils.getAPIService();

        Call<List<DummyModel>> call = serverapi.getDummyModels();
        call.enqueue(new Callback<List<DummyModel>>() {
            @Override
            public void onResponse(Call<List<DummyModel>> call, Response<List<DummyModel>> response) {
                if(response.isSuccessful()){
                    progressDoalog.dismiss();
                    generateDataList(response.body());
                } else {
                    progressDoalog.dismiss();
                    try{
                        Log.d("blabla2", response.raw().toString());
                    }catch (Exception e){
                        e.printStackTrace();
                        Log.d("blabla", e.getMessage());
                    }
                    Toast.makeText(getApplicationContext(),"FUCK !!!", Toast.LENGTH_SHORT).show();
                }
            }



            @Override
            public void onFailure(Call<List<DummyModel>> call, Throwable t) {
                progressDoalog.dismiss();
                t.printStackTrace();
                Toast.makeText(RecyclerViewActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


    }
    @SuppressLint("WrongViewCast")
    private void generateDataList(List<DummyModel> messages) {
        recyclerView = findViewById(R.id.recycler_superviser);
        adapter = new CustomAdapter(messages);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerViewActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}





