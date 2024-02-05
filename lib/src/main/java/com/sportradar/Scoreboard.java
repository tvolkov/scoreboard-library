package com.sportradar;

import com.sportradar.model.Match;
import com.sportradar.model.Score;
import com.sportradar.model.Team;

public interface Scoreboard {
    Match startNewMatch(Team homeTeam, Team awayTeam);
    void updateScore(Match match, Score score);
    void finishMatch(Match match);
    ScoreboardSummary getSummary();
}
