package seedu.address.model.student.academic.question;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UnsolvedQuestionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnsolvedQuestion(null));
    }

    @Test
    public void constructor_invalidString_throwsIllegalArgumentException() {
        String invalidString = " ";
        assertThrows(IllegalArgumentException.class, () -> new UnsolvedQuestion(invalidString));
    }

    @Test
    public void toString_equals() {
        assertEquals(new UnsolvedQuestion("Hello?").toString(), "(\u2718) Hello?");
    }

    @Test
    public void equals() {
        String testString = "1 + 1 = ?";
        Question test = new UnsolvedQuestion(testString);

        // different question gives false
        assertFalse(test.equals(new UnsolvedQuestion("Hello?")));

        // different question type gives false
        assertFalse(test.equals(new SolvedQuestion(testString, "2")));

        // true
        assertTrue(test.equals(test));
        assertTrue(test.equals(new UnsolvedQuestion(testString)));
    }

    @Test
    public void isResolved() {
        String testString = "1 + 1 = ?";
        Question test = new UnsolvedQuestion(testString);

        assertFalse(test.isResolved());
    }
}
