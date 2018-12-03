package jp.ac.asojuku.tatsuyayamaguchi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.List;

public class U_06 extends AppCompatActivity {

    private SQLiteDatabase db = null;
    private DBManager manager = null;
    protected ListView johoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_06);
        manager = new DBManager(this);
        db = manager.getReadableDatabase();
        db = manager.getWritableDatabase();
        johoList = (ListView)findViewById(R.id.JohoList);
        Cursor c = db.query("user", null, null, null, null, null, null
        );
        c.moveToFirst();
        johoList.setAdapter(new SimpleCursorAdapter(this,R.layout.list_item,c,
                new String[] {""})





    }
}
