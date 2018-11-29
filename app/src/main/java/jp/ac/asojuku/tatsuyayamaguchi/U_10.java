package jp.ac.asojuku.tatsuyayamaguchi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class U_10 extends AppCompatActivity {

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

        TextView textView = findViewById(R.id.textView8);
        textView.setText(result);

    }
}
