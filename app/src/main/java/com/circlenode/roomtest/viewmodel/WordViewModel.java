package com.circlenode.roomtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.circlenode.roomtest.data.WordRepository;
import com.circlenode.roomtest.data.model.Word;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository wordRepository;
    private LiveData<List<Word>> mAllWord;

    public WordViewModel(@NonNull Application application) {
        super(application);
            wordRepository = new WordRepository(application);
            mAllWord = wordRepository.getAllWord();
    }

    public LiveData<List<Word>> getAllWord(){
        return mAllWord;
    }

    public void insert(Word word){
        wordRepository.insert(word);
    }
}
