package com.sportradar;

import com.sportradar.model.Match;
import com.sportradar.model.Score;
import com.sportradar.model.Team;
import com.sportradar.model.TeamName;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static List<Pair<Match, Score>> setupStorageView() {
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
        var storageView = new ArrayList<Pair<Match, Score>>();
        storageView.add(Pair.of(match1, score1));
        storageView.add(Pair.of(match2, score2));
        storageView.add(Pair.of(match3, score3));
        storageView.add(Pair.of(match4, score4));
        storageView.add(Pair.of(match5, score5));
        return storageView;
    }
}
