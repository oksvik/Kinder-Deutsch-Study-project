package com.example.dudar.mydeutsch;

/**
 * Class represents a vocabulary word that the user want to learn.
 * It contains a default translation and a Deutsch translation for that word.
 */

public class WordItem {
    /** Default transation for the word*/
    private String mDefaultTranslation;
    /** Deutsch translation for the word*/
    private String mDeutschTranslation;

    private int mImageSrc = NO_IMAGE_PROVIDED;

    private int mAudioSrc;

    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;


    public WordItem (String defTr, String deutTr){
        mDefaultTranslation = defTr;
        mDeutschTranslation = deutTr;
    }

    public WordItem (String defTr, String deutTr, int audioId){
        mDefaultTranslation = defTr;
        mDeutschTranslation = deutTr;
        mAudioSrc = audioId;
    }

    public WordItem (String defTr, String deutTr, int imgId, int audioId){
        mDefaultTranslation = defTr;
        mDeutschTranslation = deutTr;
        mImageSrc = imgId;
        mAudioSrc = audioId;
    }

    /** Get default translation of the word.*/
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /** Get deutsch translation of the word.*/
    public String getDeutschTranslation() {
        return mDeutschTranslation;
    }

    public int getImageSrc(){return mImageSrc;}

    public int getAudioSrc(){return mAudioSrc;}
    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage() {
        return mImageSrc != NO_IMAGE_PROVIDED;
    }

    @Override
    public String toString() {
        return "WordItem{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mDeutschTranslation='" + mDeutschTranslation + '\'' +
                ", mImageSrc=" + mImageSrc +
                ", mAudioSrc=" + mAudioSrc +
                '}';
    }
}
