package com.example.paul.testsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        final Button btnYes;
        btnYes = (Button)findViewById(R.id.yesBtn);
        btnYes.setOnClickListener(btnYesClickListener);

        final Button btnPer;
        btnPer = (Button)findViewById(R.id.perhapsBtn);
        btnPer.setOnClickListener(btnperhapsClickListener);

        final Button btnNo;
        btnNo = (Button)findViewById(R.id.noBtn);
        btnNo.setOnClickListener(btnNoClickListener);

        final Button btnOut;
        btnOut = (Button)findViewById(R.id.outBtn);
        btnOut.setOnClickListener(btnOutClickListener);

        final TextView txtQuestM3A;
        txtQuestM3A = (TextView)findViewById(R.id.txtQuestM3A);

        final TextView txtScoreM3A;
        txtScoreM3A = (TextView)findViewById(R.id.txtScoreM3A);

    }

    private View.OnClickListener btnYesClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View c){
            Intent intent1 = new Intent(Main3Activity.this, MainActivity.class);

            startActivity(intent1);
        }
    };

    private View.OnClickListener btnperhapsClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View c){
            Intent intent1 = new Intent(Main3Activity.this, MainActivity.class);

            startActivity(intent1);
        }
    };

    private View.OnClickListener btnNoClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View c){
            Intent intent1 = new Intent(Main3Activity.this, MainActivity.class);

            startActivity(intent1);
        }
    };

    private View.OnClickListener btnOutClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View c){
            Intent intent1 = new Intent(Main3Activity.this, MainActivity.class);

            startActivity(intent1);
        }
    };
}

