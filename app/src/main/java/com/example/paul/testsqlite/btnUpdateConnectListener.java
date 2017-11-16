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
import java.util.List;



public class btnUpdateConnectListener implements View.OnClickListener {

    private DatabaseManager databaseManager;

    public void onClick(View view){

        final Context context = view.getRootView().getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.update_player_form,null,true);

        final EditText txtname = (EditText) formElementsView.findViewById(R.id.editNameUp);
        final EditText txtscore = (EditText) formElementsView.findViewById(R.id.editScoreUp);
        final Spinner spinId = (Spinner) formElementsView.findViewById(R.id.spinId);

        databaseManager = new DatabaseManager(context);
        final List<Integer> idList = databaseManager.readId();

        ArrayAdapter<Integer> adapter =new ArrayAdapter<Integer>(view.getRootView().getContext(),android.R.layout.simple_spinner_item, idList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinId.setAdapter(adapter);



        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Update player")
                .setPositiveButton("Update",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                final int j = Integer.parseInt(String.valueOf(idList.get(0)));
                                final int tid = spinId.getSelectedItemPosition()+j;

                                String dbtxtname = txtname.getText().toString();
                                int dbtxtscore = Integer.parseInt(txtscore.getText().toString());
                                ScoreData theScoreData= new ScoreData();
                                theScoreData.setName(dbtxtname);
                                theScoreData.setScore(dbtxtscore);
                                theScoreData.setIdScore(tid);

                                boolean createSucessful = new TableControllerPlayer(context).update(theScoreData);
                                if(createSucessful){

                                    Toast.makeText(context,"Score was update."+tid, Toast.LENGTH_SHORT).show();}
                                else{
                                    Toast.makeText(context,"unable to update player information.", Toast.LENGTH_SHORT).show();
                                }
                                databaseManager.close();
                                dialog.cancel();
                            }

                        }).show();

    }


}
