package com.sportradar.impl;

import com.sportradar.Scoreboard;
import com.sportradar.ScoreboardSummary;
import com.sportradar.impl.storage.ScoreboardStorage;
import com.sportradar.model.Match;
import com.sportradar.model.Score;
import com.sportradar.model.Team;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Comparator;
import java.util.List;

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

        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("Can't start match with one team");
        }

        final var newMatch = new Match(homeTeam, awayTeam);
        scoreboardStorage.add(newMatch, zeroScore());

        return newMatch;
    }

    @Override
    public void updateScore(Match match, Score score) {
        requireNonNull(match);
        requireNonNull(score);

        final var maybeCurrentScore = scoreboardStorage.get(match);

        if (maybeCurrentScore.isEmpty()) {
            throw new IllegalStateException("Unable to update score for non-existing match");
        }

        final var currentScore = maybeCurrentScore.get();

        if (currentScore.equals(score)) {
            throw new IllegalArgumentException("Can't set the same score as current");
        }

        if (currentScore.homeTeamScore() > score.homeTeamScore() || currentScore.awayTeamScore() > score.awayTeamScore()) {
            throw new IllegalStateException("Unable to decrease score");
        }

        scoreboardStorage.update(match, score);
    }

    @Override
    public void finishMatch(Match match) {
        requireNonNull(match);

        scoreboardStorage
                .get(match)
                .ifPresentOrElse(__ -> scoreboardStorage.remove(match), () -> {
                    throw new IllegalStateException("Unable to finish match which is not currently in progress");
                });
    }

    @Override
    public ScoreboardSummary getSummary() {
        final var summaryListView = scoreboardStorage.getAll();
        final var sortedMatches = summaryListView
                .stream()
                .sorted(getComparator(summaryListView))
                .toList();
        return new ScoreboardSummary(sortedMatches);
    }

    private Comparator<Pair<Match, Score>> getComparator(List<Pair<Match, Score>> summaryListView) {
        return Comparator.<Pair<Match, Score>>comparingInt(pair -> calculateTotalScore(pair.getValue())).reversed()
                .thenComparing(Comparator.<Pair<Match, Score>>comparingInt(summaryListView::indexOf).reversed());
    }

    private int calculateTotalScore(Score score) {
        return score.homeTeamScore() + score.awayTeamScore();
    }
}
