package example.sunny.functioncheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    private Button btnMenedzherForm, btnVoditelForm, btnSuperviserForm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnMenedzherForm = (Button) findViewById(R.id.btnOpenMenedzherForm);
        btnSuperviserForm = (Button) findViewById(R.id.btnOpenSuperviserForm);
        btnVoditelForm = (Button) findViewById(R.id.btnOpenVoditelForm);

        btnVoditelForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, VoditelActivity.class);
                startActivity(intent);
            }
        });

        btnSuperviserForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, SuperviserActivity.class);
                startActivity(intent);
            }
        });

        btnMenedzherForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, MenedzherActivity.class);
                startActivity(intent);
            }
        });

    }

}
