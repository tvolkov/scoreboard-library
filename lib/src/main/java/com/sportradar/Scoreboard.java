package com.sportradar;

import com.sportradar.model.Score;
import com.sportradar.model.Team;

public interface Scoreboard {
    void startNewMatch(Team homeTeam, Team awayTeam); //todo return some 'MatchID' which can be used to finish match later?
    void updateScore(Score homeTeamScore, Score awayTeamScore) throws IllegalStateException; //todo custom exception?
    void finishMatch(Team homeTeam, Team awayTeam) throws IllegalStateException; //todo custom exception?
    ScoreboardSummary getSummary();
}
