package com.example.roomwordssample.database;

import android.content.Context;
import android.content.SharedPreferences;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.roomwordssample.dao.WordDAO;
import com.example.roomwordssample.entity.Word;


@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {

    public abstract WordDAO wordDao();

    private static MyRoomDatabase mWordRoomDatabase;

    static SharedPreferences mPreferences;

    public static MyRoomDatabase getDatabase(final Context context) {
        mPreferences = context.getSharedPreferences("myPrefs", context.MODE_PRIVATE);

        if (mWordRoomDatabase == null) {
            synchronized (MyRoomDatabase.class) {
                if (mWordRoomDatabase == null) {
                    // Create database here
                    // Create database here
                    mWordRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            MyRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return mWordRoomDatabase;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            boolean initedDB = mPreferences.getBoolean("initedDB", false);


            if (!initedDB) {
                new Thread(() -> {
                    final WordDAO mDao = mWordRoomDatabase.wordDao();
                    mDao.deleteAll();
                    String[] words = {"dolphin", "crocodile", "cobra"};
                    for (int i = 0; i <= words.length - 1; i++) {
                        Word word = new Word(words[i]);
                        mDao.insert(word);
                    }
                    mPreferences.edit().putBoolean("initedDB", true).commit();
                }).start();
            }
        }
    };


}
