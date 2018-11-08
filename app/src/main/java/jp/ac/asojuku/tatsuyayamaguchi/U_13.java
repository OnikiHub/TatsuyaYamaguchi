package jp.ac.asojuku.tatsuyayamaguchi;

        import android.content.Context;
        import android.database.sqlite.SQLiteCursor;
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
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.Statement;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;

public class U_13 extends AppCompatActivity {
    //private list<ImageView> Imagelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_13);


        ListView myListView = (ListView) findViewById(R.id.myListView);
        ArrayList<User> users = new ArrayList<>();
        int[] icons = {
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher
        };

        String[] names = {
                "azunobu",
                "azuki",
                "kanon"
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

        }
    }
}





     /*@Override
     protected  void onResume(){
         super.onResume();

        ListView listView = (ListView)findViewById(R.id.Imagelist);


        ArrayList<ImageView> listItems = new ArrayList<>();
        for(int i = 0; i < 4 ;i++) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            ImageView item = new ImageView(bmp, "基本の" + String.valueOf(i));
            listItems.add(item);
        }
        TlistAdapte                                                                                                                                                                                                                                         r adapter = new TlistAdapter(this,R.id.Imagelist,);
        listView.setAdapter(adapter);

        }*/
        /*try {
            //データベースに接続
            Connection con = MySqlConnect.getConnection();
            //ステートメントオブジェクトを作成
            Statement stmt = (Statement) con.createStatement();

            //SQL
            String mySql = "select *  from table;";
            ResultSet rs = stmt.executeQuery(mySql);

            //オブジェクトを解放
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
        }*/





