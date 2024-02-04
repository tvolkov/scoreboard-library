package com.sportradar.impl.storage;

import com.sportradar.model.Match;
import com.sportradar.model.Score;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SimpleScoreboardStorage implements ScoreboardStorage {

    private final Map<Match, Score> storage = new LinkedHashMap<>();

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
    public List<Pair<Match, Score>> getAll() {
        return storage
                .entrySet()
                .stream()
                .map(Pair::of)
                .toList();
    }
}
