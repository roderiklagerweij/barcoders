package com.icemobile.barcoders.data.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Sentence implements Parcelable {
    private final String text;
    private final String audioFilename;

    public Sentence(String text, String audioFilename) {
        this.text = text;
        this.audioFilename = audioFilename;
    }

    public String getText() {
        return text;
    }

    public String getAudioFilename() {
        return audioFilename;
    }

    protected Sentence(Parcel in) {
        text = in.readString();
        audioFilename = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeString(audioFilename);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Sentence> CREATOR = new Parcelable.Creator<Sentence>() {
        @Override
        public Sentence createFromParcel(Parcel in) {
            return new Sentence(in);
        }

        @Override
        public Sentence[] newArray(int size) {
            return new Sentence[size];
        }
    };

}
