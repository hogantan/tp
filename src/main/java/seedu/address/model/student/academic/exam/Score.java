package seedu.address.model.student.academic.exam;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an exam's score in an Exam.
 * Guarantees: immutable; is valid as declared in {@link #isValidExamScore(String)}
 */
public class Score {

    public static final String MESSAGE_CONSTRAINTS =
            "Scores should be in the form x/y, where x and y are both whole numbers and x is less than "
                    + "or equal to y";

    public final double marksObtained;
    public final double totalMarks;
    public final String examScore;

    /**
     * Constructs a {@code Score}.
     *
     * @param score A valid exam score.
     */
    public Score(String score) {
        requireNonNull(score);
        checkArgument(isValidExamScore(score), MESSAGE_CONSTRAINTS);
        String[] splitScore = score.split("/");
        marksObtained = Double.parseDouble(splitScore[0]);
        totalMarks = Double.parseDouble(splitScore[1]);
        examScore = marksObtained + "/" + totalMarks;
    }

    /**
     * Returns true if a given string is a valid score.
     */
    public static boolean isValidExamScore(String score) {
        String[] splitScore = score.split("/");

        if (splitScore.length != 2) {
            return false;
        }

        double marksObtained;
        double totalMarks;

        try {
            marksObtained = Double.parseDouble(splitScore[0]);
            totalMarks = Double.parseDouble(splitScore[1]);
        } catch (NumberFormatException e) {
            return false;
        }

        if (marksObtained < 0 || totalMarks < 0) {
            return false;
        }

        return marksObtained <= totalMarks;
    }

    /**
     * Get percentage converted score.
     * @return score in terms of percentage.
     */
    public double getScorePercentage() {
        return Math.round((marksObtained / totalMarks) * 1000) / 10.0;
    }

    @Override
    public String toString() {
        return examScore;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Score // instanceof handles nulls
                && examScore.equals(((Score) other).examScore)); // state check
    }

    @Override
    public int hashCode() {
        return examScore.hashCode();
    }
}
