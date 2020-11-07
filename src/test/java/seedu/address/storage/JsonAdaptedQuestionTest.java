package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.StudentBuilder.DEFAULT_QUESTION_NEWTON;
import static seedu.address.testutil.StudentBuilder.DEFAULT_SOLUTION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.academic.question.Question;
import seedu.address.model.student.academic.question.SolvedQuestion;
import seedu.address.model.student.academic.question.UnsolvedQuestion;

public class JsonAdaptedQuestionTest {

    private static final String SOLVED = "solved";
    private static final String UNSOLVED = "unsolved";

    @Test
    public void toModelType_validQuestion_returnsQuestion() throws Exception {
        Question question = new UnsolvedQuestion(DEFAULT_QUESTION_NEWTON);
        assertEquals(question, new JsonAdaptedQuestion(question).toModelType());

        question = new SolvedQuestion(DEFAULT_QUESTION_NEWTON, DEFAULT_SOLUTION);
        assertEquals(question, new JsonAdaptedQuestion(question).toModelType());
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalArgumentException() {
        String invalidStatus = "r@nd0m";

        JsonAdaptedQuestion test = new JsonAdaptedQuestion(invalidStatus, DEFAULT_QUESTION_NEWTON, DEFAULT_SOLUTION);
        assertThrows(IllegalValueException.class, test::toModelType);

        test = new JsonAdaptedQuestion("", DEFAULT_QUESTION_NEWTON, DEFAULT_SOLUTION);
        assertThrows(IllegalValueException.class, test::toModelType);

        test = new JsonAdaptedQuestion(null, DEFAULT_QUESTION_NEWTON, DEFAULT_SOLUTION);
        assertThrows(IllegalValueException.class, test::toModelType);
    }

    @Test
    public void toModelType_invalidQuestion_throwsIllegalArgumentException() {
        JsonAdaptedQuestion test = new JsonAdaptedQuestion(SOLVED, "", "");
        assertThrows(IllegalValueException.class, test::toModelType);

        test = new JsonAdaptedQuestion(SOLVED, " ", DEFAULT_SOLUTION);
        assertThrows(IllegalValueException.class, test::toModelType);
    }

    @Test
    public void toModelType_nullQuestion_throwsIllegalArgumentException() {
        JsonAdaptedQuestion test = new JsonAdaptedQuestion(UNSOLVED, null, "");
        assertThrows(IllegalValueException.class, test::toModelType);

        test = new JsonAdaptedQuestion(UNSOLVED, "", "");
        assertThrows(IllegalValueException.class, test::toModelType);

        test = new JsonAdaptedQuestion(SOLVED, null, DEFAULT_SOLUTION);
        assertThrows(IllegalValueException.class, test::toModelType);

        test = new JsonAdaptedQuestion(SOLVED, "", DEFAULT_SOLUTION);
        assertThrows(IllegalValueException.class, test::toModelType);
    }

    @Test
    public void toModelType_invalidSolution_throwsIllegalArgumentException() {
        JsonAdaptedQuestion test = new JsonAdaptedQuestion(SOLVED, DEFAULT_QUESTION_NEWTON, null);
        assertThrows(IllegalValueException.class, test::toModelType);

        test = new JsonAdaptedQuestion(SOLVED, DEFAULT_QUESTION_NEWTON, "");
        assertThrows(IllegalValueException.class, test::toModelType);

        test = new JsonAdaptedQuestion(SOLVED, DEFAULT_QUESTION_NEWTON, "   ");
        assertThrows(IllegalValueException.class, test::toModelType);
    }

    @Test
    public void toModelType_validInputs_returnsQuestion() throws Exception {
        Question expected = new SolvedQuestion(DEFAULT_QUESTION_NEWTON, DEFAULT_SOLUTION);
        JsonAdaptedQuestion test = new JsonAdaptedQuestion(SOLVED, DEFAULT_QUESTION_NEWTON, DEFAULT_SOLUTION);
        assertEquals(expected, test.toModelType());

        expected = new UnsolvedQuestion(DEFAULT_QUESTION_NEWTON);
        test = new JsonAdaptedQuestion(UNSOLVED, DEFAULT_QUESTION_NEWTON, "");
        assertEquals(expected, test.toModelType());
    }
}
