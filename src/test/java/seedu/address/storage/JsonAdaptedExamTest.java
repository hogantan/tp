package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.util.DateUtil.parseToDate;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.academic.exam.Exam;
import seedu.address.model.student.academic.exam.Score;

public class JsonAdaptedExamTest {

    private static final String VALID_NAME = "EOY 2020";
    private static final String VALID_DATE = "14/5/2020";
    private static final String VALID_SCORE = "30/100";

    @Test
    public void toModelType_null_throwsException() {
        JsonAdaptedExam exam = new JsonAdaptedExam(null, VALID_DATE, VALID_SCORE);
        assertThrows(IllegalValueException.class, exam::toModelType);

        exam = new JsonAdaptedExam(VALID_NAME, null, VALID_SCORE);
        assertThrows(IllegalValueException.class, exam::toModelType);

        exam = new JsonAdaptedExam(VALID_NAME, VALID_DATE, null);
        assertThrows(IllegalValueException.class, exam::toModelType);
    }

    @Test
    public void toModelType_emptyString_throwsException() {
        String empty = "";

        JsonAdaptedExam exam = new JsonAdaptedExam(VALID_NAME, VALID_DATE, empty);
        assertThrows(IllegalValueException.class, exam::toModelType);

        exam = new JsonAdaptedExam(VALID_NAME, empty, VALID_SCORE);
        assertThrows(IllegalValueException.class, exam::toModelType);

        exam = new JsonAdaptedExam(empty, VALID_DATE, VALID_SCORE);
        assertThrows(IllegalValueException.class, exam::toModelType);
    }

    @Test
    public void toModelType_invalidString_throwsException() {
        String invalidName = "    ";
        String invalidDate = "23.9.2019";
        String invalidScore = "50/20";

        JsonAdaptedExam exam = new JsonAdaptedExam(invalidName, VALID_DATE, VALID_SCORE);
        assertThrows(IllegalValueException.class, exam::toModelType);

        exam = new JsonAdaptedExam(VALID_NAME, invalidDate, VALID_SCORE);
        assertThrows(IllegalValueException.class, exam::toModelType);

        exam = new JsonAdaptedExam(VALID_NAME, VALID_DATE, invalidScore);
        assertThrows(IllegalValueException.class, exam::toModelType);
    }

    @Test
    public void toModelType_validFields_success() throws Exception {
        Exam expected = new Exam(VALID_NAME, parseToDate(VALID_DATE), new Score(VALID_SCORE));
        JsonAdaptedExam exam = new JsonAdaptedExam(VALID_NAME, VALID_DATE, VALID_SCORE);
        assertEquals(expected, exam.toModelType());
    }
}
