package me.lovenery.a20161120_3;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp1 = new MediaPlayer();
        mp1 = MediaPlayer.create(MainActivity.this, R.raw.light);
        mp1.setLooping(true);

    }

    // 暫停
    @Override
    protected void onPause() {
        try {
            mp1.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onPause();
    }

    // 繼續
    @Override
    protected void onResume() {
        try {
            if (mp1 != null) {
                mp1.stop(); // 停止
            }
            mp1.prepare(); // 準備
            mp1.start(); // 開始
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onResume();
    }
}
