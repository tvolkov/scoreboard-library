package com.sportradar.model;

import static java.util.Objects.requireNonNull;

public record Match(Team homeTeam, Team awayTeam) {
    public Match {
        requireNonNull(homeTeam);
        requireNonNull(awayTeam);
    }
}
