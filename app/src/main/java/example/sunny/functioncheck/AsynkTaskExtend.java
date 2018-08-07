package example.sunny.functioncheck;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;

public class AsynkTaskExtend extends AsyncTask<Void, Void, String> {

    public AsynkTaskExtend(String json){

    }

    private StringBuilder jsonStringBuilder = new StringBuilder();
    @Override
    protected String doInBackground(Void... voids) {
        String urlString = "http://127.0.0.1:8000/api/Blog/?format=json";
        URL url = null;
        try {
            url = new URL(urlString);

            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();

            StringBuffer buffer = new StringBuffer();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = null;
            while ((line = br.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String json = jsonStringBuilder.toString();

        return json;
    }

}
