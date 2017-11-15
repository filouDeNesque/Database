package com.example.paul.testsqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


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
            .setTitle("Edit player")
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
}
