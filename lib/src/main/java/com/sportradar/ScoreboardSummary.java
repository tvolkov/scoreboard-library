package com.sportradar;

import com.sportradar.model.Match;
import com.sportradar.model.Score;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;
import static java.util.Objects.requireNonNull;

public class ScoreboardSummary {

    private final List<Map.Entry<Match, Score>> scoreboardView;

    public ScoreboardSummary(List<Map.Entry<Match, Score>> scoreboardView) {
        requireNonNull(scoreboardView);
        this.scoreboardView = scoreboardView;
    }

    public String print() {
        return scoreboardView
                .stream()
                .map(entry -> {
                    final var match = entry.getKey();
                    final var score = entry.getValue();
                    return String.format("%d. %s %d - %s %d", scoreboardView.indexOf(entry) + 1, match.homeTeam().teamName().value(), score.homeTeamScore(), match.awayTeam().teamName().value(), score.awayTeamScore());
                })
                .collect(Collectors.joining(lineSeparator()));
    }
}
