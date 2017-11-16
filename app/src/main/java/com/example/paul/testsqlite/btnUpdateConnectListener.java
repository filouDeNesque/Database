package com.example.paul.testsqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;


public class btnUpdateConnectListener implements View.OnClickListener {

    private DatabaseManager databaseManager;

    public void onClick(View view){

        final Context context = view.getRootView().getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.update_player_form,null,true);

        final EditText txtname = (EditText) formElementsView.findViewById(R.id.editName);
        final EditText txtscore = (EditText) formElementsView.findViewById(R.id.editScore);
        final Spinner spinId = (Spinner) formElementsView.findViewById(R.id.spinId);

        databaseManager = new DatabaseManager(context);
        List<Integer> idList = databaseManager.readId();

        ArrayAdapter<Integer> adapter =new ArrayAdapter<Integer>(view.getRootView().getContext(),android.R.layout.simple_spinner_item, idList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinId.setAdapter(adapter);


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
                                databaseManager.close();
                                dialog.cancel();
                            }

                        }).show();

    }


}
