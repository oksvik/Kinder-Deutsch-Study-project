package com.example.dudar.mydeutsch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<WordItem> colorsList = new ArrayList<>();
        colorsList.add(new WordItem("красный","rot",R.drawable.color_red));
        colorsList.add(new WordItem("желтый","gelb",R.drawable.color_mustard_yellow));
        colorsList.add(new WordItem("зеленый","grün",R.drawable.color_green));
        colorsList.add(new WordItem("светло-зеленый","hellgrün",R.drawable.color_light_green));
        colorsList.add(new WordItem("темно-зеленый","dunkelgrün",R.drawable.color_dark_green));
        colorsList.add(new WordItem("синий","blau",R.drawable.color_blue));
        colorsList.add(new WordItem("голубой","hellblau",R.drawable.color_light_blue));
        colorsList.add(new WordItem("темно-синий","dunkelblau",R.drawable.color_dark_blue));
        colorsList.add(new WordItem("бирюзовый","türkis",R.drawable.color_turquiose));
        colorsList.add(new WordItem("фиолетовый","lila/violett",R.drawable.color_lila));
        colorsList.add(new WordItem("розовый","rosa/pink",R.drawable.color_pink));
        colorsList.add(new WordItem("серый","grau",R.drawable.color_gray));
        colorsList.add(new WordItem("коричневый","braun",R.drawable.color_brown));
        colorsList.add(new WordItem("оранжевый","orange",R.drawable.color_orange));
        colorsList.add(new WordItem("белый","weiß",R.drawable.color_white));
        colorsList.add(new WordItem("черный","schwarz",R.drawable.color_black));
        colorsList.add(new WordItem("бежевый","beige",R.drawable.color_dusty_yellow));
        //colorsList.add(new WordItem());

        WordItemAdapter itemsRAdapter = new WordItemAdapter(this,colorsList,R.color.category_colors);

        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(itemsRAdapter);
    }
}
