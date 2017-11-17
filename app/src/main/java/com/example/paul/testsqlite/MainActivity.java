package com.example.paul.testsqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
            final DatabaseManager databaseManager;
            final Context context = c.getRootView().getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            final View formElementsView = inflater.inflate(R.layout.choose_player_form,null,true);

            final Spinner spinId = (Spinner) formElementsView.findViewById(R.id.spinChoose);

            databaseManager = new DatabaseManager(context);
            final List<Integer> idList = databaseManager.readId();

            ArrayAdapter<Integer> adapter =new ArrayAdapter<Integer>(c.getRootView().getContext(),android.R.layout.simple_spinner_item, idList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinId.setAdapter(adapter);


            new AlertDialog.Builder(context)
                    .setView(formElementsView)
                    .setTitle("Choose player")
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int id){

                                    final int tid = spinId.getSelectedItemPosition();
                                    final int j = Integer.parseInt(String.valueOf(idList.get(tid)));

                                    ScoreData theScoreData= new ScoreData();
                                    theScoreData.setIdScore(j);

                                    countRecords();
                                    Toast.makeText(context,"Player was choose "+j, Toast.LENGTH_SHORT).show();
                                    Intent intent1 = new Intent(MainActivity.this, Main3Activity.class);
                                    intent1.putExtra("id",j);
                                    startActivity(intent1);
                                    databaseManager.close();
                                    dialog.cancel();
                                }

                            }).show();



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

