package jp.ac.asojuku.tatsuyayamaguchi;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.FaceDetector;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.projectoxford.face.*;
import com.microsoft.projectoxford.face.contract.*;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


import java.net.URL;
import java.util.UUID;


public class U_09 extends AppCompatActivity {
    private final int PICK_IMAGE = 1;
    private ProgressDialog detectionProgressDialog;
    private final String apiEndpoint = "https://japaneast.api.cognitive.microsoft.com/face/v1.0";
    private final String subscriptionKey = "85604a0889d0489e80d8745eabe9f798";

    private final FaceServiceClient faceServiceClient = new FaceServiceRestClient(apiEndpoint,subscriptionKey);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_09);


        final EditText editTextid2 = findViewById(R.id.editTextId2);
        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE);
                }
        });

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources r = getResources();
                Bitmap bmp = BitmapFactory.decodeResource(r,R.drawable.tatsuya);
                String id1 = detect(bmp);


                SpannableStringBuilder sb = (SpannableStringBuilder)editTextid2.getText();
                String id2 = sb.toString();

                String fre = verify(id1,id2);







            }
        });

        detectionProgressDialog = new ProgressDialog(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null &&   data.getData() != null){
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                ImageView imageView = findViewById(R.id.imageView1);
                imageView.setImageBitmap(bitmap);
                detectAndFrame(bitmap);
            }catch (IOException e){
                e.printStackTrace();

            }
        }
    }
    private String verify(final String id1, final  String id2){


        class Faceids{
            String face1 =id1;
            String face2 =id2;
        }

        Faceids faces = new Faceids();

        final EditText editText = findViewById(R.id.editText);




        AsyncTask<Faceids,String,Double> verifyTask = new AsyncTask<Faceids, String, Double>() {
            Double re;



            protected Double doInBackground(Faceids... params) {
                try {
                    Faceids face = params[0];
                    String facedata1 = face.face1;
                    String facedata2 = face.face2;

                    UUID faceId1 = UUID.fromString(facedata1);
                    UUID faceId2 = UUID.fromString(facedata2);


                    VerifyResult result1 = faceServiceClient.verify(faceId1, faceId2);
                    re=result1.confidence;




                }catch (Exception e){

                }
                return re;

            }

            @Override
            protected void onPostExecute(Double aDouble) {
                editText.setText(String.valueOf(aDouble));
            }






        };
        verifyTask.execute(faces);
        SpannableStringBuilder sb = (SpannableStringBuilder)editText.getText();
        String str = sb.toString();

        return str;

    }

    private String detect (final Bitmap imageBitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        final FaceServiceClient.FaceAttributeType[] faceAttributes = {FaceServiceClient.FaceAttributeType.Age};
        final EditText editTextid = findViewById(R.id.editTextId1);


        AsyncTask<InputStream,String,Face[]> detectTask = new AsyncTask<InputStream, String, Face[]>() {
            String exceptionMessage = "";
            Face[] result;
            @Override
            protected Face[] doInBackground(InputStream... params) {
                try {
                    result = faceServiceClient.detect(
                            params[0],
                            true,
                            false,
                            faceAttributes


                    );

                    for (Face face : result){
                        editTextid.setText(String.valueOf(face.faceId));



                    }





                }catch (Exception e){

                }
                return result;



            }





        };

        detectTask.execute(inputStream);
        SpannableStringBuilder sb = (SpannableStringBuilder)editTextid.getText();
        String id1 = sb.toString();
        return id1;


    }







    private void detectAndFrame(final Bitmap imageBitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        final FaceServiceClient.FaceAttributeType[] faceAttributes = {FaceServiceClient.FaceAttributeType.Age};
        final EditText editText = findViewById(R.id.editTextId2);



        AsyncTask<InputStream,String,Face[]> detectTask = new AsyncTask<InputStream, String, Face[]>() {

            String exceptionMessage = "";
            @Override
            protected Face[] doInBackground(InputStream... params) {
                try {
                    publishProgress("Detecting...");
                    Face[] result = faceServiceClient.detect(
                            params[0],
                            true,
                            false,
                             faceAttributes




                    );

                    for (Face face : result){
                        FaceAttribute faceAttribute = face.faceAttributes;


                        editText.setText(String.valueOf(face.faceId));

                        TextView textage = (TextView)findViewById(R.id.textViewage);
                        textage.setText(String.valueOf(faceAttribute.age));
                    }








                    if (result == null){
                        publishProgress("Detection Finished. Nothing detected");
                        return null;
                    }
                    publishProgress(String.format("Detection Finished. %d face(s) detected",result.length));
                    return result;
                }catch (Exception e){
                    exceptionMessage = String.format("Detection failed: %s", e.getMessage());
                    return  null;
                }
            }

            @Override
            protected void onPreExecute() {
                detectionProgressDialog.show();
            }

            @Override
            protected void onProgressUpdate(String... progress) {
                detectionProgressDialog.setMessage(progress[0]);
            }

            @Override
            protected void onPostExecute(Face[] result) {
                detectionProgressDialog.dismiss();
                if (!exceptionMessage.equals("")){
                    showError(exceptionMessage);
                }
                if (result == null) return;
                ImageView imageView = findViewById(R.id.imageView1);
                imageView.setImageBitmap(drawFaceRectanglesOnBitmap(imageBitmap, result));
                imageBitmap.recycle();

            }
        };

        detectTask.execute(inputStream);
    }

    private void showError(String message){
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .create().show();
    }

    private static Bitmap drawFaceRectanglesOnBitmap(Bitmap originalBitmap, Face[] faces){
        Bitmap bitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888,true);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        if(faces != null){
            for (Face face : faces){
                FaceRectangle faceRectangle = face.faceRectangle;
                canvas.drawRect(
                        faceRectangle.left,
                        faceRectangle.top,
                        faceRectangle.left + faceRectangle.width,
                        faceRectangle.top + faceRectangle.height,
                        paint
                );
            }
        }
        return bitmap;
    }

}
