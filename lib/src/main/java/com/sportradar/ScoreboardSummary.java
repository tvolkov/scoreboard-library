package com.sportradar;

import com.sportradar.model.Match;
import com.sportradar.model.Score;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;
import static java.util.Objects.requireNonNull;

public record ScoreboardSummary(List<Pair<Match, Score>> scoreboardView) {

    public ScoreboardSummary {
        requireNonNull(scoreboardView);
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
