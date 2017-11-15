package com.example.paul.testsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private TextView scoresView;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        scoresView = (TextView) findViewById(R.id.scoresView);
        databaseManager = new DatabaseManager(this);

//        databaseManager.insertScore( "polox", 1000);
//        databaseManager.insertScore( "opppp", 500);
//        databaseManager.insertScore( "oujhbutf", 250);
//        databaseManager.insertScore( "hvhg", 1500);

        List<ScoreData> scores = databaseManager.readTop10();
        for (ScoreData score : scores){
            scoresView.append(score.toString()+ "\n\n");
        }
        databaseManager.close();
    }
}
