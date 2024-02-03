package com.sportradar.model;

import static java.util.Objects.requireNonNull;

//todo remove, just use Team with String value for name
public record TeamName(String value) {
    public TeamName {
        requireNonNull(value);
    }
}
