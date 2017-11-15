package com.example.paul.testsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

/**
 * Created by Pierre on 13/11/2017.
 */

public class TableControllerPlayer extends DatabaseManager {

    public TableControllerPlayer(Context context){
        super(context);
    }

    public boolean create(ScoreData scoreData){

        ContentValues values= new ContentValues();

        values.put("name", scoreData.getName());
        values.put("score", scoreData.getScore());
        values.put("when_", new Date().getTime());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("T_Scores", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count(){
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM T_Scores";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;
    }

    public boolean update(ScoreData scoreData){
        ContentValues values = new ContentValues();
        values.put("name", scoreData.getName());
        values.put("score", scoreData.getScore());
        values.put("when_", new Date().getTime());

        SQLiteDatabase db = this.getWritableDatabase();
        db.update("T_Scores",values,"idScore = ?", new String[]{String.valueOf(scoreData.getIdScore())});
        db.close();

        return true;
    }
}
