package com.sportradar.impl.storage;

import com.sportradar.model.Match;
import com.sportradar.model.Team;
import com.sportradar.model.TeamName;

import java.util.Collection;

public interface ScoreboardStorage {

    void add(Match match);
    void remove(Match match);
    boolean hasMatchOfTeams(TeamName homeTeamName, TeamName awayTeamName);
    void update(Match match);
    Collection<Match> get();
}
