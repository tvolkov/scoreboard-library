package com.sportradar.impl;

import com.sportradar.Scoreboard;
import com.sportradar.ScoreboardSummary;
import com.sportradar.model.Score;
import com.sportradar.model.Team;

import java.util.Objects;

public class ScoreboardImpl implements Scoreboard {

    private final ScoreboardStorage scoreboardStorage;

    public ScoreboardImpl(ScoreboardStorage scoreboardStorage) {
        this.scoreboardStorage = Objects.requireNonNull(scoreboardStorage);
    }

    @Override
    public void startNewMatch(Team homeTeam, Team awayTeam) {

    }

    @Override
    public void updateScore(Score homeTeamScore, Score awayTeamScore) {

    }

    @Override
    public void finishMatch(Team homeTeam, Team awayTeam) {

    }

    @Override
    public ScoreboardSummary getSummary() {
        return null;
    }
}
