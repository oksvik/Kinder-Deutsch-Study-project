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

public class FamilyActivity extends AppCompatActivity {

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

        ArrayList<WordItem> familyList = new ArrayList<>();
        familyList.add(new WordItem("мама", "Mutter", R.drawable.family_mother, R.raw.family_mutter));
        familyList.add(new WordItem("папа", "Vater", R.drawable.family_father, R.raw.family_vater));
        familyList.add(new WordItem("сын", "Sohn", R.drawable.family_son, R.raw.family_sohn));
        familyList.add(new WordItem("дочь", "Tochter", R.drawable.family_daughter, R.raw.family_tochter));
        familyList.add(new WordItem("дедушка", "Opa", R.drawable.family_grandfather, R.raw.family_opa));
        familyList.add(new WordItem("бабушка", "Oma", R.drawable.family_grandmother, R.raw.family_oma));
        familyList.add(new WordItem("брат", "Bruder", R.drawable.family_younger_brother, R.raw.family_bruder));
        familyList.add(new WordItem("сестра", "Schwester", R.drawable.family_older_sister, R.raw.family_schwester));
        familyList.add(new WordItem("братья и сестры", "Geschwister", R.drawable.family_geschwister, R.raw.family_geschwister));
        familyList.add(new WordItem("старший брат", "älterer Bruder", R.drawable.family_older_brother, R.raw.family_alterer_bruder));
        familyList.add(new WordItem("младшая сестра", "jüngere Schwester", R.drawable.family_younger_sister, R.raw.family_jungere_schwester));
        familyList.add(new WordItem("родители", "Eltern", R.drawable.family_parents, R.raw.family_eltern));
        familyList.add(new WordItem("дети", "Kinder", R.drawable.family_children, R.raw.family_kinder));
        familyList.add(new WordItem("дядя", "Onkel", R.drawable.family_uncle, R.raw.family_onkel));
        familyList.add(new WordItem("тетя", "Tante", R.drawable.family_aunt, R.raw.family_tante));
        familyList.add(new WordItem("двоюродный брат", "Cousin", R.drawable.family_older_brother, R.raw.family_cousin));
        familyList.add(new WordItem("двоюродная сестра", "Cousine", R.drawable.family_older_sister, R.raw.family_cousine));
        //familyList.add(new WordItem("",""));

        WordItemAdapter itemsRAdapter = new WordItemAdapter(this, familyList, R.color.category_family);

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
                    mMediaPlayer = MediaPlayer.create(FamilyActivity.this, curItem.getAudioSrc());
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

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
             mMediaPlayer.release();

            mMediaPlayer = null;

            am.abandonAudioFocus(afChangeListener);
        }
    }
}
