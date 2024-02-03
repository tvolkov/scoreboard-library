package com.sportradar;

import com.sportradar.model.Score;
import com.sportradar.model.Team;

public interface Scoreboard {
    void startNewMatch(Team homeTeam, Team awayTeam);
    void updateScore(Score homeTeamScore, Score awayTeamScore);
    void finishMatch(Team homeTeam, Team awayTeam);
    ScoreboardSummary getSummary();
}
