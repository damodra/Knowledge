package com.example.knowledge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

public class HomeTechActivity extends AppCompatActivity {
    private Toolbar mtoolbar;

    TextView tv123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_tech);


        mtoolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Teacher Home");


//        tv123=findViewById(R.id.tv_tech_HOME);
//        Bundle b=getIntent().getExtras();
//        String name=b.getString("name");
//        tv123.setText("Mr./Ms "+name);

    }
}
