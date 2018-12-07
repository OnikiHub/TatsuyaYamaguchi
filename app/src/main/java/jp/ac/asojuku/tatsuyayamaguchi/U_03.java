package jp.ac.asojuku.tatsuyayamaguchi;

import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class U_03 extends AppCompatActivity {
    private SQLiteDatabase sqlDB;
    DBManager dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_03);
        dbm = new DBManager(this);
        sqlDB = dbm.getWritableDatabase();


    }



    @Override
    protected void onResume() {
        super.onResume();

        EditText editTextweight = findViewById(R.id.editTextweight);
        final String weight = editTextweight.getText().toString();
        //int Weight = Integer.parseInt(weight);

        Button buttonInsert = (Button) findViewById(R.id.buttonInsert);



        SQLiteCursor cursor = dbm.selectweight(sqlDB);

//        SQLiteCursor cursor1 = dbm.selectAnke1(sqlDB);
//        cursor1.moveToFirst();
//        SQLiteCursor cursor2 = dbm.selectAnke2(sqlDB);
//        cursor2.moveToFirst();
//        SQLiteCursor cursor3 = dbm.selectAnke3(sqlDB);
//        cursor3.moveToFirst();
//        SQLiteCursor cursor4 = dbm.selectAnke4(sqlDB);
//        cursor4.moveToFirst();

        if (cursor.getCount() >0){

            Intent intent = new Intent(U_03.this,U_08.class);
            startActivity(intent);


        }








        //buttonInsert.setOnClickListener(new View.OnClickListener() {
        //xxx
    }

    private void initSpinners(){
        Spinner spinneranke1 = findViewById(R.id.spinneranke1);
        Spinner spinneranke2 = findViewById(R.id.spinneranke2);
        Spinner spinneranke3 = findViewById(R.id.spinneranke3);
        Spinner spinneranke4 = findViewById(R.id.spinneranke4);
        String[] list1 = getResources().getStringArray(R.array.listOne);
        String[] list2 = getResources().getStringArray(R.array.listTwo);
        String[] list3 = getResources().getStringArray(R.array.listThree);
        String[] list4 = getResources().getStringArray(R.array.listFour);
        ArrayAdapter<String> adapter1
                = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list1);

        ArrayAdapter<String> adapter2
                = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list2);

        ArrayAdapter<String> adapter3
                = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list3);

        ArrayAdapter<String> adapter4
                = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list4);

        spinneranke1.setAdapter(adapter1);
        spinneranke2.setAdapter(adapter2);
        spinneranke3.setAdapter(adapter3);
        spinneranke4.setAdapter(adapter4);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


    }



    public void buttonInsert_click(View view) {
        //int message = user_weight.getText().toint();
        EditText editTextweight = (EditText) findViewById(R.id.editTextweight);
        String weight = editTextweight.getText().toString();
        //Integer Weight = Integer.parseInt(weight);
        Spinner spinneranke1 = (Spinner) findViewById(R.id.spinneranke1);
        Spinner spinneranke2 = (Spinner) findViewById(R.id.spinneranke2);
        Spinner spinneranke3 = (Spinner) findViewById(R.id.spinneranke3);
        Spinner spinneranke4 = (Spinner) findViewById(R.id.spinneranke4);



        //if (Weight != null) dbm.touroku(sqlDB, Weight);

        if (weight != null &&weight.length() >0) {
            String anke1 = spinneranke1.getSelectedItem().toString();
            String anke2 = spinneranke2.getSelectedItem().toString();
            String anke3 = spinneranke3.getSelectedItem().toString();
            String anke4 = spinneranke4.getSelectedItem().toString();

                try{
                    Integer Weight = Integer.parseInt(weight);
                    dbm.usertouroku(sqlDB, Weight, anke1, anke2, anke3, anke4);
                    Intent intent = new Intent(U_03.this,U_08.class);
                    startActivity(intent);

                }catch(NumberFormatException e){
                    Intent intent = new Intent(U_03.this,U_04.class);
                    startActivity(intent);
                }

            //dbm.usertouroku(sqlDB, Weight, anke1, anke2, anke3, anke4);
            //Intent intent = new Intent(U_03.this,U_08.class);
            //startActivity(intent);

        }else{
            //ここまで処理が行かないので落ちる。日本語
            Intent intent = new Intent(U_03.this,U_04.class);
            startActivity(intent);
        }


        //user_weight.setText("");
        //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
    }

}