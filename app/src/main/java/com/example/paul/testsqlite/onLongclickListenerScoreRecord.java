package com.example.paul.testsqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class onLongclickListenerScoreRecord implements View.OnLongClickListener {

    Context context;
    String id;

    @Override
    public boolean onLongClick(final View v) {
        context = v.getContext();
        id = v.getTag().toString();

        final CharSequence[] items = {"Edit","Delete"};

        new AlertDialog.Builder(context).setTitle("Scores Records")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       switch (which){
                           case 0:
                               editItem(v);
                               break;
                           case 1 :
                               supprItem(v);

                               break;
                           default:
                               break;
                       }

                        dialog.dismiss();
                    }
                }).show();
        return false;
    }


public void editItem(View v){
    final Context context = v.getRootView().getContext();
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    final View formElementsView = inflater.inflate(R.layout.edit_player_form,null, true);

    final TextView txtId = (TextView) formElementsView.findViewById(R.id.txtId);
    final EditText txtname = (EditText) formElementsView.findViewById(R.id.editName);
    final EditText txtscore = (EditText) formElementsView.findViewById(R.id.editScore);
    final int ed = Integer.parseInt(id);

    txtId.setText("Id : " + id);

    new AlertDialog.Builder(context)
            .setView(formElementsView)
            .setTitle("update player")
            .setPositiveButton("Edit",
                    new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id ){
                            String dbtxtname = txtname.getText().toString();
                            int dbtxtscore = Integer.parseInt(txtscore.getText().toString());
                            ScoreData theScoreData= new ScoreData();
                            theScoreData.setName(dbtxtname);
                            theScoreData.setScore(dbtxtscore);
                            theScoreData.setIdScore(ed);

                            boolean createSucessful = new TableControllerPlayer(context).update(theScoreData);
                            if(createSucessful){
                                Toast.makeText(context,"Score was update. " + ed, Toast.LENGTH_SHORT).show();}
                            else{
                                Toast.makeText(context,"unable to edit player information.", Toast.LENGTH_SHORT).show();
                            }

                            dialog.cancel();
                        }

                    }).show();

}

public void supprItem (View v){
     final DatabaseManager databaseManager;



        final Context context = v.getRootView().getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.delet_element_form, null, true);


        final Spinner spinId = (Spinner) formElementsView.findViewById(R.id.spinDelet);

        databaseManager = new DatabaseManager(context);
        final List<Integer> idList = databaseManager.readId();

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(v.getRootView().getContext(), android.R.layout.simple_spinner_item, idList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinId.setAdapter(adapter);


        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Deleted player")
                .setPositiveButton("Deleted",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                final int j = Integer.parseInt(String.valueOf(idList.get(0)));
                                final int tid = spinId.getSelectedItemPosition()+j;

                                ScoreData theScoreData = new ScoreData();
                                theScoreData.setIdScore(tid);

                                boolean createSucessful = new TableControllerPlayer(context).delete(theScoreData);
                                if (createSucessful) {

                                    Toast.makeText(context, "Score was deleted." + tid, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "unable to deleted player information.", Toast.LENGTH_SHORT).show();
                                }
                                databaseManager.close();
                                dialog.cancel();
                            }

                        }).show();

    }

}

