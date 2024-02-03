package com.sportradar.model;

public record Score(int value) {
    public Score {
        if (value < 0) {
            throw new IllegalArgumentException("Score can't be negative");
        }
    }
}
