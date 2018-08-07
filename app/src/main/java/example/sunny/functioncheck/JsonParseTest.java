package example.sunny.functioncheck;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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


public class JsonParseTest extends AppCompatActivity{

    StringBuilder jsonStringBuilder;
    ArrayList<Zayavka> mZayavkas = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            String urlString = "http://127.0.0.1:8000/api/Blog/?format=json";
            URL url = new URL(urlString);
            URLConnection con = url.openConnection();
            InputStream is =con.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null) {
                jsonStringBuilder.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

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
}