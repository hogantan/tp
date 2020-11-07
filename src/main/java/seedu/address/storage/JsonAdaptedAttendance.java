package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.student.academic.Attendance;
import seedu.address.model.student.academic.Feedback;

public class JsonAdaptedAttendance {

    public static final String MISSING_ATTENDANCE_FIELD_MESSAGE_FORMAT = "Attendance's %s field is missing!";
    public static final String INVALID_ATTENDANCE_STATUS_MESSAGE = "attendanceStatus field can only accept "
            + "either \"present\" or \"absent\" (case-sensitive)";

    private static final String PRESENT = "present";
    private static final String ABSENT = "absent";

    private final String date;
    private final String attendanceStatus;
    private final String feedback;

    /**
     * Constructs a {@code JsonAdaptedExam} with the given {@code exam}.
     */
    @JsonCreator
    public JsonAdaptedAttendance(@JsonProperty("date") String date,
                                 @JsonProperty("attendanceStatus") String attendanceStatus,
                                 @JsonProperty("feedback") String feedback) {
        this.date = date;
        this.attendanceStatus = attendanceStatus;
        this.feedback = feedback;
    }

    /**
     * Converts a given {@code Exam} into this class for Jackson use.
     */
    public JsonAdaptedAttendance(Attendance source) {
        this.date = source.getUserInputDate();
        this.attendanceStatus = source.isStudentPresent() ? PRESENT : ABSENT;
        this.feedback = source.getFeedback().map(Feedback::toString).orElse("");
    }

    /**
     * Converts this Jackson-friendly adapted attendance object into the model's {@code Attendance} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted detail.
     */
    public Attendance toModelType() throws IllegalValueException {
        LocalDate modelDate = toModelDate();
        boolean isPresent = toModelPresence();

        if (feedback == null) {
            throw new IllegalValueException(String.format(MISSING_ATTENDANCE_FIELD_MESSAGE_FORMAT, "feedback"));
        }
        if (feedback.isEmpty()) {
            return new Attendance(modelDate, isPresent);
        }

        Feedback modelFeedback = toModelFeedback();
        return new Attendance(modelDate, isPresent, modelFeedback);
    }

    private LocalDate toModelDate() throws IllegalValueException {
        if (date == null || date.isEmpty()) {
            throw new IllegalValueException(String.format(MISSING_ATTENDANCE_FIELD_MESSAGE_FORMAT, "date"));
        }
        if (!DateUtil.isValidDate(date)) {
            throw new IllegalValueException("Invalid date format");
        }
        return DateUtil.parseToDate(date);
    }

    private boolean toModelPresence() throws IllegalValueException {
        if (attendanceStatus == null) {
            throw new IllegalValueException(String.format(MISSING_ATTENDANCE_FIELD_MESSAGE_FORMAT, "status"));
        }
        if (!isValidAttendanceStatus()) {
            throw new IllegalValueException(INVALID_ATTENDANCE_STATUS_MESSAGE);
        }
        return attendanceStatus.equals(PRESENT);
    }

    private Feedback toModelFeedback() throws IllegalValueException {
        if (!Feedback.isValidFeedback(feedback)) {
            throw new IllegalValueException(Feedback.MESSAGE_CONSTRAINTS);
        }
        return new Feedback(feedback);
    }

    private boolean isValidAttendanceStatus() {
        return attendanceStatus.equals(PRESENT) || attendanceStatus.equals(ABSENT);
    }

}
