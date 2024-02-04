package com.sportradar;

import com.sportradar.model.Match;
import com.sportradar.model.Score;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class ScoreboardSummary {

    private final Map<Match, Score> scoreboardView;

    public ScoreboardSummary(Map<Match, Score> scoreboardView) {
        requireNonNull(scoreboardView);
        this.scoreboardView = scoreboardView;
    }

    public String print() {
        final var orderedView = new ArrayList<>(scoreboardView.entrySet());
        return orderedView
                .stream()
                .map(entry -> {
                    final var match = entry.getKey();
                    final var score = entry.getValue();
                    return String.format("%d. %s %d - %s %d", orderedView.indexOf(entry), match.homeTeam().teamName().value(), score.homeTeamScore(), match.awayTeam().teamName().value(), score.awayTeamScore());
//                    return orderedView.indexOf(entry) + ". " + match.homeTeam().teamName().value() + " " + score.homeTeamScore() + " - " + match.awayTeam().teamName().value() + " " + score.awayTeamScore();
                })
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
