package com.example.paul.testsqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Pierre on 13/11/2017.
 */

public class btnAddOnClickListener implements View.OnClickListener {

    public void onClick(View view){
        Context context = view.getRootView().getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.add_player_form, null, false);

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

                             dialog.cancel();
                            }
                        }).show();
    }
}
