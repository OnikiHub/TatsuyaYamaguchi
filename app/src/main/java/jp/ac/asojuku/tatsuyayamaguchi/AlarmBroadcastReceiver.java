package jp.ac.asojuku.tatsuyayamaguchi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.widget.Toast;

public class AlarmBroadcastReceiver extends BroadcastReceiver{
    public void onReceive(Context context, Intent intent){
        // toast で受け取りを確認
        Toast.makeText(context, "Received ", Toast.LENGTH_LONG).show();
        MediaPlayer player = new MediaPlayer();

//アラーム音として設定
        player.setAudioStreamType(AudioManager.STREAM_ALARM);

//音源を指定
        //player.setDataSource(context,System.DEFAULT_ALARM_ALERT_URI);

//繰り返し再生するように指定
        player.setLooping(true);

    }
}
