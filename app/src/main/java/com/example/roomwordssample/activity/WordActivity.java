package com.example.roomwordssample.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomwordssample.R;
import com.example.roomwordssample.entity.Word;
import com.example.roomwordssample.viewmodel.WordViewModel;

public class WordActivity extends AppCompatActivity {

    private EditText mEditWordView;
    private String word;
    private int id;
    private Button button_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        mEditWordView = findViewById(R.id.edit_word);
        button_delete = findViewById(R.id.button_delete);

        Intent intent = getIntent();
        word = intent.getStringExtra("word");
        id = intent.getIntExtra("id",0);
        if (id != 0){
            mEditWordView.setText(word);
            button_delete.setVisibility(View.VISIBLE);
        }
    }


    public void SaveClick(View view) {
        WordViewModel mWordViewModel = new WordViewModel(getApplication());
        Word word = new Word(mEditWordView.getText().toString());

        if (id != 0){
            word.setID(id);
            mWordViewModel.update(word);
        } else {
            mWordViewModel.insert(word);

        }


        finish();
    }

    public void DeleteClick(View view) {
        WordViewModel mWordViewModel = new WordViewModel(getApplication());
        Word word = new Word(mEditWordView.getText().toString());
        word.setID(id);
        mWordViewModel.delete(word);

        finish();
    }
}