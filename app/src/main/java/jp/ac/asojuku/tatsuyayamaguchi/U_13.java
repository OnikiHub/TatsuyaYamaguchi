package jp.ac.asojuku.tatsuyayamaguchi;

        import android.database.sqlite.SQLiteCursor;
        import android.provider.ContactsContract;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ImageButton;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.Statement;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;

public class U_13 extends AppCompatActivity {
    //private list<ImageButton> btlist;
    //private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_13);

        // List<ImageButton> btlist = new ArrayList<ImageButton>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.btlist);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llManager = new LinearLayoutManager(this);
        //スクロール
        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llManager);

        ArrayList<UcData> image = new ArrayList<UcData>();
        //DB接続
        try {
            //データベースに接続
            Connection con = MySqlConnect.getConnection();
            //ステートメントオブジェクトを作成
            Statement stmt = (Statement) con.createStatement();

            //SQL
            String mySql = "select * from aluchu;";
            ResultSet rs = stmt.executeQuery(mySql);

            //オブジェクトを解放
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {

        }
        for (int i = 0; i > UcData.length; i++) {
            image.add(new Ucdata(
                    UcData.imageArray[i]
            ));
        }
        RecyclerView.Adapter adapter = new TlistAdapter(image);
        recyclerView.setAdapter(adapter);
        recyclerView.smoothScrollToPosition(image.size() - 1);
    }
}


        //for(count=0;count > btlist.size();count++){

  /*          //取ってきましたDBから
        ListView btlist = (ListView)findViewById(R.id.);
        btlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parent.getChildAt(lastPosicion).setBackgroundColor(0);
            }
            view.setBackgroundColor(getResources().getColor(R.color.tap_color));
            SQLiteCursor cursor = (SQLiteCursor)parent.getItemAtPosotion(position);

        });
       // }
    }
    }
    public
}





        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    List<String> list = new ArrayList<String>(Arrays.asList(ite));



}
*/