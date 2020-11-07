package seedu.address.model.student.academic;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.commons.util.DateUtil.getInputFormat;
import static seedu.address.commons.util.DateUtil.print;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Represents a Student's attendance in a lesson.
 * Guarantees: immutable
 */
public class Attendance {

    public static final String STATUS_CONSTRAINTS =
            "Attendance status should be either 'present' or 'absent'.";

    public static final String PRESENT_STATUS = "present";
    public static final String ABSENT_STATUS = "absent";

    private final LocalDate lessonDate;
    private final boolean isPresent;
    private final Optional<Feedback> feedback;

    /**
     * Constructs a {@code Attendance} object.
     * @param date date of lesson
     * @param isPresent whether student was present for lesson
     * @param feedback feedback for lesson
     */
    public Attendance(LocalDate date, boolean isPresent, Feedback feedback) {
        requireAllNonNull(date, feedback);

        this.lessonDate = date;
        this.isPresent = isPresent;
        this.feedback = Optional.of(feedback);
    }

    /**
     * Constructs a {@code Attendance} object.
     * @param date date of lesson
     * @param isPresent whether student was present for lesson
     */
    public Attendance(LocalDate date, boolean isPresent) {
        requireAllNonNull(date);

        this.lessonDate = date;
        this.isPresent = isPresent;
        this.feedback = Optional.empty();
    }

    public static boolean isValidStatus(String status) {
        return status.equals(PRESENT_STATUS) || status.equals(ABSENT_STATUS);
    }

    public LocalDate getLessonDate() {
        return lessonDate;
    }

    /**
     * Returns true if the given date matches the lesson date of an Attendance object.
     * This is a weaker notion of equality between Attendance objects.
     */
    public boolean isSameAttendance(Attendance date) {
        return lessonDate.equals(date.lessonDate);
    }

    public String getUserInputDate() {
        return getInputFormat(lessonDate);
    }

    public boolean isStudentPresent() {
        return isPresent;
    }

    public Optional<Feedback> getFeedback() {
        return feedback;
    }

    @Override
    public String toString() {
        String feedback = getFeedback().map(Feedback::toString).orElse("");
        return String.format("%1$s (%2$s) %3$s",
                print(lessonDate), (isStudentPresent() ? "\u2713" : "\u2718"), feedback);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Attendance)) {
            return false;
        }

        Attendance other = (Attendance) obj;
        return other.getLessonDate().equals(getLessonDate())
                && other.isStudentPresent() == isStudentPresent()
                && other.getFeedback().equals(getFeedback());
    }
}
