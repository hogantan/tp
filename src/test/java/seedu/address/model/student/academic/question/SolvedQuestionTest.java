package seedu.address.model.student.academic.question;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SolvedQuestionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        String validString = "test";

        // both fields null
        assertThrows(NullPointerException.class, () -> new SolvedQuestion(null, null));

        // one field null
        assertThrows(NullPointerException.class, () -> new SolvedQuestion(null, validString));
        assertThrows(NullPointerException.class, () -> new SolvedQuestion(validString, null));
    }

    @Test
    public void constructor_invalidString_throwsIllegalArgumentException() {
        String invalidString = " ";
        String validString = "test";

        // both fields invalid
        assertThrows(IllegalArgumentException.class, () -> new SolvedQuestion(invalidString, invalidString));

        // one field invalid
        assertThrows(IllegalArgumentException.class, () -> new SolvedQuestion(validString, invalidString));
        assertThrows(IllegalArgumentException.class, () -> new SolvedQuestion(invalidString, validString));
    }

    @Test
    public void isValidSolution() {
        // null question
        assertThrows(NullPointerException.class, () -> SolvedQuestion.isValidSolution(null));

        // invalid solution
        assertFalse(SolvedQuestion.isValidSolution(" "));
        assertFalse(SolvedQuestion.isValidSolution(""));

        // valid questions
        assertTrue(SolvedQuestion.isValidSolution("2"));
        assertTrue(SolvedQuestion.isValidSolution("No thanks"));
        assertTrue(SolvedQuestion.isValidSolution("Answered via Whatsapp"));
    }

    @Test
    public void toString_equals() {
        assertEquals("(\u2713) Hello? [Goodbye]",
                new SolvedQuestion("Hello?", "Goodbye").toString());
    }

    @Test
    public void equals() {
        String testQuestion = "1 + 1 = ?";
        String testSolution = "2";
        Question test = new SolvedQuestion(testQuestion, testSolution);

        // different question gives false
        assertFalse(test.equals(new SolvedQuestion("Hello?", testSolution)));

        // different solution gives false
        assertFalse(test.equals(new SolvedQuestion(testQuestion, "No")));

        // different question type gives false
        assertFalse(test.equals(new UnsolvedQuestion(testQuestion)));

        // true
        assertTrue(test.equals(test));
        assertTrue(test.equals(new SolvedQuestion(testQuestion, testSolution)));
    }

    @Test
    public void isResolved() {
        String testQuestion = "1 + 1 = ?";
        String testSolution = "2";
        Question test = new SolvedQuestion(testQuestion, testSolution);

        assertTrue(test.isResolved());
    }

}
