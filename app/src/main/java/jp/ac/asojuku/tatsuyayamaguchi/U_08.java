package jp.ac.asojuku.tatsuyayamaguchi;

import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class U_08 extends AppCompatActivity {
    DBManager dbm;
    private SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_08);
        dbm = new DBManager(this);
        sqlDB = dbm.getWritableDatabase();

        //TextView textView = findViewById(R.id.textViewww);
        //SQLiteCursor cursor = dbm.selectwe(sqlDB);
        //cursor.moveToFirst();
        //textView.setText(String.valueOf(cursor.getInt(0)));

    }

    @Override
    protected void onResume() {
        super.onResume();

        /*TextView textView = findViewById(R.id.textViewww);
        SQLiteCursor cursor = dbm.selectwe(sqlDB);
        cursor.moveToFirst();
        textView.setText(cursor.getInt(0));*/

        Button buttonpicture = (Button)findViewById(R.id.buttonpic);
        buttonpicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(U_08.this, U_09.class);
                startActivity(intent);
            }
        });

        Button buttondrink = (Button)findViewById(R.id.buttondrink);
        buttondrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(U_08.this, U_13.class);
                startActivity(intent);
            }
        });

        Button buttonchange = (Button)findViewById(R.id.buttonchange);
        buttonchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(U_08.this, U_06.class);
                startActivity(intent);
            }
        });

        Button buttonhis = (Button)findViewById(R.id.buttonhis);
        buttonhis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(U_08.this, U_11.class);
                startActivity(intent);
            }
        });


        /*Button button3    = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(U_08.this, U_03.class);
                startActivity(intent);
            }
        });*/




    }
}
