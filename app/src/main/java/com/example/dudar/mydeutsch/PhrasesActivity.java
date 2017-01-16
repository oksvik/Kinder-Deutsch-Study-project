package com.example.dudar.mydeutsch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<WordItem> phrasesRList = new ArrayList<WordItem>();
        phrasesRList.add(new WordItem("Как тебя зовут?","Wie heißt du? "));
        phrasesRList.add(new WordItem("Меня зовут...","Ich heiße"));
        phrasesRList.add(new WordItem("Как дела?","Wie geht's?"));
        phrasesRList.add(new WordItem("Сколько тебе лет?","Wie alt bist du?"));
        phrasesRList.add(new WordItem("Мне 5 лет.","Ich bin 5 Jahre alt."));
        phrasesRList.add(new WordItem("Сколько это стоит?","Wie viel kostet das?"));
        phrasesRList.add(new WordItem("Это стоит 2 евро.","Das kostet 2 euro."));
        phrasesRList.add(new WordItem("Можешь мне помочь?","Kannst du mir helfen?"));
        phrasesRList.add(new WordItem("Я могу...","Ich kann..."));
        phrasesRList.add(new WordItem("Я не могу...","Ich kann ... nicht"));
        phrasesRList.add(new WordItem("Я не хочу","Ich will nicht"));
        phrasesRList.add(new WordItem("Я боюсь (мне страшно).","Ich habe Angst."));
        phrasesRList.add(new WordItem("Приятно познакомится (с тобой)!","Es freut mich, dich kennenzulernen!"));

        WordItemAdapter itemsRAdapter = new WordItemAdapter(this,phrasesRList,R.color.category_phrases);

        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(itemsRAdapter);
    }
}
