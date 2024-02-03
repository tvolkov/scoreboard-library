package com.sportradar.impl;

import com.sportradar.Scoreboard;
import com.sportradar.ScoreboardSummary;
import com.sportradar.impl.storage.ScoreboardStorage;
import com.sportradar.model.Match;
import com.sportradar.model.Team;
import com.sportradar.model.TeamScore;

import static com.sportradar.model.Score.zeroScore;
import static java.util.Objects.requireNonNull;

public class ScoreboardImpl implements Scoreboard {

    private final ScoreboardStorage scoreboardStorage;

    public ScoreboardImpl(ScoreboardStorage scoreboardStorage) {
        this.scoreboardStorage = requireNonNull(scoreboardStorage);
    }

    @Override
    public Match startNewMatch(Team homeTeam, Team awayTeam) {
        requireNonNull(homeTeam);
        requireNonNull(awayTeam);

        final var newMatch = new Match(new TeamScore(homeTeam, zeroScore()), new TeamScore(awayTeam, zeroScore()));
        scoreboardStorage.add(newMatch);

        return newMatch;
    }

    @Override
    public void updateScore(Match match) {

    }

    @Override
    public void finishMatch(Match match) {
        requireNonNull(match);

        if (scoreboardStorage.has(match)) {
            throw new IllegalStateException("Unable to finish match which is not currently in progress");
        }

        scoreboardStorage.remove(match);
    }

    @Override
    public ScoreboardSummary getSummary() {
        return null;
    }
}
