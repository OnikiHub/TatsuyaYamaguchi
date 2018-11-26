package jp.ac.asojuku.tatsuyayamaguchi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class U_08 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_08);
    }

    @Override
    protected void onResume() {
        super.onResume();

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

        Button buttoncontent = (Button)findViewById(R.id.buttoncontent);
        buttoncontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(U_08.this,M_10.class);
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
