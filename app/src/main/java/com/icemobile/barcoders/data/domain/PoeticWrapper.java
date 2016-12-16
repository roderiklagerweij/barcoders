package com.icemobile.barcoders.data.domain;

public class PoeticWrapper {

    private final Poetic poetic;
    private final SentenceType sentenceType;

    public PoeticWrapper(Poetic poetic, SentenceType sentenceType) {
        this.poetic = poetic;
        this.sentenceType = sentenceType;
    }

    public Poetic getPoetic() {
        return poetic;
    }

    public SentenceType getSentenceType() {
        return sentenceType;
    }
}
