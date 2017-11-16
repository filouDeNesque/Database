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

/**
 * Created by Pierre on 16/11/2017.
 */

public class btnSuprConnectListener implements View.OnClickListener {
    private DatabaseManager databaseManager;

    public void onClick(View view) {

        final Context context = view.getRootView().getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.delet_element_form, null, true);


        final Spinner spinId = (Spinner) formElementsView.findViewById(R.id.spinDelet);

        databaseManager = new DatabaseManager(context);
        final List<Integer> idList = databaseManager.readId();

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(view.getRootView().getContext(), android.R.layout.simple_spinner_item, idList);
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

