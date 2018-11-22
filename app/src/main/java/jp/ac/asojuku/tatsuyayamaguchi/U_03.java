package jp.ac.asojuku.tatsuyayamaguchi;

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

        EditText editTextweight = (EditText) findViewById(R.id.editTextweight);
        final String weight = editTextweight.getText().toString();
        int Weight = Integer.parseInt(weight);

        Button buttonInsert = (Button) findViewById(R.id.buttonInsert);

        //buttonInsert.setOnClickListener(new View.OnClickListener() {
        //xxx
    }


    private void initSpinners(){
        Spinner spinneranke1 = (Spinner)findViewById(R.id.spinneranke1);
        Spinner spinneranke2 = (Spinner) findViewById(R.id.spinneranke2);
        Spinner spinneranke3 = (Spinner) findViewById(R.id.spinneranke3);
        String[] list1 = getResources().getStringArray(R.array.listOne);
        String[] list2 = getResources().getStringArray(R.array.listTwo);
        String[] list3 = getResources().getStringArray(R.array.listThree);
        ArrayAdapter<String> adapter1
                = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list1);

        ArrayAdapter<String> adapter2
                = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list2);

        ArrayAdapter<String> adapter3
                = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list3);

        spinneranke1.setAdapter(adapter1);
        spinneranke2.setAdapter(adapter2);
        spinneranke3.setAdapter(adapter3);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


    }



    public void buttonInsert_click(View view) {
        //int message = user_weight.getText().toint();
        EditText editTextweight = (EditText) findViewById(R.id.editTextweight);
        String weight = editTextweight.getText().toString();
        Integer Weight = Integer.parseInt(weight);
        Spinner spinneranke1 = (Spinner) findViewById(R.id.spinneranke1);
        Spinner spinneranke2 = (Spinner) findViewById(R.id.spinneranke2);
        Spinner spinneranke3 = (Spinner) findViewById(R.id.spinneranke3);


        if (Weight != null) dbm.touroku(sqlDB, Weight);

        //user_weight.setText("");
        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
    }

}
