package jp.ac.asojuku.tatsuyayamaguchi;

import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class U_10 extends AppCompatActivity {
    private SQLiteDatabase sqlDB;
    DBManager dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_10);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String result = intent.getStringExtra("result");
        double jre = Double.parseDouble(result);

        TextView textView = findViewById(R.id.textView8);
        textView.setText(result);
        TextView textViewwe = findViewById(R.id.textViewwe);
        dbm = new DBManager(this);
        sqlDB = dbm.getWritableDatabase();






        TextView textViewre = findViewById(R.id.textViewresult);


        if (jre <= 0.1){
            textViewre.setText("レベル1");
            dbm.updateLevel(sqlDB,1);
        }else if(jre <=0.2){
            textViewre.setText("レベル2");
            dbm.updateLevel(sqlDB,2);
        }else if(jre <=0.4){
            textViewre.setText("レベル3");
            dbm.updateLevel(sqlDB,3);
        }else if(jre <=0.6){
            textViewre.setText("レベル4");
            dbm.updateLevel(sqlDB,4);
        }else {
            textViewre.setText("レベル5");
            dbm.updateLevel(sqlDB,5);
        }

        //SQLiteCursor cursor = dbm.selectUser(sqlDB);
        //cursor.moveToFirst();
        //textViewwe.setText(String.valueOf(cursor.getInt(2)));



    }
}
