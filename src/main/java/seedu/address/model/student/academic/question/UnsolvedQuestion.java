package seedu.address.model.student.academic.question;

/**
 * Represents an unsolved question a student has for a tutor in Reeve.
 */
public class UnsolvedQuestion extends Question {

    private static final String STATUS = "(\u2718)";

    /**
     * Constructs a new UnsolvedQuestion object.
     */
    public UnsolvedQuestion(String question) {
        super(question);
    }

    /**
     * Returns true if both questions are unsolved and have similar details.
     * This is a stronger notion of equality between questions.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { // short circuit if same object
            return true;
        }

        if (!(obj instanceof UnsolvedQuestion)) { // instanceof handles nulls
            return false;
        }

        // state check
        UnsolvedQuestion other = (UnsolvedQuestion) obj;
        return question.equals(other.question);
    }

    @Override
    public String toString() {
        return String.format("%1$s %2$s", STATUS, super.toString());
    }

    @Override
    public int hashCode() {
        return question.hashCode();
    }

    @Override
    public boolean isResolved() {
        return false;
    }

}
