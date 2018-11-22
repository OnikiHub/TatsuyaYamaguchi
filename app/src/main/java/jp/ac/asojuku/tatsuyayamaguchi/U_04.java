package jp.ac.asojuku.tatsuyayamaguchi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class U_04 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_04);
    }

    protected void onResume(){
        super.onResume();
        Button buttonReTouroku =(Button)findViewById(R.id.buttonReTouroku);
        buttonReTouroku.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(U_04.this,U_03.class);
            }
        });


    }
}
