package com.sportradar.model;

import static java.util.Objects.requireNonNull;

public record Team(TeamName teamName, Score score) {
    public Team {
        requireNonNull(teamName);
        requireNonNull(score);
    }
}
