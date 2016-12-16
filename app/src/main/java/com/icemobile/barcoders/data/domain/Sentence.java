package com.icemobile.barcoders.data.domain;

public class Sentence {
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

}
