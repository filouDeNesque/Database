package com.example.paul.testsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnAdd;
        btnAdd = (Button)findViewById(R.id.addBtn);
        btnAdd.setOnClickListener(new btnAddOnClickListener());

        final Button btnList;
        btnList = (Button)findViewById(R.id.listBtn);
        btnList.setOnClickListener(btnListConnectListener);
    }

    private View.OnClickListener btnListConnectListener = new View.OnClickListener(){
        @Override
        public void onClick(View c){
            Intent intent1 = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent1);
        }
    };

}

