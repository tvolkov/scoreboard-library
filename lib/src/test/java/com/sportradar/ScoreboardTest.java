package com.sportradar;

import com.sportradar.impl.ScoreboardImpl;
import com.sportradar.impl.storage.SimpleScoreboardStorage;
import com.sportradar.model.Score;
import com.sportradar.model.Team;
import com.sportradar.model.TeamName;
import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.StringUtils.EMPTY;
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
        scoreboard.updateScore(match5, score5);;
        var expectedSummaryText = """
                1. Uruguay 6 - Italy 6
                2. Spain 10 - Brazil 2
                3. Mexico 0 - Canada 5
                4. Argentina 3 - Australia 1
                5. Germany 2 - France 2""";

        // when
        var summary = scoreboard.getSummary();

        // then
        assertThat(summary.print()).isEqualTo(expectedSummaryText);

        // given
        scoreboard.finishMatch(match1);
        scoreboard.finishMatch(match2);
        scoreboard.finishMatch(match3);
        scoreboard.finishMatch(match4);
        scoreboard.finishMatch(match5);
        expectedSummaryText = EMPTY;

        // when
        summary = scoreboard.getSummary();

        // then
        assertThat(summary.print()).isEqualTo(expectedSummaryText);
    }
}
