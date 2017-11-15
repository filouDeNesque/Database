package com.example.paul.testsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;


public class btnUpdateConnectListener implements View.OnClickListener {

    public void onClick(View view){

        final Context context = view.getRootView().getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.update_player_form,null,true);
    }
}
