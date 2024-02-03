package com.sportradar.model;

import static java.util.Objects.requireNonNull;

public record TeamScore(Team team, Score score) {
    public TeamScore {
        requireNonNull(team);
        requireNonNull(score);
    }
}
