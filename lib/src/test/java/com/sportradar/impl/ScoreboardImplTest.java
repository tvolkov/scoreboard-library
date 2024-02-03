package com.sportradar.impl;

import com.sportradar.Scoreboard;
import com.sportradar.impl.storage.ScoreboardStorage;
import com.sportradar.model.Match;
import com.sportradar.model.Score;
import com.sportradar.model.Team;
import com.sportradar.model.TeamName;
import com.sportradar.model.TeamScore;
import org.junit.jupiter.api.Test;

import static com.sportradar.model.Score.of;
import static com.sportradar.model.Score.zeroScore;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ScoreboardImplTest {

    ScoreboardStorage scoreboardStorage = mock(ScoreboardStorage.class);
    Scoreboard scorboard = new ScoreboardImpl(scoreboardStorage);

    @Test
    void starts_new_match() {
        // given
        var homeTeam = new Team(new TeamName("team1"));
        var awayTeam = new Team(new TeamName("team2"));
        var expectedMatch = new Match(new TeamScore(homeTeam, zeroScore()), new TeamScore(awayTeam, zeroScore()));

        // when
        scorboard.startNewMatch(homeTeam, awayTeam);

        // then
        verify(scoreboardStorage).add(expectedMatch);
    }

    @Test
    void updates_score() {
        // given
        var homeTeam = new Team(new TeamName("team1"));
        var awayTeam = new Team(new TeamName("team2"));
        var match = new Match(new TeamScore(homeTeam, zeroScore()), new TeamScore(awayTeam, zeroScore()));
        var updatedMatch = new Match(new TeamScore(homeTeam, zeroScore()), new TeamScore(awayTeam, Score.of(1)));
        when(scoreboardStorage.has(match)).thenReturn(true);

        // when
        scorboard.updateScore(updatedMatch);

        // then
        verify(scoreboardStorage).update(updatedMatch);
    }

    @Test
    void throws_exception_if_updated_score_is_less_than_current(){
        // given
        var homeTeam = new Team(new TeamName("team1"));
        var awayTeam = new Team(new TeamName("team2"));
        var match = new Match(new TeamScore(homeTeam, zeroScore()), new TeamScore(awayTeam, of(2)));
        var updatedMatch = new Match(new TeamScore(homeTeam, zeroScore()), new TeamScore(awayTeam, Score.of(1)));
        when(scoreboardStorage.has(match)).thenReturn(true);

        // when
        scorboard.updateScore(updatedMatch);

        // then
        assertThatThrownBy(() -> scorboard.updateScore(updatedMatch)).isInstanceOf(IllegalStateException.class);
        verify(scoreboardStorage, times(1)).has(updatedMatch);
        verify(scoreboardStorage, never()).update(updatedMatch);
    }

    @Test
    void finishes_match() {
        // given
        var homeTeam = new Team(new TeamName("team1"));
        var awayTeam = new Team(new TeamName("team2"));
        var match = new Match(new TeamScore(homeTeam, zeroScore()), new TeamScore(awayTeam, zeroScore()));
        when(scoreboardStorage.has(match)).thenReturn(false);

        // when
        scorboard.finishMatch(match);

        // then
        verify(scoreboardStorage, times(1)).has(match);
        verify(scoreboardStorage, times(1)).remove(match);
    }

    @Test
    void throws_exception_when_trying_to_finish_non_existing_match(){
        // given
        var homeTeam = new Team(new TeamName("team1"));
        var awayTeam = new Team(new TeamName("team2"));
        var match = new Match(new TeamScore(homeTeam, zeroScore()), new TeamScore(awayTeam, zeroScore()));
        when(scoreboardStorage.has(match)).thenReturn(true);

        // then
        assertThatThrownBy(() -> scorboard.finishMatch(match)).isInstanceOf(IllegalStateException.class);
        verify(scoreboardStorage, times(1)).has(match);
        verify(scoreboardStorage, never()).remove(match);
    }

    @Test
    void returns_scoreboard_summary() {

    }
}