package seedu.address.model.student.academic;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.address.model.student.academic.exam.Exam;
import seedu.address.model.student.academic.question.Question;

/**
 * Represents all academic details of a Student in Reeve.
 * Consists of questions, exams and attendance.
 */
public class Academic {

    private final List<Question> questions = new ArrayList<>();
    private final List<Attendance> attendance = new ArrayList<>();
    private final List<Exam> exams = new ArrayList<>();

    /**
     * Builds an Academic object.
     * @param attendance list of attendance for student.
     */
    public Academic(List<Question> questions, List<Attendance> attendance, List<Exam> exams) {
        requireAllNonNull(questions, attendance, exams);
        this.questions.addAll(questions);
        this.attendance.addAll(attendance);
        this.exams.addAll(exams);
    }

    private Academic(Academic copy) {
        this.questions.addAll(copy.questions);
        this.attendance.addAll(copy.attendance);
        this.exams.addAll(copy.exams);
    }

    public List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    public List<Attendance> getAttendance() {
        return new ArrayList<>(attendance);
    }

    public List<Exam> getExams() {
        return new ArrayList<>(exams);
    }

    /**
     * Get Question of student formatted for GUI use.
     * @return formatted questions.
     */
    public String getFormattedQuestions() {
        String result = "";
        int index = 1;
        for (Question question : questions) {
            result = result + index + ". " + question.toString() + "\n";
            index++;
        }
        return result;
    }

    /**
     * Get Attendance of student formatted for GUI use.
     * @return formatted exams.
     */
    public String getFormattedAttendance() {
        String result = "";
        int index = 1;
        for (Attendance singleAttendance : attendance) {
            result = result + index + ". " + singleAttendance + "\n";
            index++;
        }
        return result;
    }

    /**
     * Get exams of student formatted for GUI use.
     * @return formatted exams.
     */
    public String getFormattedExams() {
        String result = "";
        int index = 1;
        for (Exam exam : exams) {
            result = result + index + "." + exam.toString() + "\n";
            index++;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attendance);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Academic)) {
            return false;
        }

        Academic other = (Academic) obj;
        return other.getAttendance().equals(getAttendance())
                && other.getExams().equals(getExams())
                && other.getQuestions().equals(getQuestions());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        if (!questions.isEmpty()) {
            builder.append("\nQuestions:\n");
            String questionList = questions.stream()
                    .map(Question::toString)
                    .collect(Collectors.joining("\n"));
            builder.append(questionList);
        }

        if (!attendance.isEmpty()) {
            builder.append("\nAttendance:\n");
            String detailList = attendance.stream()
                    .map(detail -> String.format("- %s", detail))
                    .collect(Collectors.joining("\n"));
            builder.append(detailList);
        }

        if (!exams.isEmpty()) {
            builder.append("\nExams:\n");
            exams.forEach(builder::append);
        }

        return builder.toString();
    }

    //==============QUESTION OPERATIONS==============//

    /**
     * Returns true if there is an existing {@code Question} in the question list that has the same question.
     */
    public boolean containsQuestion(Question question) {
        return questions.stream().anyMatch(question::isSameQuestion);
    }

    /**
     * Creates a new academic object with a newly added question at the end of the questions list.
     * The newly added question must not already be present in the list.
     * This operation preserves the immutability of the academic class.
     */
    public Academic addQuestion(Question question) {
        assert !containsQuestion(question);
        requireNonNull(question);

        Academic replacement = new Academic(this);
        replacement.questions.add(question);
        return replacement;
    }

    /**
     * Creates a new academic object with a modified question replacing the previous question in the list.
     * The question to be replaced must be already present in the list, and the replacement must be absent.
     * This operation preserves the immutability of the Academic class.
     */
    public Academic setQuestion(Question target, Question newQuestion) {
        assert questions.contains(target) && !questions.contains(newQuestion);
        requireAllNonNull(target, newQuestion);

        Academic replacement = new Academic(this);
        int location = replacement.questions.indexOf(target);
        replacement.questions.set(location, newQuestion);
        return replacement;
    }

    /**
     * Creates a new academic object with the specified question removed from the list.
     * The question to delete must already be in the list.
     * This operation preserves the immutability of the Academic class.
     */
    public Academic deleteQuestion(Question target) {
        assert questions.contains(target);
        requireNonNull(target);

        Academic replacement = new Academic(this);
        replacement.questions.remove(target);
        return replacement;
    }

    //==============ATTENDANCE OPERATIONS==============//

    /**
     * Returns true if there is an existing {@code Attendance} in the attendance list that shares the same date.
     */
    public boolean containsAttendance(Attendance other) {
        return attendance.stream().anyMatch(other::isSameAttendance);
    }

}
