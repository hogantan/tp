package seedu.address.model.student.academic.question;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a question a Student has for a tutor in Reeve.
 */
public abstract class Question {

    public static final String MESSAGE_CONSTRAINTS =
            "Questions should at least contain a non-whitespace character";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String question;

    /**
     * Constructs a new Question object. This is the default constructor for adding
     * a new unresolved Question to a student.
     */
    public Question(String question) {
        requireNonNull(question);
        checkArgument(isValidQuestion(question), MESSAGE_CONSTRAINTS);
        this.question = question;
    }

    /**
     * Returns true if the given String is valid.
     */
    public static boolean isValidQuestion(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if both questions are the same, regardless of resolution status.
     * This is a weaker notion of equality between questions.
     */
    public boolean isSameQuestion(Question other) {
        return question.equals(other.question);
    }

    @Override
    public String toString() {
        return question;
    }

    /**
     * Returns true if the question has been resolved by the tutor.
     */
    public abstract boolean isResolved();

}
