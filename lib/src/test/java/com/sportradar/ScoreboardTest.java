package com.sportradar;

import com.sportradar.impl.ScoreboardImpl;
import com.sportradar.impl.storage.SimpleScoreboardStorage;
import com.sportradar.model.Match;
import com.sportradar.model.Score;
import com.sportradar.model.Team;
import com.sportradar.model.TeamName;
import org.junit.jupiter.api.Test;

import static com.sportradar.model.Score.zeroScore;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class ScoreboardTest {
    Scoreboard scoreboard = new ScoreboardImpl(new SimpleScoreboardStorage());

    @Test
    void test() {
        // given
        setup();

        // when
        var summary = scoreboard.getSummary().print();

        // then
        assertThat(summary).isEqualTo();
    }

    private void setup() {
//        var match1 = new Match(new Team(new TeamName("Mexico")), new Team(new TeamName("Canada")));
        var score1 = Score.of(0, 5);
//        var match2 = new Match(new Team(new TeamName("Spain")), new Team(new TeamName("Brazil")));
        var score2 = Score.of(10, 2);
//        var match3 = new Match(new Team(new TeamName("Germany")), new Team(new TeamName("France")));
        var score3 = Score.of(2, 2);
//        var match4 = new Match(new Team(new TeamName("Uruguay")), new Team(new TeamName("Italy")));
        var score4 = Score.of(6, 6);
//        var match5 = new Match(new Team(new TeamName("Argentina")), new Team(new TeamName("Australia")));
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

    }
}
