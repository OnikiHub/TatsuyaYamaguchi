package jp.ac.asojuku.tatsuyayamaguchi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

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




    }
    public void setText(){
        Integer dispWeight = "usertouroku().weight";
        EditText editTextTaijuHenko = (EditText)findViewById(R.id.editTextTaijuHenko);
        editTextTaijuHenko.setText(dispWeight, TextView.BufferType.NORMAL);

        String dispAnke1 = "anke1";
        EditText editTextAnke1Henko = (EditText)findViewById(R.id.editTextAnke1Henko);
        editTextAnke1Henko.setText(dispAnke1, TextView.BufferType.NORMAL);


    }
}
