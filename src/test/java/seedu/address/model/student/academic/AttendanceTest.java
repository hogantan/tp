package seedu.address.model.student.academic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.DateUtil.parseToDate;
import static seedu.address.commons.util.DateUtil.print;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class AttendanceTest {

    private static final String DATE_INPUT = "14/3/1998";
    private static final String FEEDBACK_INPUT = "sleepy";
    private static final LocalDate VALID_DATE = parseToDate(DATE_INPUT);
    private static final Feedback VALID_FEEDBACK = new Feedback(FEEDBACK_INPUT);
    private static final Attendance VALID_ATTENDANCE = new Attendance(VALID_DATE, true, VALID_FEEDBACK);

    @Test
    public void constructor_null_throwsNullPointerException() {
        // All but one null
        assertThrows(NullPointerException.class, () -> new Attendance(null, true,
                null));

        // Only one null
        assertThrows(NullPointerException.class, () -> new Attendance(VALID_DATE, true,
                null));
        assertThrows(NullPointerException.class, () -> new Attendance(null, true,
                VALID_FEEDBACK));
    }

    @Test
    public void constructor_invalidAttendanceFields_throwsIllegalArgumentException() {
        String invalidAttendanceFeedback = "";

        // 1 invalid
        assertThrows(IllegalArgumentException.class, () -> new Attendance(VALID_DATE,
                true, new Feedback(invalidAttendanceFeedback)));
    }

    @Test
    public void equals_test() {
        Attendance test = VALID_ATTENDANCE;

        // same object -> return true
        assertTrue(VALID_ATTENDANCE.equals(test));

        // same fields -> return true
        test = new Attendance(VALID_DATE, true, VALID_FEEDBACK);
        assertTrue(VALID_ATTENDANCE.equals(test));

        // different class
        assertFalse(VALID_ATTENDANCE.equals(VALID_FEEDBACK));

        // diff fields
        LocalDate differentDate = parseToDate("17/04/1998");
        test = new Attendance(differentDate, true, VALID_FEEDBACK);
        assertFalse(VALID_ATTENDANCE.equals(test));

        test = new Attendance(VALID_DATE, false, VALID_FEEDBACK);
        assertFalse(VALID_ATTENDANCE.equals(test));

        Feedback differentFeedback = new Feedback("sleepayy son");
        test = new Attendance(VALID_DATE, true, differentFeedback);
        assertFalse(VALID_ATTENDANCE.equals(test));

        test = new Attendance(VALID_DATE, true);
        assertFalse(VALID_ATTENDANCE.equals(test));
    }

    @Test
    public void toString_feedbackTest() {
        String outputDate = print(VALID_DATE);
        String expected = outputDate + " (\u2713) " + FEEDBACK_INPUT;
        assertEquals(expected, VALID_ATTENDANCE.toString());

        expected = outputDate + " (\u2718) " + FEEDBACK_INPUT;
        Attendance test = new Attendance(VALID_DATE, false, VALID_FEEDBACK);
        assertEquals(expected, test.toString());

        expected = outputDate + " (\u2713) ";
        test = new Attendance(VALID_DATE, true);
        assertEquals(expected, test.toString());

        expected = outputDate + " (\u2718) ";
        test = new Attendance(VALID_DATE, false);
        assertEquals(expected, test.toString());
    }

    @Test
    public void isSameAttendance() {
        Attendance test = new Attendance(VALID_DATE, true, VALID_FEEDBACK);
        assertTrue(VALID_ATTENDANCE.isSameAttendance(test));
        test = new Attendance(VALID_DATE, true);
        assertTrue(VALID_ATTENDANCE.isSameAttendance(test));

        test = new Attendance(VALID_DATE, false, VALID_FEEDBACK);
        assertTrue(VALID_ATTENDANCE.isSameAttendance(test));
        test = new Attendance(VALID_DATE, false);
        assertTrue(VALID_ATTENDANCE.isSameAttendance(test));

        LocalDate otherDate = parseToDate("28/2/20");
        test = new Attendance(otherDate, true, VALID_FEEDBACK);
        assertFalse(VALID_ATTENDANCE.isSameAttendance(test));
    }

}
