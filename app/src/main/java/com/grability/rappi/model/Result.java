package com.grability.rappi.model;

import java.io.Serializable;

/**
 * Created by jorgesanmartin on 4/12/16.
 */
public class Result implements Serializable {

    private final int minSpeed;
    private final int maxSpeed;
    private final int textResult;

    public Result(int minSpeed, int maxSpeed, int textResult) {
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.textResult = textResult;
    }

    private Result(ResultBuilder builder) {
        this.minSpeed = builder.minSpeed;
        this.maxSpeed = builder.maxSpeed;
        this.textResult = builder.textResult;
    }

    public int getMinSpeed() {
        return minSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getTextResult() {
        return textResult;
    }

    public static class ResultBuilder {
        private final int minSpeed;
        private final int maxSpeed;
        private final int textResult;

        public ResultBuilder(int minSpeed, int maxSpeed, int textResult) {
            this.minSpeed = minSpeed;
            this.maxSpeed = maxSpeed;
            this.textResult = textResult;
        }

        public Result build() {
            return new Result(this);
        }
    }
}
