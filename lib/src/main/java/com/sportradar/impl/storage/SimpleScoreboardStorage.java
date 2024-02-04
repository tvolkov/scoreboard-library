package com.sportradar.impl.storage;

import com.sportradar.model.Match;
import com.sportradar.model.Score;

import java.util.LinkedHashMap;
import java.util.Optional;

public class SimpleScoreboardStorage implements ScoreboardStorage {

    @Override
    public void add(Match match, Score score) {

    }

    @Override
    public void remove(Match match) {

    }

    @Override
    public Optional<Score> get(Match match) {
        return Optional.empty();
    }

    @Override
    public void update(Match match, Score score) {

    }
    @Override
    public LinkedHashMap<Match, Score> getAll() {
        return null;
    }
}
