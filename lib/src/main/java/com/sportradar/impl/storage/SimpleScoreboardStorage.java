package com.sportradar.impl.storage;

import com.sportradar.model.Match;
import com.sportradar.model.Score;
import com.sportradar.model.Team;

import java.util.Collection;
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
    public Collection<Match> getAll() {
        return null;
    }
}
