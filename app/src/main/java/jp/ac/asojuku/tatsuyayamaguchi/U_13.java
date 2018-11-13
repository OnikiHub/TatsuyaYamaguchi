package jp.ac.asojuku.tatsuyayamaguchi;

        import android.content.Context;
        import android.content.Intent;
        import android.database.sqlite.SQLiteCursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.provider.ContactsContract;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
        import android.widget.SimpleCursorAdapter;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.Statement;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class U_13 extends AppCompatActivity {
    private SQLiteDatabase sqlDB;
    DBManager dbm;
    int selectedID = -1;
    int lastPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_13);
        String[] osake ={"StrongZ","Wine","Kiil","Poppii","noize"};
        //String[] aluchu = {"12%","32%","9%","5%","40%"};
        String[] comment = {"Japanese solu drenk!!!","死ぬ","Yeahhhh","FUCK YOU","そんなのないよ"};
        List<Map<String, String>>data = new ArrayList<Map<String, String>>();
        for (int i=0; i<osake.length; i++) {
            Map<String, String> item = new HashMap<String, String>();
            item.put("osake", osake[i]);
            item.put("comment", comment[i]);
            data.add(item);
        }

        // リスト項目とListViewを対応付けるArrayAdapterを用意する
        SimpleAdapter adapter = new SimpleAdapter(this,data,android.R.layout.simple_list_item_2,
                new String[]{ "osake", "comment"},
                new int[] { android.R.id.text1, android.R.id.text2}
                );
        //ListViewにAdapterを設定する,,,,いい感じ
        ListView listView = (ListView)findViewById(R.id.sListView);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button check = (Button) findViewById(R.id.buttonCheck);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(U_13.this, U_14.class);
                startActivity(intent);
            }
        });
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
        setValueToList(listView);
    }

    private void setValueToList(ListView list) {
        SQLiteCursor cursor = null;

        dbm = new DBManager(this);
        sqlDB = dbm.getWritableDatabase();
        cursor = dbm.selectWord(sqlDB);

        int dblayout = android.R.layout.simple_list_item_1;

        String[] from = {"phrase"};
        int[] to = new int[]{android.R.id.text1};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, dblayout, cursor, from, to, 0);
        list.setAdapter(adapter);
    }
}
/*

    ListView myListView = (ListView) findViewById(R.id.myListView);
        ArrayList<User> users = new ArrayList<>();
        int[] icons = {
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher
        };

        String[] names = {
                "yuumi",
                "ikuj",
                "kikori"
        };

        String[] comments = {
                "FUCK YOU。",
                "…。",
                "ｷｬﾝｷｬﾝ!!!ﾜﾝﾜﾝ!!!ｱﾝｱﾝｱﾝｱﾝｱﾝｱﾝ!!"
        };
        for (int i = 0; i<icons.length; i++) {
            User user = new User();
            user.setIcon(BitmapFactory.decodeResource(
                    getResources(),
                    icons[i]
            ));
            user.setName(names[i]);
            user.setComment(comments[i]);
            users.add(user);
        }
        UserAdapter adapter = new UserAdapter(this, 0, users);

        myListView.setEmptyView(findViewById(R.id.emptyView));
        myListView.setAdapter(adapter);
    }
    public class UserAdapter extends ArrayAdapter<User> {

        private LayoutInflater layoutInflater;

        public UserAdapter(Context c, int id, ArrayList<User> users) {
            super(c, id, users);
            this.layoutInflater = (LayoutInflater) c.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE
            );
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(
                        R.layout.style2,
                        parent,
                        false
                );
            }
            User user = (User) getItem(position);

            ((ImageView) convertView.findViewById(R.id.icon))
                    .setImageBitmap(user.getIcon());
            ((TextView) convertView.findViewById(R.id.name))
                    .setText(user.getName());
            ((TextView) convertView.findViewById(R.id.comment))
                    .setText(user.getComment());

            return convertView;
        }

        public class User {
*/