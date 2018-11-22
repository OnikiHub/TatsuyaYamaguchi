package jp.ac.asojuku.tatsuyayamaguchi;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.IntegerRes;

public class DBManager extends SQLiteOpenHelper{
    public DBManager(Context context) { super(context,"Tatsuya",null,1);}

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS user(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,tel INTEGER,weight INTEGER,level INTEGER,anke1 TEXT,anke2 TEXT,anke3 TEXT)");

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

    public  void  weighttouroku(SQLiteDatabase sqLiteDatabase, Integer inputweight){
        String sql = "INSERT INTO weight VALUEs(?)";
        sqLiteDatabase.execSQL(sql,new Integer[]{inputweight});
    }
    public  void  anke1touroku(SQLiteDatabase sqLiteDatabase, String inputanke1){
        String sql = "INSERT INTO weight VALUEs(?)";
        sqLiteDatabase.execSQL(sql,new String[]{inputanke1});
    }
    public  void  anke2touroku(SQLiteDatabase sqLiteDatabase, String inputanke2){
        String sql = "INSERT INTO weight VALUEs(?)";
        sqLiteDatabase.execSQL(sql,new String[]{inputanke2});
    }
    public  void  anke3touroku(SQLiteDatabase sqLiteDatabase, String inputanke3){
        String sql = "INSERT INTO weight VALUEs(?)";
        sqLiteDatabase.execSQL(sql,new String[]{inputanke3});
    }


}
