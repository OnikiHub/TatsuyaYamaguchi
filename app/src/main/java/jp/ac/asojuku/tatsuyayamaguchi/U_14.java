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
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class U_14 extends AppCompatActivity {
    private SQLiteDatabase sqlDB;
    DBManager dbm;
    //iは顔で判断した強さ
    int i = 0;
    private int alcohol;
    private double update = 0;
    private double update2 = 0;
    private double update3 = 0;
    private double update4 = 0;
    int weight = 65;
    int selectedID = -1;
    int lastPosition = -1;
    private AlarmManager am;
    private PendingIntent pending;
    private int requestCode = 1;
    int judment = 1;
   /* private Calendar setAlarmTime() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 5);
        private PendingIntent setAlarmPendingIntent(Int) {

            Intent intent = new Intent(getApplicationContext(), MyIntentService.class);
            intent.putExtra("ALARM_FLAG", flags);
            PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 0, intent, flags);

            return pendingIntent;
        }

        return calendar;
    }*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_14);

        String[] osake ={"ビール","芋焼酎","ハイボール","カクテル","ワイン","米焼酎"};
        Integer[] alcohol = {5,25,20,4,14,7};
        String[] comment = {"発泡酒","芋","ウイスキー","カクテル","果実酒","米"};
        Integer[] ml = {400,150,350,350,120,150};

        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (int i=0; i<osake.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("osake", osake[i]);
            item.put("alcohol",alcohol[i]);
            item.put("comment", comment[i]);
            item.put("ml", ml[i]);
            data.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,data,android.R.layout.simple_list_item_2,
                new String[]{ "osake","comment"},
                new int[] { android.R.id.text1, android.R.id.text2}
        );
        //ListViewにAdapterを設定する,,,,いい感じ
        ListView listView = (ListView)findViewById(R.id.sListView);
        listView.setAdapter(adapter);

        Button button = this.findViewById(R.id.button1);
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
                calendar.add(Calendar.SECOND, 3);

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
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //小数点


        //ここはリスト表示
        ListView listView = (ListView) findViewById(R.id.sListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (selectedID != -1) {

                    parent.getChildAt(lastPosition).setBackgroundColor(0);
                }
                //hashMapで値を渡してposition(行)で計算
                //view.setBackgroundColor(getResources().getColor(R.color.tap_color));

                HashMap<String, Object> hashMap = (HashMap<String, Object>) parent.getItemAtPosition(position);
                selectedID = position;
                //キャストして受け取る(String,Integer,String)明日はここから↓↓

                String osake = (String) hashMap.get("osake");
                Integer value = (Integer) hashMap.get("alcohol");
                String comment = (String) hashMap.get("comment");
                Integer ml = (Integer) hashMap.get("ml");
                //ここで酔いの強さ（弱い1.4、普通1、強い0.5、普通より弱い1.2,普通より強い0.65）の四つに分ける
                if (judment == 1) {
                    update = (ml * value * 1);
                    update2 = (833 * weight);
                    update3 = update / update2;
                    update4 = update3 + update4;
                    double d3 = update4;
                    BigDecimal bd = new BigDecimal(String.valueOf(d3));


                String osake = (String)hashMap.get("osake");
                Integer value = (Integer)hashMap.get("alcohol");
                String comment = (String)hashMap.get("comment");
                Integer ml = (Integer)hashMap.get("ml");

                update = (ml * value * 1) ;
                update2 = (833 * 52) ;
                update3 = update / update2 ;
                update4 = update3 + update4;
                double d3 = update4;
                BigDecimal bd = new BigDecimal(String.valueOf(d3));


                TextView updatatext = (TextView)findViewById(R.id.updatatext);
                updatatext.setText(String.valueOf(d3)+"%");
                if (update4 < 0.05) {
                    BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                    TextView control = (TextView) findViewById(R.id.control);
                    updatatext.setText(bd3.doubleValue() + "%です。" + "爽快期");
                }else if (update4 < 0.10) {
                    BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                    TextView control = (TextView) findViewById(R.id.control);
                    updatatext.setText(bd3.doubleValue() + "%です。" + "ほろ酔い期");
                }else if (update4 < 0.15) {
                    BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                    TextView control = (TextView) findViewById(R.id.control);
                    updatatext.setText(bd3.doubleValue() + "%" + "酩酊初期");
                }else if (update4 < 0.30) {
                    BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                    TextView control = (TextView) findViewById(R.id.control);
                    updatatext.setText(bd3.doubleValue() + "%" + "酩酊期");
                }else if (update4 < 0.40) {
                    BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                    TextView control = (TextView) findViewById(R.id.control);
                    updatatext.setText(bd3.doubleValue() + "%" + "泥酔期");
                }else if (update4 < 0.50) {
                    BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                    TextView control = (TextView) findViewById(R.id.control);
                    updatatext.setText(bd3.doubleValue() + "%" + "昏睡期　死の危険がある");
                }else if (update4 >= 0.51){
                    BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                    TextView control = (TextView)findViewById(R.id.control);
                    updatatext.setText(bd3.doubleValue() + "%" + "これ以上は飲まないほうがいい");
                }


                    TextView updatatext = (TextView) findViewById(R.id.updatatext);
                    updatatext.setText(String.valueOf(d3) + "%");
                    if (update4 < 0.05) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%です。" + "爽快期");
                    } else if (update4 < 0.10) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%です。" + "ほろ酔い期");
                    } else if (update4 < 0.15) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "酩酊初期");
                    } else if (update4 < 0.30) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "酩酊期");
                    } else if (update4 < 0.40) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "泥酔期");
                    } else if (update4 < 0.50) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "昏睡期　死の危険がある");
                    } else if (update4 >= 0.51) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "これ以上は飲まないほうがいい");
                    }
                    hashMap.get(position);
                    lastPosition = position;

                } else if (judment == 2) {
                    update = (ml * value * 1.4);
                    update2 = (833 * weight);
                    update3 = update / update2;
                    update4 = update3 + update4;
                    double d3 = update4;
                    BigDecimal bd = new BigDecimal(String.valueOf(d3));


                    TextView updatatext = (TextView) findViewById(R.id.updatatext);
                    updatatext.setText(String.valueOf(d3) + "%");
                    if (update4 < 0.05) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%です。" + "爽快期");
                    } else if (update4 < 0.10) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%です。" + "ほろ酔い期");
                    } else if (update4 < 0.15) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "酩酊初期");
                    } else if (update4 < 0.30) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "酩酊期");
                    } else if (update4 < 0.40) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "泥酔期");
                    } else if (update4 < 0.50) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "昏睡期　死の危険がある");
                    } else if (update4 >= 0.51) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "これ以上は飲まないほうがいい");
                    }
                    hashMap.get(position);
                    lastPosition = position;
                } else if (judment == 3) {
                    update = (ml * value * 1);
                    update2 = (833 * weight);
                    update3 = update / update2;
                    update4 = update3 + update4;
                    double d3 = update4;
                    BigDecimal bd = new BigDecimal(String.valueOf(d3));


                    TextView updatatext = (TextView) findViewById(R.id.updatatext);
                    updatatext.setText(String.valueOf(d3) + "%");
                    if (update4 < 0.05) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%です。" + "爽快期");
                    } else if (update4 < 0.10) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%です。" + "ほろ酔い期");
                    } else if (update4 < 0.15) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "酩酊初期");
                    } else if (update4 < 0.30) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "酩酊期");
                    } else if (update4 < 0.40) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "泥酔期");
                    } else if (update4 < 0.50) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "昏睡期　死の危険がある");
                    } else if (update4 >= 0.51) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "これ以上は飲まないほうがいい");
                    }
                    hashMap.get(position);
                    lastPosition = position;
                } else if (judment == 4) {
                    update = (ml * value * 0.5);
                    update2 = (833 * weight);
                    update3 = update / update2;
                    update4 = update3 + update4;
                    double d3 = update4;
                    BigDecimal bd = new BigDecimal(String.valueOf(d3));


                    TextView updatatext = (TextView) findViewById(R.id.updatatext);
                    updatatext.setText(String.valueOf(d3) + "%");
                    if (update4 < 0.05) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%です。" + "爽快期");
                    } else if (update4 < 0.10) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%です。" + "ほろ酔い期");
                    } else if (update4 < 0.15) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "酩酊初期");
                    } else if (update4 < 0.30) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "酩酊期");
                    } else if (update4 < 0.40) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "泥酔期");
                    } else if (update4 < 0.50) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "昏睡期　死の危険がある");
                    } else if (update4 >= 0.51) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "これ以上は飲まないほうがいい");
                    }
                    hashMap.get(position);
                    lastPosition = position;
                } else if (judment == 5) {
                    update = (ml * value * 1.25);
                    update2 = (833 * weight);
                    update3 = update / update2;
                    update4 = update3 + update4;
                    double d3 = update4;
                    BigDecimal bd = new BigDecimal(String.valueOf(d3));


                    TextView updatatext = (TextView) findViewById(R.id.updatatext);
                    updatatext.setText(String.valueOf(d3) + "%");
                    if (update4 < 0.05) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%です。" + "爽快期");
                    } else if (update4 < 0.10) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%です。" + "ほろ酔い期");
                    } else if (update4 < 0.15) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "酩酊初期");
                    } else if (update4 < 0.30) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "酩酊期");
                    } else if (update4 < 0.40) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "泥酔期");
                    } else if (update4 < 0.50) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "昏睡期　死の危険がある");
                    } else if (update4 >= 0.51) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "これ以上は飲まないほうがいい");
                    }
                    hashMap.get(position);
                    lastPosition = position;
                } else if (judment == 6) {
                    update = (ml * value * 0.65);
                    update2 = (833 * weight);
                    update3 = update / update2;
                    update4 = update3 + update4;
                    double d3 = update4;
                    BigDecimal bd = new BigDecimal(String.valueOf(d3));


                    TextView updatatext = (TextView) findViewById(R.id.updatatext);
                    updatatext.setText(String.valueOf(d3) + "%");
                    if (update4 < 0.05) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%です。" + "爽快期");
                    } else if (update4 < 0.10) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%です。" + "ほろ酔い期");
                    } else if (update4 < 0.15) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "酩酊初期");
                    } else if (update4 < 0.30) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "酩酊期");
                    } else if (update4 < 0.40) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "泥酔期");
                    } else if (update4 < 0.50) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "昏睡期　死の危険がある");
                    } else if (update4 >= 0.51) {
                        BigDecimal bd3 = bd.setScale(3, RoundingMode.HALF_UP);
                        TextView control = (TextView) findViewById(R.id.control);
                        updatatext.setText(bd3.doubleValue() + "%" + "これ以上は飲まないほうがいい");
                    }
                    hashMap.get(position);
                    lastPosition = position;
                }
            }
        });
    }
}
