package com.sportradar.model;

public record Score(int homeTeamScore, int awayTeamScore) {
    public Score {
        if (homeTeamScore < 0 || awayTeamScore < 0) {
            throw new IllegalArgumentException("Score can't be negative");
        }
    }

    public static Score of(int homeTeamScore, int awayTeamScore) {
        return new Score(homeTeamScore, awayTeamScore);
    }

    public static Score zeroScore() {
        return new Score(0, 0);
    }
}
