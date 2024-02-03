package com.sportradar.model;

public record Score(int value) {
    public Score {
        if (value < 0) {
            throw new IllegalArgumentException("Score can't be negative");
        }
    }

    public static Score of(int value) {
        return new Score(value);
    }

    public static Score zeroScore() {
        return new Score(0);
    }
}
