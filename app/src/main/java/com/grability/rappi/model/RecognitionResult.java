package com.grability.rappi.model;

import java.io.Serializable;

/**
 * Created by jorgesanmartin on 2/26/16.
 */
public class RecognitionResult implements Serializable {

    private final String result;
    private final String messageResult;
    private final int numberWords;
    private final int correctWords;
    private final int incorrectWords;

    public RecognitionResult(String result, String messageResult, int numberWords, int correctWords, int incorrectWords) {
        this.result = result;
        this.messageResult = messageResult;
        this.numberWords = numberWords;
        this.correctWords = correctWords;
        this.incorrectWords = incorrectWords;
    }

    public int getNumberWords() {
        return numberWords;
    }

    public int getCorrectWords() {
        return correctWords;
    }

    public int getIncorrectWords() {
        return incorrectWords;
    }

    public String getResult() {
        return result;
    }

    public String getMessageResult() {
        return messageResult;
    }
}
