package com.example.paul.testsqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

/**
 * Created by Pierre on 13/11/2017.
 */

public class onLongclickListenerScoreRecord implements View.OnLongClickListener {

    Context context;
    String id;

    @Override
    public boolean onLongClick(View v) {
        context = v.getContext();
        id = v.getTag().toString();

        final CharSequence[] items = {"edit","delete"};

        new AlertDialog.Builder(context).setTitle("Scores Records")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
        return false;
    }


}
