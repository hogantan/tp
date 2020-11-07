package seedu.address.model.student.academic.question;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class QuestionTest {

    @Test
    public void isValidQuestion() {
        // null question
        assertThrows(NullPointerException.class, () -> Question.isValidQuestion(null));

        // invalid question
        assertFalse(Question.isValidQuestion(" "));
        assertFalse(Question.isValidQuestion(""));

        // valid questions
        assertTrue(Question.isValidQuestion("what is 1 + 1?"));
        assertTrue(Question.isValidQuestion("Hello?"));
        assertTrue(Question.isValidQuestion("How does Newton's Second Law work?"));
    }

    @Test
    public void isSameQuestion() {
        Question test = new UnsolvedQuestion("Hello?");
        Question otherUnsolved = new UnsolvedQuestion("Hello?");
        Question otherSolved = new SolvedQuestion("Hello?", "No thanks.");

        assertTrue(test.isSameQuestion(otherSolved));
        assertTrue(test.isSameQuestion(otherUnsolved));
        assertTrue(otherSolved.isSameQuestion(test));
        assertTrue(otherUnsolved.isSameQuestion(test));

        assertFalse(test.isSameQuestion(new UnsolvedQuestion("What?")));
        assertFalse(test.isSameQuestion(new SolvedQuestion("What?", "No.")));
    }

}
