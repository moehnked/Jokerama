package com.dmoneyextreme.jokerama;

import java.util.Random;
import java.util.UUID;

public class Joke {
    private UUID mId;
    private String mTitle;

    public int getmProgress() {
        return mProgress;
    }

    public void setmProgress(int mProgress) {
        this.mProgress = mProgress;
    }

    public void next(){
        this.mProgress++;
    }

    private int mProgress;

    public String[] getmLines() {
        return mLines;
    }

    public void writeJoke(String[] mLines) {
        this.mLines = mLines;
    }

    private String[] mLines;

    public Joke(){
        mId = UUID.randomUUID();
        mProgress = 0;
    }

    public UUID getmId() {
        return mId;
    }

    public void setmId(UUID mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public boolean completed(){
        if(mProgress >= (mLines.length - 1)){
            return true;
        }
        return false;
    }
}
