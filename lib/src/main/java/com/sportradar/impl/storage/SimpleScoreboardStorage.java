package com.sportradar.impl.storage;

import com.sportradar.model.Match;
import com.sportradar.model.Team;

import java.util.Collection;

public class SimpleScoreboardStorage implements ScoreboardStorage {
    @Override
    public void add(Match match) {

    }

    @Override
    public void remove(Match match) {

    }

    @Override
    public boolean hasMatchOfTeams(Team homeTeam, Team awayTeam) {
        return false;
    }

    @Override
    public void update(Match match) {

    }
    @Override
    public Collection<Match> get() {
        return null;
    }
}
