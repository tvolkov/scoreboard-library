package com.sportradar.impl.storage;

import com.sportradar.model.Match;
import com.sportradar.model.Score;
import com.sportradar.model.Team;
import com.sportradar.model.TeamName;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static com.sportradar.model.Score.zeroScore;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleScoreboardStorageTest {

    @Test
    void adds_new_match() {
        // given
        var map = new HashMap<Match, Score>();
        var storage = new SimpleScoreboardStorage(map);
        var match = new Match(new Team(new TeamName("team1")), new Team(new TeamName("team2")));
        var score = zeroScore();

        // when
        storage.add(match, score);

        // then
        assertThat(map.get(match)).isEqualTo(score);
    }

    @Test
    void throws_exception_if_arguments_invalid_when_adding_match() {
        // given
        var map = new HashMap<Match, Score>();
        var storage = new SimpleScoreboardStorage(map);
        var match = new Match(new Team(new TeamName("team1")), new Team(new TeamName("team2")));
        var score = zeroScore();

        // then
        assertThatThrownBy(() -> storage.add(match, null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> storage.add(null, score)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> storage.add(null, null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void removes_match() {
        // given
        var match = new Match(new Team(new TeamName("team1")), new Team(new TeamName("team2")));
        var score = zeroScore();
        var map = new HashMap<Match, Score>();
        map.put(match, score);
        var storage = new SimpleScoreboardStorage(map);

        // when
        storage.remove(match);

        // then
        assertThat(map.containsKey(match)).isFalse();
    }

    @Test
    void throws_exception_if_trying_to_remove_invalid_match() {
        // given
        var match = new Match(new Team(new TeamName("team1")), new Team(new TeamName("team2")));
        var score = zeroScore();
        var map = new HashMap<Match, Score>();
        map.put(match, score);
        var storage = new SimpleScoreboardStorage(map);

        // then
        assertThatThrownBy(() -> storage.remove(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void updates_match(){
        // given
        var match = new Match(new Team(new TeamName("team1")), new Team(new TeamName("team2")));
        var score = zeroScore();
        var updatedScore = Score.of(0, 1);
        var map = new HashMap<Match, Score>();
        map.put(match, score);
        var storage = new SimpleScoreboardStorage(map);

        // when
        storage.update(match, updatedScore);

        // then
        assertThat(map.get(match)).isEqualTo(updatedScore);
    }

    @Test
    void update_match_throws_exception_if_arguments_invalid() {
        // given
        var match = new Match(new Team(new TeamName("team1")), new Team(new TeamName("team2")));
        var score = zeroScore();
        var map = new HashMap<Match, Score>();
        map.put(match, score);
        var storage = new SimpleScoreboardStorage(map);

        // then
        assertThatThrownBy(() -> storage.update(match, null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> storage.update(null, score)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> storage.update(null, null)).isInstanceOf(NullPointerException.class);

    }
    @Test
    void returns_match() {
        // given
        var match = new Match(new Team(new TeamName("team1")), new Team(new TeamName("team2")));
        var score = zeroScore();
        var map = new HashMap<Match, Score>();
        map.put(match, score);
        var storage = new SimpleScoreboardStorage(map);

        // when
        var result = storage.get(match);

        // then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(score);
    }

    @Test
    void returns_match_throws_exception_when_trying_to_get_invalid_match() {
        // given
        var match = new Match(new Team(new TeamName("team1")), new Team(new TeamName("team2")));
        var score = zeroScore();
        var map = new HashMap<Match, Score>();
        map.put(match, score);
        var storage = new SimpleScoreboardStorage(map);

        // then
        assertThatThrownBy(() -> storage.get(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void returns_all_matches() {
        // given
        var match1 = new Match(new Team(new TeamName("team1")), new Team(new TeamName("team2")));
        var score1 = zeroScore();
        var match2 = new Match(new Team(new TeamName("team3")), new Team(new TeamName("team4")));
        var score2 = zeroScore();
        var map = new HashMap<Match, Score>();
        map.put(match1, score1);
        map.put(match2, score2);
        var storage = new SimpleScoreboardStorage(map);

        // when
        var result = storage.getAll();

        // then
        assertThat(result).containsExactly(Pair.of(match1, score1), Pair.of(match2, score2));
    }
}