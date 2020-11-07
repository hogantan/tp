package seedu.address.model.student.academic.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.DateUtil.parseToDate;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ExamTest {
    private static final String VALID_EXAM_NAME = "CA2";
    private static final LocalDate VALID_EXAM_DATE_DEF = parseToDate("13/03/2020");
    private static final LocalDate VALID_EXAM_DATE_ALT = parseToDate("13/3/20");
    private static final Score VALID_SCORE = new Score("25/50");
    private static final Exam VALID_EXAM = new Exam(VALID_EXAM_NAME, VALID_EXAM_DATE_DEF, VALID_SCORE);

    @Test
    public void constructor_null_throwsNullPointerException() {
        // All fields null
        assertThrows(NullPointerException.class, () -> new Exam(null, null, null));

        // Only one non-null
        assertThrows(NullPointerException.class, () -> new Exam(VALID_EXAM_NAME, null, null));
        assertThrows(NullPointerException.class, () -> new Exam(null, VALID_EXAM_DATE_DEF, null));
        assertThrows(NullPointerException.class, () -> new Exam(null, null, VALID_SCORE));

        // Only one null
        assertThrows(NullPointerException.class, () -> new Exam(VALID_EXAM_NAME, VALID_EXAM_DATE_DEF, null));
        assertThrows(NullPointerException.class, () -> new Exam(null, VALID_EXAM_DATE_DEF, VALID_SCORE));
        assertThrows(NullPointerException.class, () -> new Exam(VALID_EXAM_NAME, null, VALID_SCORE));
    }

    @Test
    public void isValidExamName() {
        assertTrue(Exam.isValidExamName("Test")); // all letters
        assertTrue(Exam.isValidExamName("2384")); // all numbers
        assertTrue(Exam.isValidExamName("$*#")); // all special characters
        assertTrue(Exam.isValidExamName("a123!")); // all characters

        assertFalse(Exam.isValidExamName(""));
        assertFalse(Exam.isValidExamName("       "));
    }

    @Test
    public void equals_test() {
        // different object
        assertNotEquals(VALID_EXAM, "not an exam");

        // diff fields - name is the only field that differentiates exams
        assertNotEquals(VALID_EXAM, new Exam("Mid Terms 19/20", VALID_EXAM_DATE_DEF, VALID_SCORE));

        assertEquals(VALID_EXAM, new Exam(VALID_EXAM_NAME, parseToDate("25/12/2020"), VALID_SCORE));
        assertEquals(VALID_EXAM, new Exam(VALID_EXAM_NAME, VALID_EXAM_DATE_ALT, new Score("10/100")));

        // same object
        assertEquals(VALID_EXAM, VALID_EXAM);

        // same fields
        assertEquals(VALID_EXAM, new Exam(VALID_EXAM_NAME, VALID_EXAM_DATE_ALT, VALID_SCORE));
    }
}
