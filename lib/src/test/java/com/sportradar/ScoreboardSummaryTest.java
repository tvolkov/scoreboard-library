package com.sportradar;

import org.junit.jupiter.api.Test;

import static com.sportradar.TestUtils.setupStorageView;
import static org.assertj.core.api.Assertions.assertThat;

class ScoreboardSummaryTest {

    @Test
    void prints_summary() {
        // given
        var summary = new ScoreboardSummary(setupStorageView());
        var expectedSummaryText = """
                1. Uruguay 6 - Italy 6
                2. Spain 10 - Brazil 2
                3. Mexico 0 - Canada 5
                4. Argentina 3 - Australia 1
                5. Germany 2 - France 2""";

        // when
        var result = summary.print();

        // then
        assertThat(result).isEqualTo(expectedSummaryText);
    }
}