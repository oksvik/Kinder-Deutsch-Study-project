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

public class NumbersActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        ArrayList<WordItem> words = new ArrayList<WordItem>();

        words.add(new WordItem("ноль", "null", R.drawable.number_0, R.raw.number_0));
        words.add(new WordItem("один", "eins", R.drawable.number_1, R.raw.number_1));
        words.add(new WordItem("два", "zwei", R.drawable.number_2, R.raw.number_2));
        words.add(new WordItem("три", "drei", R.drawable.number_3, R.raw.number_3));
        words.add(new WordItem("четыре", "vier", R.drawable.number_4, R.raw.number_4));
        words.add(new WordItem("пять", "fünf", R.drawable.number_5, R.raw.number_5));
        words.add(new WordItem("шесть", "sechs", R.drawable.number_6, R.raw.number_6));
        words.add(new WordItem("семь", "sieben", R.drawable.number_7, R.raw.number_7));
        words.add(new WordItem("восемь", "acht", R.drawable.number_8, R.raw.number_8));
        words.add(new WordItem("девять", "neun", R.drawable.number_9, R.raw.number_9));
        words.add(new WordItem("десять", "zehn", R.drawable.number_10, R.raw.number_10));
        words.add(new WordItem("одиннадцать", "elf", R.drawable.number_11, R.raw.number_11));
        words.add(new WordItem("двенадцать", "zwölf", R.drawable.number_12, R.raw.number_12));
        words.add(new WordItem("тринадцать", "dreizehn", R.drawable.number_13, R.raw.number_13));
        words.add(new WordItem("четырнадцать", "vierzehn", R.drawable.number_14, R.raw.number_14));
        words.add(new WordItem("пятнадцать", "fünfzehn", R.drawable.number_15, R.raw.number_15));
        words.add(new WordItem("шестнадцать", "sechzehn", R.drawable.number_16, R.raw.number_16));
        words.add(new WordItem("семнадцать", "siebzehn", R.drawable.number_17, R.raw.number_17));
        words.add(new WordItem("восемнадцать", "achtzehn", R.drawable.number_18, R.raw.number_18));
        words.add(new WordItem("девятнадцать", "neunzehn", R.drawable.number_19, R.raw.number_19));
        words.add(new WordItem("двадцать", "zwanzig", R.drawable.number_20, R.raw.number_20));
        //words.add(new WordItem("",""));

        WordItemAdapter itemsRAdapter = new WordItemAdapter(this, words, R.color.category_numbers);

        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsRAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                WordItem curItem = (WordItem) listView.getItemAtPosition(position);

                // Request audio focus for playback
                int result = am.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, curItem.getAudioSrc());
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

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            mMediaPlayer.release();

            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            am.abandonAudioFocus(afChangeListener);
        }
    }
}
