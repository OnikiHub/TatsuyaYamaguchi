package jp.ac.asojuku.tatsuyayamaguchi;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper{
    public DBManager(Context context) { super(context,"Tatsuya",null,1);}

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS user(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,tel INTEGER,weight INTEGER,level INTEGER)");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE user");
        onCreate(db);
    }

    public SQLiteCursor selectWord(SQLiteDatabase sqLiteDatabase){
        String selectSql = "SELECT * FROM word ORDER BY _id";
        SQLiteCursor cursor = (SQLiteCursor)sqLiteDatabase.rawQuery(selectSql,null);
        return  cursor;
    }


}
