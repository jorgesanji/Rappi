package com.grability.rappi.model.assets;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class Phrase implements Serializable {

    @SerializedName("instructions")
    private String instructions = "";

    @SerializedName("text")
    private String text = "";

    public Phrase(String instructions, String text) {
        this.instructions = instructions;
        this.text = text;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
