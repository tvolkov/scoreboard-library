package com.sportradar.impl.storage;

import com.sportradar.model.Match;
import com.sportradar.model.Score;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ScoreboardStorage {

    void add(Match match, Score score);
    void remove(Match match);
    Optional<Score> get(Match match);
    void update(Match match, Score score);
    List<Pair<Match, Score>> getAll();
}
