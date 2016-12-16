package com.icemobile.barcoders;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

import com.icemobile.barcoders.data.domain.Sentence;

import java.util.ArrayList;

public class AnalyzeActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);

        videoView = (VideoView)findViewById(R.id.video_view);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.video);
        videoView.setVideoURI(uri);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Intent intent = new Intent(AnalyzeActivity.this, ResultActivity.class);
                ArrayList<Sentence> sentences = getIntent().getParcelableArrayListExtra("sentences");
                intent.putParcelableArrayListExtra("sentences", sentences);
                startActivity(intent);
            }
        });
        videoView.start();
    }
}
