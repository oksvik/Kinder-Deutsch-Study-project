package com.example.dudar.mydeutsch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);



        ArrayList<WordItem> familyList = new ArrayList<>();
        familyList.add(new WordItem("мама","Mama",R.drawable.family_mother));
        familyList.add(new WordItem("папа","Papa",R.drawable.family_father));
        familyList.add(new WordItem("сын","Sohn",R.drawable.family_son));
        familyList.add(new WordItem("дочь","Tochter",R.drawable.family_daughter));
        familyList.add(new WordItem("дедушка","Opa",R.drawable.family_grandfather));
        familyList.add(new WordItem("бабушка","Oma",R.drawable.family_grandmother));
        familyList.add(new WordItem("брат","Bruder",R.drawable.family_younger_brother));
        familyList.add(new WordItem("сестра","Schwester",R.drawable.family_older_sister));
        familyList.add(new WordItem("братья и сестры","Geschwister"));
        familyList.add(new WordItem("старший брат","älterer Bruder",R.drawable.family_older_brother));
        familyList.add(new WordItem("младшая сестра","jüngere Schwester",R.drawable.family_younger_sister));
        familyList.add(new WordItem("родители","Eltern",R.drawable.family_parents));
        familyList.add(new WordItem("дети","Kinder",R.drawable.family_children));
        familyList.add(new WordItem("дядя","Onkel",R.drawable.family_uncle));
        familyList.add(new WordItem("тетя","Tante",R.drawable.family_aunt));
        familyList.add(new WordItem("двоюродный брат","Cousin",R.drawable.family_older_brother));
        familyList.add(new WordItem("двоюродная сестра","Cousine",R.drawable.family_older_sister));
        //familyList.add(new WordItem("",""));

        WordItemAdapter itemsRAdapter = new WordItemAdapter(this,familyList,R.color.category_family);

        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(itemsRAdapter);

    }
}
