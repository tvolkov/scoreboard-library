package com.sportradar.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @Test
    void throws_exception_if_trying_to_create_negative_score() {
        assertThatThrownBy(() -> Score.of(0, -1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Score.of(-2, 1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Score.of(-10, -1)).isInstanceOf(IllegalArgumentException.class);
    }
}