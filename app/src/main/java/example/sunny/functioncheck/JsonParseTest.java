package example.sunny.functioncheck;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class JsonParseTest {

    public class MainActivity extends AppCompatActivity {

        private static final String TAG = "MainActivity";

        private ArrayList<Zayavka> mZayavkas = new ArrayList<>();


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Log.d(TAG, "onCreate: started");
            new ParseTask().execute();
        }

        private class ParseTask extends AsyncTask<Void, Void, String> {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String resultJson = "";

            @Override
            protected String doInBackground(Void... params) {
                try {

                    String site_url_json = "http://127.0.0.1:8000/api/Blog/?format=json";
                    URL url = new URL(site_url_json);

                    urlConnection = (HttpURLConnection) url.openConnection();

                    Log.d(TAG, "doInBackground: connection started");

                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();

                    Log.d(TAG, "doInBackground: connected");

                    InputStream inputStream = urlConnection.getInputStream();
                    StringBuffer buffer = new StringBuffer();

                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    resultJson = buffer.toString();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "doInBackground: result " + resultJson.toString());

                return resultJson;

            }


            protected void onPostExecute(String strJson) {
                super.onPostExecute(strJson);

                try {
                    JSONObject jsonObject = new JSONObject(strJson);
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

    }
}
