package example.sunny.functioncheck;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class SuperviserActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poisk_superviser);

        mListView = (ListView) findViewById(R.id.listView);

        final ArrayList<Zayavka> zayavki = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            zayavki.add(new Zayavka("10.07." + (i + 5), "N" + (i + 1)));
        }

        ZayavkiAdapter adapter = new ZayavkiAdapter(SuperviserActivity.this,R.layout.layout_list_item,zayavki);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SuperviserActivity.this, "You clicked" + zayavki.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
