package com.dmoneyextreme.jokerama;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JokeLab {
    private static JokeLab sJokeLab;
    private List<Joke> mJokes;

    public static JokeLab get(Context context){
        if(sJokeLab == null){
            sJokeLab = new JokeLab(context);
        }
        return sJokeLab;
    }

    private JokeLab(Context context){
        mJokes = new ArrayList<>();

        for(int i = 0; i < 100; i++){
            Joke joke = new Joke();
            joke.setmTitle("Big Funny #" + i);
            joke.writeJoke(new String[]{"Knock Knock!", "Whom is there?", "Recursion.", "Recursion who?", "Recursion"});
            mJokes.add(joke);
        }
    }

    public List<Joke> getJokes(){
        return mJokes;
    }

    public Joke getJoke(UUID id){
        for(Joke j : mJokes){
            if(j.getmId().equals(id)){
                return j;
            }
        }
        return null;
    }

    public int numberCompleted(){
        int completed = 0;
        for(Joke j : mJokes){
            if(j.completed()){
                completed++;
            }
        }
        return completed;
    }

    public void reset(){
        for(Joke j : mJokes){
            j.setmProgress(0);
        }
    }

}
