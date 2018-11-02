package jp.ac.asojuku.tatsuyayamaguchi;

import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class M_10 extends AppCompatActivity {
    private Uri m_uri;
    TextView texturl;
    private  static  final int REQUEST_CHOOSER = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_10);
        setViews();
    }

    @Override
    protected void onResume() {
        super.onResume();

        final EditText textname = (EditText)findViewById(R.id.editTextName);
        final EditText textdegree = (EditText)findViewById(R.id.editTextDegree);
        final EditText textoutline = (EditText)findViewById(R.id.editTextOutline);
        texturl = (TextView)findViewById(R.id.textViewurl);
        Button buttonComp = (Button)findViewById(R.id.buttoncomp);
        Button buttonback = (Button)findViewById(R.id.buttonback);
        Button buttonlogout = (Button)findViewById(R.id.buttonlogout);


        buttonComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = textname.getText().toString();
                String degree = textdegree.getText().toString();
                String outline = textoutline.getText().toString();
                String image = texturl.getText().toString();

                try {
                    //データベースに接続
                    Connection con = MySqlConnect.getConnection();
                    //ステートメントオブジェクトを作成
                    Statement stmt = (Statement) con.createStatement();

                    //SQL
                    String mySql = "INSERT INTO alcohol(alcohol_name,alcohol_degree,alcohol_outline,alcohol_image)" +
                                    "VALUES("+name+","+degree+","+outline+","+image+");";
                    ResultSet rs = stmt.executeQuery(mySql);


                    //オブジェクトを解放
                    rs.close();
                    stmt.close();
                    con.close();

                } catch (Exception e) {

                }

            }
        });






    }

    private void setViews(){
        Button buttonImg = (Button)findViewById(R.id.buttonimage);
        buttonImg.setOnClickListener(button_onClick);

    }

    private View.OnClickListener button_onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showGallery();
        }
    };

    private  void showGallery(){

        //カメラの起動Intentの用意
        String photoName = System.currentTimeMillis() + ".jpg";
        ContentValues contentValues= new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE,photoName);
        contentValues.put(MediaStore.Images.Media.MIME_TYPE,"image/jpeg");
        m_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);

        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT,m_uri);

        //ギャラリー用のIntent作成
        Intent intentGallery;
        if(Build.VERSION.SDK_INT < 19){
            intentGallery = new Intent(Intent.ACTION_GET_CONTENT);
            intentGallery.setType("image/*");
        }else {
            intentGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intentGallery.addCategory(Intent.CATEGORY_OPENABLE);
            intentGallery.setType("image/jpeg");
        }
        Intent intent = Intent.createChooser(intentCamera,"画像の選択");
        intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {intentGallery});

        startActivityForResult(intent,REQUEST_CHOOSER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CHOOSER) {
            if(resultCode != RESULT_OK){
                //キャンセル時
                return;
            }

            Uri resultUri = (data != null ? data.getData() : m_uri);

            if(resultUri == null){
                //取得失敗
                return;
            }

            //ギャラリーへスキャンを促す
            MediaScannerConnection.scanFile(
                    this,
                    new String[]{resultUri.getPath()},
                    new String[]{"image/jpeg"},
                    null
            );

            //画像を設定
            ImageView imageViewselect = (ImageView)findViewById(R.id.imageViewSelect);
            imageViewselect.setImageURI(resultUri);


            texturl.setText(resultUri.getPath());


        }
    }
}
