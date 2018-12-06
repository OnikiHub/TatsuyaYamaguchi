package jp.ac.asojuku.tatsuyayamaguchi;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;


public class U_06 extends AppCompatActivity {
    private SQLiteDatabase sqlDB = null;
    private DBManager dbm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_06);
        dbm = new DBManager(this);
        sqlDB = dbm.getWritableDatabase();


    }

    @Override
    protected void onResume() {
        super.onResume();


        EditText editTextTaijuHenko = findViewById(R.id.editTextTaijuHenko);
         SQLiteCursor cursor = dbm.selectweight(sqlDB);
         cursor.moveToFirst();
         editTextTaijuHenko.setText(cursor.getString(3));
         //[];

         EditText editTextAnke1Henko = findViewById(R.id.editTextAnke1Henko);
         SQLiteCursor cursor1 = dbm.selectAnke1(sqlDB);
         cursor1.moveToFirst();
         editTextAnke1Henko.setText(cursor.getString(5));

         EditText editTextAnke2Henko = findViewById(R.id.editTextAnke2Henko);
         SQLiteCursor cursor2 = dbm.selectAnke2(sqlDB);
         cursor2.moveToFirst();
         editTextAnke2Henko.setText(cursor.getString(6));

         EditText editTextAnke3Henko = findViewById(R.id.editTextAnke3Henko);
         SQLiteCursor cursor3 = dbm.selectAnke3(sqlDB);
         cursor3.moveToFirst();
         editTextAnke3Henko.setText(cursor.getString(7));

     }

    public void buttonHenko_click(View view) {

        EditText editTextTaijuHenko = findViewById(R.id.editTextTaijuHenko);
        String weight = editTextTaijuHenko.getText().toString();
        Integer Weight = Integer.parseInt(weight);

        EditText editTextAnke1Henko = findViewById(R.id.editTextAnke1Henko);
        String anke1 = editTextAnke1Henko.getText().toString();

        EditText editTextAnke2Henko = findViewById(R.id.editTextAnke2Henko);
        String anke2 = editTextAnke2Henko.getText().toString();

        EditText editTextAnke3Henko = findViewById(R.id.editTextAnke3Henko);
        String anke3 = editTextAnke3Henko.getText().toString();

        dbm.userupdate(sqlDB, Weight, anke1, anke2, anke3);


        //if (weight != null &&weight.length() >0) {
        //  Integer Weight = Integer.parseInt(weight);
        Intent intent = new Intent(U_06.this, U_06.class);
        startActivity(intent);
    }
}

        //}else{
          //  Intent intent = new Intent(U_03.this,U_04.class);
           // startActivity(intent);
        //}

        //user_weight.setText("");
        //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
    //publicoid setText(){
       // Integer dispWeight = "usertouroku().weight";
       // EditText editTextTaijuHenko = (EditText)findViewById(R.id.editTextTaijuHenko);
       // editTextTaijuHenko.setText(dispWeight, TextView.BufferType.NORMAL);

       // String dispAnke1 = "anke1";
       // EditText editTextAnke1Henko = (EditText)findViewById(R.id.editTextAnke1Henko);
       // editTextAnke1Henko.setText(dispAnke1, TextView.BufferType.NORMAL);



