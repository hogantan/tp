package seedu.address.model.student.academic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FeedbackTest {

    static final Feedback VALID_FEEDBACK = new Feedback("attentive");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Feedback(null));
    }

    @Test
    public void constructor_invalidAttendanceFields_throwsIllegalArgumentException() {
        // cannot be empty string
        assertThrows(IllegalArgumentException.class, () -> new Feedback(""));
        // can only be alphanumeric
        assertThrows(IllegalArgumentException.class, () -> new Feedback("!!@@##__"));
    }

    @Test
    public void isValidFeedback_test() {
        assertFalse(Feedback.isValidFeedback(""));
        assertFalse(Feedback.isValidFeedback("!!@@##__"));
        assertTrue(Feedback.isValidFeedback("was an attentive lad today"));
    }

    @Test
    public void equals_test() {
        // same object -> true
        assertTrue(VALID_FEEDBACK.equals(VALID_FEEDBACK));

        // same state -> true
        assertTrue(VALID_FEEDBACK.equals(new Feedback("attentive")));

        // different type -> false
        assertFalse(VALID_FEEDBACK.equals("lass"));

        // different state -> false
        assertFalse(VALID_FEEDBACK.equals(new Feedback("not attentive")));
    }
}
