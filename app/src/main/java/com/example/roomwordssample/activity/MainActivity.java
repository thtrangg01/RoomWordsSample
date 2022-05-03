package com.example.roomwordssample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.roomwordssample.R;
import com.example.roomwordssample.adapter.WordListAdapter;
import com.example.roomwordssample.viewmodel.WordViewModel;

public class MainActivity extends AppCompatActivity {

    private WordViewModel mWordViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWordViewModel = new WordViewModel(getApplication());

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WordListAdapter adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        adapter.setWords(mWordViewModel.getAllWords());

        mWordViewModel.getAllWords().observe(this, words -> {
            adapter.setWords(words);
        });
    }

    public void addNewWord(View view) {

        Intent intent = new Intent(this, WordActivity.class);
        startActivity(intent);
    }
}