package com.sportradar.impl.storage;

import com.sportradar.model.Match;

import java.util.Collection;

public interface ScoreboardStorage {

    void add(Match match);
    void remove(Match match);
    Collection<Match> get();
}
