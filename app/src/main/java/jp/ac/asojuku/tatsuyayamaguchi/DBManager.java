package jp.ac.asojuku.tatsuyayamaguchi;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.IntegerRes;

public class DBManager extends SQLiteOpenHelper{
    public DBManager(Context context) { super(context,"Tatsuya",null,1);}

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS user(_id INTEGER PRIMARY KEY AUTOINCREMENT,weight INTEGER,level INTEGER　default '1',anke1 TEXT,anke2 TEXT,anke3 TEXT)");

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

    public  void  usertouroku(SQLiteDatabase sqLiteDatabase, Integer inputweight, String inputanke1, String inputanke2, String inputanke3){
        //String sql = "INSERT INTO user(id,weight,anke1,anke2,anke3) VALUEs(null,?,?,?,?)";
        //sqLiteDatabase.execSQL(sql,new Object[]{inputweight,inputanke1,inputanke2,inputanke3});

        ContentValues cv = new ContentValues();
        //cv.put("id",null);
        cv.put("weight",inputweight);
        cv.put("anke1",inputanke1);
        cv.put("anke2",inputanke2);
        cv.put("anke3",inputanke3);
        sqLiteDatabase.insert("user",null,cv);
    }
    public SQLiteCursor selectweight(SQLiteDatabase sqLiteDatabase){
        String selectSql = "SELECT * FROM user WHERE weight";
        SQLiteCursor cursor = (SQLiteCursor)sqLiteDatabase.rawQuery(selectSql,null);
        return cursor;
    }

    public SQLiteCursor selectwe(SQLiteDatabase sqLiteDatabase){
        String selectSql = "SELECT weight FROM user ";
        SQLiteCursor cursor = (SQLiteCursor)sqLiteDatabase.rawQuery(selectSql,null);
        return cursor;
    }


    public void updateLevel(SQLiteDatabase sqLiteDatabase,Integer level){
        String sql = "UPDATE user set level=";
    }


}
