package jp.ac.asojuku.tatsuyayamaguchi;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class U_14 extends AppCompatActivity {
    private SQLiteDatabase sqlDB;
    DBManager dbm;
    private int value;
    private int alcohol;
    private int update;
    int selectedID = -1;
    int lastPosition = -1;
    private AlarmManager am;
    private PendingIntent pending;
    private int requestCode = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_14);
        String[] osake ={"StrongZ","Wine","Kiil","Poppii","noize"};
        //int[] alcohol = {12,32,9,5,40};
        String[] comment = {"Japanese soul drenk!!!","sick","Yeahhhh","FUCK YOU","wanwanawn"};

        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (int i=0; i<osake.length; i++) {
            Map<String, String> item = new HashMap<String, String>();
            item.put("osake", osake[i]);
            //item.put("alcohol",alcohol[i]);
            item.put("comment", comment[i]);
            data.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,data,android.R.layout.simple_list_item_2,
                new String[]{ "osake","comment"},
                new int[] { android.R.id.text1, android.R.id.text2}
        );
        //ListViewにAdapterを設定する,,,,いい感じ
        ListView listView = (ListView)findViewById(R.id.sListView);
        listView.setAdapter(adapter);

        /*Button button = this.findViewById(R.id.button1);
        String str = "Alarm set";
        button.setText(str);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //時間をSETする
                Calendar calendar = Calendar.getInstance();
                // Calendarを使って現在の時間をミリ秒で取得
                calendar.setTimeInMillis(System.currentTimeMillis());
                // 5秒後に設定
                calendar.add(Calendar.SECOND, 5);

                //明示的なBroadCast
                Intent intent = new Intent(getApplicationContext(),
                        AlarmBroadcastReceiver.class);
                PendingIntent pending = PendingIntent.getBroadcast(
                        getApplicationContext(), 0, intent, 0);
                // アラームをセットする
                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                if (am != null) {
                    am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);

                    Toast.makeText(getApplicationContext(),
                            "Set Alarm ", Toast.LENGTH_SHORT).show();
                }*/
            //}
        //});
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        value = intent.getIntExtra("value",0);
        alcohol = intent.getIntExtra("alcohol",0);
        update = intent.getIntExtra("update",0);

        ListView listView = (ListView) findViewById(R.id.sListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (selectedID != -1) {

                    parent.getChildAt(lastPosition).setBackgroundColor(0);
                }
                //view.setBackgroundColor(getResources().getColor(R.color.tap_color));
                SQLiteCursor cursor = (SQLiteCursor) parent.getItemAtPosition(position);
                selectedID = cursor.getInt(cursor.getColumnIndex("_id"));

                lastPosition = position;
            }
        });
        //setValueToList(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });
    }
}
