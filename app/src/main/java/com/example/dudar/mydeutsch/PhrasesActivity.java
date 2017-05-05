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

public class PhrasesActivity extends AppCompatActivity {

    //Handles playback of all the sound files
    MediaPlayer mMediaPlayer;
    //Handles audio focus when playing a sound file
    AudioManager am;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                     mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    mMediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
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

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            am.abandonAudioFocus(afChangeListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        ArrayList<WordItem> phrasesRList = new ArrayList<WordItem>();
        phrasesRList.add(new WordItem("Как тебя зовут?", "Wie heißt du? ", R.raw.phrases_wie_heisst));
        phrasesRList.add(new WordItem("Меня зовут...", "Ich heiße", R.raw.phrases_ich_heisse));
        phrasesRList.add(new WordItem("Как дела?", "Wie geht's?", R.raw.phrases_wie_gehts));
        phrasesRList.add(new WordItem("Сколько тебе лет?", "Wie alt bist du?", R.raw.phrases_wie_alt));
        phrasesRList.add(new WordItem("Мне 5 лет.", "Ich bin 5 Jahre alt.", R.raw.phrases_ich_5));
        phrasesRList.add(new WordItem("Сколько это стоит?", "Wie viel kostet das?", R.raw.phrases_wie_kostet));
        phrasesRList.add(new WordItem("Это стоит 2 евро.", "Das kostet 2 euro.", R.raw.phrases_das_kostet));
        phrasesRList.add(new WordItem("Можешь мне помочь?", "Kannst du mir helfen?", R.raw.phrases_kannst_helfen));
        phrasesRList.add(new WordItem("Я могу...", "Ich kann...", R.raw.phrases_ich_kann));
        phrasesRList.add(new WordItem("Я не могу...", "Ich kann ... nicht", R.raw.phrases_kann_nicht));
        phrasesRList.add(new WordItem("Я не хочу", "Ich will nicht", R.raw.phrases_will_nicht));
        phrasesRList.add(new WordItem("Я боюсь (мне страшно).", "Ich habe Angst.", R.raw.phrases_habe_angst));
        phrasesRList.add(new WordItem("Приятно познакомится (с тобой)!", "Es freut mich, dich kennenzulernen!", R.raw.phrases_kennenzulernen));

        WordItemAdapter itemsRAdapter = new WordItemAdapter(this, phrasesRList, R.color.category_phrases);

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
                    mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, curItem.getAudioSrc());
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
