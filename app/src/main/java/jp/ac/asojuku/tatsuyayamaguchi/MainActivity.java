package jp.ac.asojuku.tatsuyayamaguchi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static final String PREFERENCES_FILE_NAME = "preference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

<<<<<<<<< Temporary merge branch 1
        ias

=========

                
>>>>>>>>> Temporary merge branch 2
/*<<<<<<< HEAD
        setContentView(R.layout.activity_main);kk
=======
        setContentView(R.layout.activity_u_01);
       *//* if (logincheck()) {
            Intent intent = new Intent(getApplicationContext(), U_08.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getApplicationContext(), U_02.class);
            startActivity(intent);
        }
    }
    public Boolean logincheck(){
        SharedPreferences settings = getSharedPreferences(PREFERENCES_FILE_NAME, 0);
        if(settings == null) return false;
        int login = (int) settings.getLong("logged-in", 0);
        if(login == 1) return true;
        else return false;
    *//*
>>>>>>> ログイン画面*/
        startActionMode("kiki");
    }



}

