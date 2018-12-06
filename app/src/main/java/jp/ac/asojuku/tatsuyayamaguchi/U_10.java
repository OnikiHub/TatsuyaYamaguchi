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
        dbm = new DBManager(this);
        sqlDB = dbm.getWritableDatabase();
        Intent intent = getIntent();
        String result = intent.getStringExtra("result");
        double jre = Double.parseDouble(result);




        TextView textViewre = findViewById(R.id.textViewresult);


        if (jre <= 0.1){
            textViewre.setText("レベル1：非常に弱い");
            dbm.updateLevel(sqlDB,1);
        }else if(jre <=0.2){
            textViewre.setText("レベル2：弱い");
            dbm.updateLevel(sqlDB,2);
        }else if(jre <=0.3){
            textViewre.setText("レベル3：普通");
            dbm.updateLevel(sqlDB,3);
        }else if(jre <=0.5){
            textViewre.setText("レベル4：強い");
            dbm.updateLevel(sqlDB,4);
        }else {
            textViewre.setText("レベル5：酒豪");
            dbm.updateLevel(sqlDB,5);
        }

        //SQLiteCursor cursor = dbm.selectwe(sqlDB);
        //cursor.moveToFirst();
        //textViewwe.setText(cursor.getInt(1));


    }
}
