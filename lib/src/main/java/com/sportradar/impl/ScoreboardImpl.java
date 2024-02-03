package com.sportradar.impl;

import com.sportradar.Scoreboard;
import com.sportradar.ScoreboardSummary;
import com.sportradar.impl.storage.ScoreboardStorage;
import com.sportradar.model.Match;
import com.sportradar.model.Team;

import java.util.Objects;

public class ScoreboardImpl implements Scoreboard {

    private final ScoreboardStorage scoreboardStorage;

    public ScoreboardImpl(ScoreboardStorage scoreboardStorage) {
        this.scoreboardStorage = Objects.requireNonNull(scoreboardStorage);
    }

    @Override
    public Match startNewMatch(Team homeTeam, Team awayTeam) {
        return null;
    }

    @Override
    public void updateScore(Match match) {

    }

    @Override
    public void finishMatch(Match match) {

    }

    @Override
    public ScoreboardSummary getSummary() {
        return null;
    }
}
