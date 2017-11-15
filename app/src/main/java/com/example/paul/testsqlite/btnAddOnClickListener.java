package com.example.paul.testsqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class btnAddOnClickListener implements View.OnClickListener {

    public void onClick(View view){

        final Context context = view.getRootView().getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.add_player_form,null, true);

        final EditText txtname = (EditText) formElementsView.findViewById(R.id.txtName);
        final EditText txtscore = (EditText) formElementsView.findViewById(R.id.txtscore);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Create player")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                            String dbtxtname = txtname.getText().toString();
                            int dbtxtscore = Integer.parseInt(txtscore.getText().toString());
                            ScoreData theScoreData= new ScoreData();
                            theScoreData.setName(dbtxtname);
                            theScoreData.setScore(dbtxtscore);

                                boolean createSucessful = new TableControllerPlayer(context).create(theScoreData);
                                if(createSucessful){

                                    Toast.makeText(context,"Score was saved.", Toast.LENGTH_SHORT).show();}
                                else{
                                    Toast.makeText(context,"unable to save player information.", Toast.LENGTH_SHORT).show();
                                }
                             dialog.cancel();
                            }

                        }).show();


    }


}
