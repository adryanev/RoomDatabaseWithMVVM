package com.circlenode.roomtest.ui.mainactivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.circlenode.roomtest.R;
import com.circlenode.roomtest.data.model.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.MyViewHolder> {

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItem;
        public MyViewHolder(View itemView) {
            super(itemView);
            wordItem = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater layoutInflater;
    private List<Word> mWord;
    WordListAdapter(Context context) { layoutInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recyclerview_items,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if(mWord!=null){
            Word currentWord = mWord.get(position);
            holder.wordItem.setText(currentWord.getWord());

        }else{
            holder.wordItem.setText("No Item Available.");
        }

    }

    void setWords(List<Word> words){
        mWord = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mWord != null)
            return mWord.size();
        else return 0;
    }


}
