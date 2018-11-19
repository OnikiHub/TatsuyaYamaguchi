package jp.ac.asojuku.tatsuyayamaguchi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static jp.ac.asojuku.tatsuyayamaguchi.R.id.editTextEmail;

public class U_03 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_03);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbm = new DBManager(this);
        sqlDB = dbm.getWritableDatabase();

        final EditText name = (EditText)findViewById(R.id.editTextName);
        final EditText tel = (EditText)findViewById(R.id.editTextTEL);
        final EditText taiju = (EditText)findViewById(R.id.editTextTaiju);
        final EditText email = (EditText)findViewById(R.id.editTextEmail);
        Button buttonInsert = (Button)findViewById(R.id.buttonInsert);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = word.getText().toString();

                if (message != null)dbm.insertWord(sqlDB,message);

                word.setText("");
                Toast.makeText(getApplicationContext(),"コトバを覚えました", Toast.LENGTH_LONG).show();
            }
        });
}
