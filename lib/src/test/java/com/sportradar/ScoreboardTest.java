package com.sportradar;

import com.sportradar.impl.ScoreboardImpl;
import com.sportradar.impl.storage.SimpleScoreboardStorage;
import com.sportradar.model.Score;
import com.sportradar.model.Team;
import com.sportradar.model.TeamName;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreboardTest {
    Scoreboard scoreboard = new ScoreboardImpl(new SimpleScoreboardStorage());

    @Test
    void test() {
        // given
        var score1 = Score.of(0, 5);
        var score2 = Score.of(10, 2);
        var score3 = Score.of(2, 2);
        var score4 = Score.of(6, 6);
        var score5 = Score.of(3, 1);
        var match1 = scoreboard.startNewMatch(new Team(new TeamName("Mexico")), new Team(new TeamName("Canada")));
        var match2 = scoreboard.startNewMatch(new Team(new TeamName("Spain")), new Team(new TeamName("Brazil")));
        var match3 = scoreboard.startNewMatch(new Team(new TeamName("Germany")), new Team(new TeamName("France")));
        var match4 = scoreboard.startNewMatch(new Team(new TeamName("Uruguay")), new Team(new TeamName("Italy")));
        var match5 = scoreboard.startNewMatch(new Team(new TeamName("Argentina")), new Team(new TeamName("Australia")));
        scoreboard.updateScore(match1, score1);
        scoreboard.updateScore(match2, score2);
        scoreboard.updateScore(match3, score3);
        scoreboard.updateScore(match4, score4);
        scoreboard.updateScore(match5, score5);
        var expectedSummary = new ScoreboardSummary(List.of(
                Pair.of(match4, score4),
                Pair.of(match2, score2),
                Pair.of(match1, score1),
                Pair.of(match5, score5),
                Pair.of(match3, score3)
        ));

        // when

        // when
        var summary = scoreboard.getSummary();

        // then
        assertThat(summary).isEqualTo(expectedSummary);

        // given
        scoreboard.finishMatch(match1);
        scoreboard.finishMatch(match2);
        scoreboard.finishMatch(match3);
        scoreboard.finishMatch(match4);
        scoreboard.finishMatch(match5);
        expectedSummary = new ScoreboardSummary(Collections.emptyList());

        // when
        summary = scoreboard.getSummary();

        // then
        assertThat(summary).isEqualTo(expectedSummary);
    }
}
