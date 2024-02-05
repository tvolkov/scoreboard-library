package com.sportradar.impl.storage;

import com.sportradar.model.Match;
import com.sportradar.model.Score;
import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class SimpleScoreboardStorage implements ScoreboardStorage {

    private final Map<Match, Score> storage;

    public SimpleScoreboardStorage(Map<Match, Score> storage) {
        this.storage = storage;
    }

    public SimpleScoreboardStorage() {
        this.storage = new LinkedHashMap<>();
    }

    @Override
    public void add(Match match, Score score) {
        requireNonNull(match);
        requireNonNull(score);
        storage.put(match, score);
    }

    @Override
    public void remove(Match match) {
        requireNonNull(match);
        storage.remove(match);
    }

    @Override
    public Optional<Score> get(Match match) {
        requireNonNull(match);
        return Optional.ofNullable(storage.get(match));
    }

    @Override
    public void update(Match match, Score score) {
        requireNonNull(match);
        requireNonNull(score);
        storage.put(match, score);
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
