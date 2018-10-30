package jp.ac.asojuku.tatsuyayamaguchi;

        import android.provider.ContactsContract;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.ImageButton;
        import android.widget.Toast;

        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.Statement;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;

public class U_13 extends AppCompatActivity {
    private List<ImageButton>btlist{
        for(btlist <= null){
            setContentView(R.layout.activity_u_13);
            try{
                Connection con = MySqlConnect.getConnection();
                Statement stmt = (Statement)con.createStatement();

                String mySql = "select * from osake";
                ResultSet rs = stmt.executeQuery(mySql);

                while (rs.next()){
                    Toast.makeText(getApplicationContext(), rs.getString("data"),Toast
                }
                rs.close();
                stmt.close();
                con.close();
            }catch (Exception e){
            
        }
    }
}





        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    List<String> list = new ArrayList<String>(Arrays.asList(ite));



}
