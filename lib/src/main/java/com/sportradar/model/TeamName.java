package com.sportradar.model;

import static java.util.Objects.requireNonNull;

public record TeamName(String value) {
    public TeamName {
        requireNonNull(value);
    }
}
