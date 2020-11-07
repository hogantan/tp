package seedu.address.model.student.academic.question;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a student's question in Reeve that the tutor has resolved.
 */
public class SolvedQuestion extends Question {

    public static final String MESSAGE_SOLUTION_CONSTRAINTS =
            "Solutions should at least contain a non-whitespace character";

    private static final String STATUS = "(\u2713)";

    public final String solution;

    /**
     * Constructs a SolvedQuestion object.
     */
    public SolvedQuestion(String question, String solution) {
        super(question);
        requireNonNull(solution);
        checkArgument(isValidSolution(solution), MESSAGE_SOLUTION_CONSTRAINTS);
        this.solution = solution;
    }

    /**
     * Returns true if the given String is valid.
     */
    public static boolean isValidSolution(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if both questions are solved and have similar details.
     * This is a stronger notion of equality between questions.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { // short circuit if same object
            return true;
        }

        if (!(obj instanceof SolvedQuestion)) { // instanceof handles nulls
            return false;
        }

        // state check
        SolvedQuestion other = (SolvedQuestion) obj;
        return question.equals(other.question) && solution.equals(other.solution);
    }

    @Override
    public String toString() {
        return String.format("%1$s %2$s [%3$s]", STATUS, super.toString(), solution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, solution);
    }

    @Override
    public boolean isResolved() {
        return true;
    }

}
