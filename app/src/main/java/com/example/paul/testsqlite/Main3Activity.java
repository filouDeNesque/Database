package com.example.paul.testsqlite;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {


    int nmbQuestion = 0;
    String repJoueurY = "yes";
    String repJoueurN = "no";
    int theScore ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Bundle pid = getIntent().getExtras();
        int id = pid.getInt("id");
        String sid = Integer.toString(id);





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

            Resources res = getResources();
            String[] question =res.getStringArray(R.array.quest) ;
            String quest = question[nmbQuestion];
            String[] reponse =res.getStringArray(R.array.rep1) ;
            String i = reponse[nmbQuestion];

            if ((nmbQuestion<reponse.length-1)) {
                nmbQuestion++;
                final TextView txtScoreM3A;
                txtScoreM3A = (TextView)findViewById(R.id.txtScoreM3A);
                String theStrScore = Integer.toString(theScore);
                txtScoreM3A.setText(theStrScore+" Pts");

                final TextView txtQuestM3A;
                txtQuestM3A = (TextView)findViewById(R.id.txtQuestM3A);
                txtQuestM3A.setText(quest);

                if (repJoueurY.equals(i)){
                    theScore += 1000;



                }else{
                    theScore += 0;
                }
            }
            else{
                final TextView txtQuestM3A;
                txtQuestM3A = (TextView)findViewById(R.id.txtQuestM3A);
                String theStrScore = Integer.toString(theScore);
                txtQuestM3A.setText("Votre Score : "+theStrScore);

            }




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
            Resources res = getResources();
            String[] question =res.getStringArray(R.array.quest) ;
            String quest = question[nmbQuestion];
            String[] reponse =res.getStringArray(R.array.rep1) ;
            String i = reponse[nmbQuestion];

            if ((nmbQuestion<reponse.length-1)) {
                nmbQuestion++;
                final TextView txtScoreM3A;
                txtScoreM3A = (TextView)findViewById(R.id.txtScoreM3A);
                String theStrScore = Integer.toString(theScore);
                txtScoreM3A.setText(theStrScore+" Pts");

                final TextView txtQuestM3A;
                txtQuestM3A = (TextView)findViewById(R.id.txtQuestM3A);
                txtQuestM3A.setText(quest);

                if (repJoueurN.equals(i)){
                    theScore += 1000;



                }else{
                    theScore += 0;
                }
            }
            else{
                final TextView txtQuestM3A;
                txtQuestM3A = (TextView)findViewById(R.id.txtQuestM3A);
                String theStrScore = Integer.toString(theScore);
                txtQuestM3A.setText("Votre Score : "+theStrScore);

            }


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

