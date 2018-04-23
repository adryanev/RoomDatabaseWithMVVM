package com.circlenode.roomtest.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.circlenode.roomtest.data.dao.WordDao;
import com.circlenode.roomtest.data.model.Word;

import java.util.List;

public class WordRepository {

    private WordDao wordDao;
    private LiveData<List<Word>> allWord;

    public WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        wordDao = db.wordDao();
        allWord = wordDao.getAllWord();
    }

    public LiveData<List<Word>> getAllWord(){
        return allWord;
    }

    public void insert(Word word){
        new insertAsyncTask(wordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word,Void,Void> {
        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.insertWord(words[0]);
            return null;
        }
    }
}
