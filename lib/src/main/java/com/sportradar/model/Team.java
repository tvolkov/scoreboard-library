package com.sportradar.model;

import static java.util.Objects.requireNonNull;

public record Team(TeamName teamName) {
    public Team {
        requireNonNull(teamName);
    }
}
