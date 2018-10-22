package jp.ac.asojuku.tatsuyayamaguchi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class U_01 extends AppCompatActivity {
    public static final String PREFERENCES_FILE_NAME = "preference";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_01);

        ((Button) findViewById(R.id.logincheck)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                Intent intent = new Intent(getApplicationContext(), U_08.class);
                startActivity(intent);

            }
        });
    }
    //ログイン処理
    public void login(){
        SharedPreferences settings = getSharedPreferences(PREFERENCES_FILE_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong("logged-in", 1);
        editor.commit();
    }

}

