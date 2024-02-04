package com.sportradar.impl;

import com.sportradar.Scoreboard;
import com.sportradar.impl.storage.ScoreboardStorage;
import com.sportradar.model.Match;
import com.sportradar.model.Score;
import com.sportradar.model.Team;
import com.sportradar.model.TeamName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;

import static com.sportradar.model.Score.zeroScore;
import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ScoreboardImplTest {

    ScoreboardStorage scoreboardStorage = mock(ScoreboardStorage.class);
    Scoreboard scoreboard = new ScoreboardImpl(scoreboardStorage);

    @Test
    void starts_new_match() {
        // given
        var homeTeam = new Team(new TeamName("team1"));
        var awayTeam = new Team(new TeamName("team2"));
        var expectedMatch = new Match(homeTeam, awayTeam);
        var expectedScore = zeroScore();

        // when
        scoreboard.startNewMatch(homeTeam, awayTeam);

        // then
        verify(scoreboardStorage).add(expectedMatch, expectedScore);
    }

    @Test
    void updates_score() {
        // given
        var teamName1 = new TeamName("team1");
        var teamName2 = new TeamName("team2");
        var updatedMatch = new Match(new Team(teamName1), new Team(teamName2));
        var initialScore = zeroScore();
        var updatedScore = Score.of(0, 1);
        when(scoreboardStorage.get(updatedMatch)).thenReturn(Optional.of(initialScore));

        // when
        scoreboard.updateScore(updatedMatch, updatedScore);

        // then
        verify(scoreboardStorage).update(updatedMatch, updatedScore);
    }

    @Test
    void throws_exception_if_updated_score_is_less_than_current(){
        // given
        var homeTeam = new Team(new TeamName("team1"));
        var awayTeam = new Team(new TeamName("team2"));
        var match = new Match(homeTeam, awayTeam);
        var initialScore = Optional.of(Score.of(0, 1));
        var updatedScore = zeroScore();
        when(scoreboardStorage.get(match)).thenReturn(initialScore);

        // then
        assertThatThrownBy(() -> scoreboard.updateScore(match, updatedScore)).isInstanceOf(IllegalStateException.class);
        verify(scoreboardStorage, times(1)).get(match);
        verify(scoreboardStorage, never()).update(match, updatedScore);
    }

    @Test
    void finishes_match() {
        // given
        var homeTeam = new Team(new TeamName("team1"));
        var awayTeam = new Team(new TeamName("team2"));
        var match = new Match(homeTeam, awayTeam);
        var score = zeroScore();
        when(scoreboardStorage.get(match)).thenReturn(Optional.of(score));

        // when
        scoreboard.finishMatch(match);

        // then
        verify(scoreboardStorage, times(1)).get(match);
        verify(scoreboardStorage, times(1)).remove(match);
    }

    @Test
    void throws_exception_when_trying_to_finish_non_existing_match(){
        // given
        var homeTeam = new Team(new TeamName("team1"));
        var awayTeam = new Team(new TeamName("team2"));
        var match = new Match(homeTeam, awayTeam);
        when(scoreboardStorage.get(match)).thenReturn(empty());

        // then
        assertThatThrownBy(() -> scoreboard.finishMatch(match)).isInstanceOf(IllegalStateException.class);
        verify(scoreboardStorage, times(1)).get(match);
        verify(scoreboardStorage, never()).remove(match);
    }

    @Test
    void returns_scoreboard_summary() {
        // given
        var storageView = setupStorageView();
        when(scoreboardStorage.getAll()).thenReturn(storageView);
        var expectedSummaryText = """
                1. Uruguay 6 - Italy 6
                2. Spain 10 - Brazil 2
                3. Mexico 0 - Canada 5
                4. Argentina 3 - Australia 1
                5. Germany 2 - France 2
                """;

        // when
        var summary = scoreboard.getSummary().print();

        // then
        assertThat(expectedSummaryText).isEqualTo(summary);
    }

    private static LinkedHashMap<Match, Score> setupStorageView() {
        var match1 = new Match(new Team(new TeamName("Mexico")), new Team(new TeamName("Canada")));
        var score1 = Score.of(0, 5);
        var match2 = new Match(new Team(new TeamName("Spain")), new Team(new TeamName("Brazil")));
        var score2 = Score.of(10, 2);
        var match3 = new Match(new Team(new TeamName("Germany")), new Team(new TeamName("France")));
        var score3 = Score.of(2, 2);
        var match4 = new Match(new Team(new TeamName("Uruguay")), new Team(new TeamName("Italy")));
        var score4 = Score.of(6, 6);
        var match5 = new Match(new Team(new TeamName("Argentina")), new Team(new TeamName("Australia")));
        var score5 = Score.of(3, 1);
        var storageView = new LinkedHashMap<Match, Score>();
        storageView.put(match1, score1);
        storageView.put(match2, score2);
        storageView.put(match3, score3);
        storageView.put(match4, score4);
        storageView.put(match5, score5);
        return storageView;
    }
}