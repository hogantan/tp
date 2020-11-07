package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.util.DateUtil.parseToDate;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.academic.Attendance;
import seedu.address.model.student.academic.Feedback;

public class JsonAdaptedAttendanceTest {

    private static final String PRESENT = "present";
    private static final String ABSENT = "absent";

    @Test
    public void toModelType_invalidDate_throwsException() {
        JsonAdaptedAttendance test = new JsonAdaptedAttendance("", PRESENT, "");
        assertThrows(IllegalValueException.class, test::toModelType);

        test = new JsonAdaptedAttendance(null, PRESENT, "");
        assertThrows(IllegalValueException.class, test::toModelType);

        test = new JsonAdaptedAttendance("abc", PRESENT, "");
        assertThrows(IllegalValueException.class, test::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsException() {
        String dateInput = "10/10/20";
        String feedbackInput = "sleepy";
        String invalidPresence = "random";

        JsonAdaptedAttendance test = new JsonAdaptedAttendance(dateInput, null, feedbackInput);
        assertThrows(IllegalValueException.class, test::toModelType);

        test = new JsonAdaptedAttendance(dateInput, invalidPresence, feedbackInput);
        assertThrows(IllegalValueException.class, test::toModelType);

        test = new JsonAdaptedAttendance(dateInput, "", feedbackInput);
        assertThrows(IllegalValueException.class, test::toModelType);
    }

    @Test
    public void toModelType_invalidFeedback_throwsException() {
        String dateInput = "10/10/20";
        String invalidFeedback = "!n_val!d";
        JsonAdaptedAttendance test = new JsonAdaptedAttendance(dateInput, PRESENT, invalidFeedback);
        assertThrows(IllegalValueException.class, test::toModelType);

        test = new JsonAdaptedAttendance(dateInput, PRESENT, null);
        assertThrows(IllegalValueException.class, test::toModelType);
    }

    @Test
    public void toModelType_noFeedback_success() throws Exception {
        String dateInput = "10/10/20";
        LocalDate date = parseToDate(dateInput);

        // status = PRESENT
        Attendance expected = new Attendance(date, true);
        JsonAdaptedAttendance test = new JsonAdaptedAttendance(dateInput, PRESENT, "");
        assertEquals(expected, test.toModelType());

        // status = ABSENT
        expected = new Attendance(date, false);
        test = new JsonAdaptedAttendance(dateInput, ABSENT, "");
        assertEquals(expected, test.toModelType());
    }

    @Test
    public void toModelType_withFeedback_success() throws Exception {
        String dateInput = "10/10/20";
        LocalDate date = parseToDate(dateInput);
        String feedbackInput = "sleepy";
        Feedback feedback = new Feedback(feedbackInput);

        // status = PRESENT
        Attendance expected = new Attendance(date, true, feedback);
        JsonAdaptedAttendance test = new JsonAdaptedAttendance(dateInput, PRESENT, feedbackInput);
        assertEquals(expected, test.toModelType());

        // status = ABSENT
        expected = new Attendance(date, false, feedback);
        test = new JsonAdaptedAttendance(dateInput, ABSENT, feedbackInput);
        assertEquals(expected, test.toModelType());
    }
}
