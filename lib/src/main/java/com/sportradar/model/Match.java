package com.sportradar.model;

import static java.util.Objects.requireNonNull;

public record Match(TeamScore homeTeamScore, TeamScore awayTeamScore) {
    public Match {
        requireNonNull(homeTeamScore);
        requireNonNull(awayTeamScore);
    }
}
