package seedu.address.model.student.academic.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ScoreTest {
    static final String VALID_SCORE_STRING = "25/50";
    static final String INVALID_SCORE_LARGER = "50/25";
    static final String INVALID_SCORE_NEGATIVE_1 = "-25/50";
    static final String INVALID_SCORE_NEGATIVE_2 = "-25/-50";
    static final String INVALID_SCORE_EMPTY = "";
    static final String INVALID_SCORE_ALPHABETS_1 = "alphabets";
    static final String INVALID_SCORE_ALPHABETS_2 = "alphabets/alphabets";
    static final String INVALID_SCORE_FORMAT = "25-50";
    static final Score VALID_SCORE = new Score(VALID_SCORE_STRING);

    @Test
    public void constructor_null_throwsNullPointerException() {
        // All fields null
        assertThrows(NullPointerException.class, () -> new Score(null));
    }

    @Test
    public void isValidExamScore() {
        //valid score
        assertTrue(Score.isValidExamScore(VALID_SCORE_STRING));

        //invalid scores
        assertFalse(Score.isValidExamScore(INVALID_SCORE_LARGER));
        assertFalse(Score.isValidExamScore(INVALID_SCORE_NEGATIVE_1));
        assertFalse(Score.isValidExamScore(INVALID_SCORE_NEGATIVE_2));
        assertFalse(Score.isValidExamScore(INVALID_SCORE_EMPTY));
        assertFalse(Score.isValidExamScore(INVALID_SCORE_ALPHABETS_1));
        assertFalse(Score.isValidExamScore(INVALID_SCORE_ALPHABETS_2));
        assertFalse(Score.isValidExamScore(INVALID_SCORE_FORMAT));
    }

    @Test
    public void equals_test() {
        //same object
        assertEquals(VALID_SCORE, VALID_SCORE);

        //different object
        assertNotEquals(VALID_SCORE, "not a score");

        //same score string
        assertEquals(VALID_SCORE, new Score(VALID_SCORE_STRING));

        //different score string
        assertNotEquals(VALID_SCORE, new Score("100/100"));
    }
}
