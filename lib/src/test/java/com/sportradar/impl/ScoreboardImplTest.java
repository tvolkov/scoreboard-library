package com.sportradar.impl;

import com.sportradar.Scoreboard;
import com.sportradar.ScoreboardSummary;
import com.sportradar.impl.storage.ScoreboardStorage;
import com.sportradar.model.Match;
import com.sportradar.model.Score;
import com.sportradar.model.Team;
import com.sportradar.model.TeamName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.sportradar.TestUtils.setupStorageView;
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
    void throws_exception_when_starting_match_with_null_teams() {
        // given
        var team = new Team(new TeamName("team"));

        // then
        assertThatThrownBy(() -> scoreboard.startNewMatch(team, null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> scoreboard.startNewMatch(null, team)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void throws_exception_when_starting_match_with_same_team() {
        // given
        var team = new Team(new TeamName("team"));

        // then
        assertThatThrownBy(() -> scoreboard.startNewMatch(team, team)).isInstanceOf(IllegalArgumentException.class);
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
    void throws_exception_if_trying_to_update_score_for_non_existing_match() {
        // given
        var teamName1 = new TeamName("team1");
        var teamName2 = new TeamName("team2");
        var match = new Match(new Team(teamName1), new Team(teamName2));
        var updatedScore = Score.of(0, 1);
        when(scoreboardStorage.get(match)).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> scoreboard.updateScore(match, updatedScore)).isInstanceOf(IllegalStateException.class);
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
    void throws_exception_if_new_score_is_same_as_current() {
        // given
        var homeTeam = new Team(new TeamName("team1"));
        var awayTeam = new Team(new TeamName("team2"));
        var match = new Match(homeTeam, awayTeam);
        var initialScore = Optional.of(Score.of(0, 1));
        var updatedScore = Score.of(0, 1);
        when(scoreboardStorage.get(match)).thenReturn(initialScore);

        // then
        assertThatThrownBy(() -> scoreboard.updateScore(match, updatedScore)).isInstanceOf(IllegalArgumentException.class);
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
        var expectedSummary = new ScoreboardSummary(List.of(storageView.get(3), storageView.get(1), storageView.get(0), storageView.get(4), storageView.get(2)));

        // when
        var summary = scoreboard.getSummary();

        // then
        assertThat(summary).isEqualTo(expectedSummary);
    }
}