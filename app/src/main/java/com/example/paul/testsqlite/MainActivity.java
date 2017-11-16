package com.example.paul.testsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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

        final Button btnPlay;
        btnPlay = (Button)findViewById(R.id.playbtn);
        btnPlay.setOnClickListener(btnPlayConnectListener);

        final Button btnUpdate;
        btnUpdate = (Button)findViewById(R.id.updateBtn);
        btnUpdate.setOnClickListener(new btnUpdateConnectListener());

        final Button btnDelet;
        btnDelet = (Button)findViewById(R.id.supprBtn);
        btnDelet.setOnClickListener(new btnSuprConnectListener());


        countRecords();
        readRecords();
    }



    private View.OnClickListener btnPlayConnectListener = new View.OnClickListener(){
        @Override
        public void onClick(View c){
            Intent intent1 = new Intent(MainActivity.this, Main3Activity.class);
            countRecords();
            startActivity(intent1);
        }
    };

    private View.OnClickListener btnListConnectListener = new View.OnClickListener(){
        @Override
        public void onClick(View c){
            Intent intent1 = new Intent(MainActivity.this, Main2Activity.class);
            countRecords();
            startActivity(intent1);
        }
    };

    public void countRecords(){
        int recordCount = new TableControllerPlayer(this).count();
        TextView viewRecords;
        viewRecords = (TextView) findViewById(R.id.textViewRecordCount);
        viewRecords.setText (recordCount + " records found");
    }

    public void readRecords(){

        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        List<ScoreData> scores = new DatabaseManager(this).readTop10();

        if (scores.size()>0){
            for (ScoreData score : scores){
                int id = score.getIdScore();
                String name = score.getName();
                int point = score.getScore();

                String TextViewContents = id + " - " +name + " - " + point;

                TextView textViewpointplayer = new TextView(this);
                textViewpointplayer.setGravity(17);
                textViewpointplayer.setPadding(0,10,0,10);
                textViewpointplayer.setText(TextViewContents);
                textViewpointplayer.setTag(Integer.toString(id));
                textViewpointplayer.setOnLongClickListener(new onLongclickListenerScoreRecord());

                linearLayoutRecords.addView(textViewpointplayer);
            }
        }
        else {
            TextView locationItem =new TextView(this);
            locationItem.setPadding(8,8,8,8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }
    }



}

