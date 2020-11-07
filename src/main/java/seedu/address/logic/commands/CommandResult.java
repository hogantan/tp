package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.student.Student;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean shouldShowHelp;

    /** The application should exit. */
    private final boolean shouldExit;

    /** Schedule should be shown to user. */
    private final boolean shouldShowSchedule;

    /** The application should toggle between admin and academic student cards. */
    private final boolean toggleStudentCard;

    /** Exam stats of selected student should be shown to the user. */
    private final Student selectedStudent;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean shouldShowHelp, boolean shouldExit,
                         boolean shouldShowSchedule, boolean toggleStudentCard) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.shouldShowHelp = shouldShowHelp;
        this.shouldExit = shouldExit;
        this.shouldShowSchedule = shouldShowSchedule;
        this.toggleStudentCard = toggleStudentCard;
        this.selectedStudent = null;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean shouldShowHelp, boolean shouldExit,
                         boolean toggleStudentCard, Student student) {

        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.shouldShowHelp = shouldShowHelp;
        this.shouldExit = shouldExit;
        this.toggleStudentCard = toggleStudentCard;
        this.shouldShowSchedule = false;
        this.selectedStudent = student;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code student},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Student student) {
        this(feedbackToUser, false, false, false, student);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public boolean isShouldShowHelp() {
        return shouldShowHelp;
    }

    public boolean isShouldExit() {
        return shouldExit;
    }

    public boolean isShouldShowSchedule() {
        return shouldShowSchedule;
    }

    public boolean isToggleStudentCard() {
        return toggleStudentCard;
    }

    public boolean isExamStats() {
        return !isNull(selectedStudent);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        boolean result = feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && shouldShowHelp == otherCommandResult.shouldShowHelp
                && shouldExit == otherCommandResult.shouldExit
                && toggleStudentCard == otherCommandResult.toggleStudentCard;
        if (selectedStudent != null) {
            result = result && selectedStudent.equals(otherCommandResult.selectedStudent);
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, shouldShowHelp, shouldExit, toggleStudentCard, selectedStudent);
    }

    @Override
    public String toString() {
        return this.feedbackToUser;
    }
}
