package seedu.address.model.student.academic.exam;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.commons.util.DateUtil.getInputFormat;
import static seedu.address.commons.util.DateUtil.print;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents an Exam in Reeve that can be assigned to a {@code Student}.
 */
public class Exam {

    public static final String MESSAGE_CONSTRAINTS = "Exam names can take any values, and "
            + "should not be should not be blank.";

    /*
     * Exam names must have at least 1 alphabet with spaces in between allowed.
     * First character cannot be empty string if not empty string becomes valid school.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String examName;
    private final LocalDate examDate;
    private final Score score;

    /**
     * Constructs a {@code Exam}.
     *
     * @param examName name of exam.
     * @param examDate date of exam.
     * @param score score obtained.
     */
    public Exam(String examName, LocalDate examDate, Score score) {
        requireAllNonNull(examName, examDate, score);
        checkArgument(isValidExamName(examName), MESSAGE_CONSTRAINTS);

        this.examName = examName;
        this.examDate = examDate;
        this.score = score;
    }

    public static boolean isValidExamName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getName() {
        return examName;
    }

    public LocalDate getDate() {
        return examDate;
    }

    public Score getScore() {
        return score;
    }

    public String getUserInputDate() {
        return getInputFormat(examDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examName, examDate, score);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Exam)) {
            return false;
        }

        Exam other = (Exam) obj;
        return other.getName().equals(getName());
    }

    @Override
    public String toString() {
        return "- " + examName + "\n\t-> Date: " + print(examDate) + "\n\t-> Score: "
                + score + " (" + score.getScorePercentage() + "%)\n";
    }
}
