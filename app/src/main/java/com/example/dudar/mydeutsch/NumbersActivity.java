package com.example.dudar.mydeutsch;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


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

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a sing le {@link TextView}, which the adapter will set to
        // display a single word.
        WordItemAdapter itemsRAdapter = new WordItemAdapter(this, words, R.color.category_numbers);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml file.
        final ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(itemsRAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                WordItem curItem = (WordItem) listView.getItemAtPosition(position);
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, curItem.getAudioSrc());
                mMediaPlayer.start();

                // Setup a listener on the media player, so that we can stop and release the
                // media player once the sound has finished playing.
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });


        //Log.v("NumberActivity","Element number 0: " + deutschWords.get(0));
        //Log.v("NumberActivity","Element number 1: " + deutschWords.get(1));

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
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}
