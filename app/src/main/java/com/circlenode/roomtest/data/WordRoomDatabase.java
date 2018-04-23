package com.circlenode.roomtest.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.circlenode.roomtest.data.dao.WordDao;
import com.circlenode.roomtest.data.model.Word;

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract WordDao wordDao();
    private static WordRoomDatabase INSTANCE;

    static WordRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (WordRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),WordRoomDatabase.class,"word_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(mRoomDatabaseCallBack)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback mRoomDatabaseCallBack = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
            private final WordDao mDao;
        PopulateDbAsync(WordRoomDatabase db){
            mDao = db.wordDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            Word word = new Word("Hello");
            mDao.insertWord(word);
            Word word1 = new Word("World");
            mDao.insertWord(word);

            return null;
        }
    }
}
