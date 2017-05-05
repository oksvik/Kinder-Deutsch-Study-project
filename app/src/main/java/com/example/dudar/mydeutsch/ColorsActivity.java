package com.example.dudar.mydeutsch;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    //Handles playback of all the sound files
    MediaPlayer mMediaPlayer;
    //Handles audio focus when playing a sound file
    AudioManager am;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    // Pause playback and reset player to the start of the file. That way, we can
                    // play the word from the beginning when we resume playback.
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    // We have regained focus and can resume playback.
                    mMediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    // Stop playback and clean up resources
                    releaseMediaPlayer();
                    break;
            }
        }
    };


    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            mMediaPlayer.release();

            mMediaPlayer = null;

            am.abandonAudioFocus(afChangeListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        ArrayList<WordItem> colorsList = new ArrayList<>();
        colorsList.add(new WordItem("красный", "rot", R.drawable.color_red, R.raw.color_rot));
        colorsList.add(new WordItem("желтый", "gelb", R.drawable.color_mustard_yellow, R.raw.color_gelb));
        colorsList.add(new WordItem("зеленый", "grün", R.drawable.color_green, R.raw.color_grun));
        colorsList.add(new WordItem("светло-зеленый", "hellgrün", R.drawable.color_light_green, R.raw.color_hellgrun));
        colorsList.add(new WordItem("темно-зеленый", "dunkelgrün", R.drawable.color_dark_green, R.raw.color_dunkelgrun));
        colorsList.add(new WordItem("синий", "blau", R.drawable.color_blue, R.raw.color_blau));
        colorsList.add(new WordItem("голубой", "hellblau", R.drawable.color_light_blue, R.raw.color_hellblau));
        colorsList.add(new WordItem("темно-синий", "dunkelblau", R.drawable.color_dark_blue, R.raw.color_dunkelblau));
        colorsList.add(new WordItem("бирюзовый", "türkis", R.drawable.color_turquiose, R.raw.color_turkis));
        colorsList.add(new WordItem("фиолетовый", "lila/violett", R.drawable.color_lila, R.raw.color_lila));
        colorsList.add(new WordItem("розовый", "rosa/pink", R.drawable.color_pink, R.raw.color_pink));
        colorsList.add(new WordItem("серый", "grau", R.drawable.color_gray, R.raw.color_grau));
        colorsList.add(new WordItem("коричневый", "braun", R.drawable.color_brown, R.raw.color_braun));
        colorsList.add(new WordItem("оранжевый", "orange", R.drawable.color_orange, R.raw.color_orange));
        colorsList.add(new WordItem("белый", "weiß", R.drawable.color_white, R.raw.color_weiss));
        colorsList.add(new WordItem("черный", "schwarz", R.drawable.color_black, R.raw.color_schwarz));
        colorsList.add(new WordItem("бежевый", "beige", R.drawable.color_dusty_yellow, R.raw.color_beige));
        //colorsList.add(new WordItem());

        WordItemAdapter itemsRAdapter = new WordItemAdapter(this, colorsList, R.color.category_colors);

        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsRAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                releaseMediaPlayer();

                WordItem curItem = (WordItem) listView.getItemAtPosition(position);

                // Request audio focus for playback
                int result = am.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback
                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this, curItem.getAudioSrc());
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
