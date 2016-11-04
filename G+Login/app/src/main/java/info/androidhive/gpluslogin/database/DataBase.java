package info.androidhive.gpluslogin.database;

/**
 * Created by Douglas on 04/10/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper{

    public DataBase(Context context){
        super(context, "AGENDA", null, 3);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( ScriptSQL.getCreateContato());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
