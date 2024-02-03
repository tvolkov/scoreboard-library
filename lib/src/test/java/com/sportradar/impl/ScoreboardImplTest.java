package com.sportradar.impl;

import com.sportradar.Scoreboard;
import com.sportradar.impl.storage.ScoreboardStorage;
import com.sportradar.impl.storage.SimpleScoreboardStorage;
import com.sportradar.model.Team;
import com.sportradar.model.TeamName;
import org.junit.jupiter.api.Test;

class ScoreboardImplTest {

    ScoreboardStorage scoreboardStorage = new SimpleScoreboardStorage();
    Scoreboard scorboard = new ScoreboardImpl(scoreboardStorage);

    @Test
    void starts_new_match() {
        // given
        var homeTeam = new Team(new TeamName("team1"));
        var awayTeam = new Team(new TeamName("team2"));

        // when
        scorboard.startNewMatch(homeTeam, awayTeam);

        // then
    }

    @Test
    void updates_score() {
    }

    @Test
    void throws_exception_if_updated_score_is_less_than_current(){

    }

    @Test
    void finishes_match() {

    }

    @Test
    void throws_exception_when_trying_to_finish_non_existing_match(){

    }

    @Test
    void returns_scoreboard_summary() {

    }
}