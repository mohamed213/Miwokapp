package com.example.osama.miwokapp;

import android.content.Context;

/**
 * Created by osama on 8/27/2016.
 */
public class word {
    private String EnglishWord;
    private String MiwakWord;
    private int ImageId= NOIMAGESTATE;
    private static final int NOIMAGESTATE= -1;
    private  int ResId;



    public word (String englishWord , String miwakWord, int resId){
       EnglishWord= englishWord;
        MiwakWord= miwakWord;
        ResId= resId;
    }
    public word (String englishWord , String miwakWord, int mImageId,int resId){
        EnglishWord= englishWord;
        MiwakWord= miwakWord;
        ImageId = mImageId;
        ResId= resId;

    }

    public int getImageId() {
        return ImageId;
    }

    public String getEnglishWord() {
        return EnglishWord;
    }

    public String getMiwakWord() {
        return MiwakWord;
    }
    public boolean hasimage (){
        if(ImageId!= NOIMAGESTATE){
            return true;
        }
        else return false;
    }
    public int getResId(){
        return ResId;
    }

    @Override
    public String toString() {
        return "word{" +
                "EnglishWord='" + EnglishWord + '\'' +
                ", MiwakWord='" + MiwakWord + '\'' +
                ", ImageId=" + ImageId +
                ", ResId=" + ResId +
                '}';
    }
}
