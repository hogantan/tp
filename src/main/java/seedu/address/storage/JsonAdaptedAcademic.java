package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Student;
import seedu.address.model.student.academic.Academic;
import seedu.address.model.student.academic.Attendance;
import seedu.address.model.student.academic.exam.Exam;
import seedu.address.model.student.academic.question.Question;

public class JsonAdaptedAcademic {

    private final List<JsonAdaptedQuestion> questions = new ArrayList<>();
    private final List<JsonAdaptedAttendance> attendanceList = new ArrayList<>();
    private final List<JsonAdaptedExam> exams = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAcademic} with academic details.
     */
    @JsonCreator
    public JsonAdaptedAcademic(@JsonProperty("questions") List<JsonAdaptedQuestion> questions,
                               @JsonProperty("attendanceList") List<JsonAdaptedAttendance> attendanceList,
                               @JsonProperty("exams") List<JsonAdaptedExam> exams) {
        if (questions != null) {
            this.questions.addAll(questions);
        }
        if (attendanceList != null) {
            this.attendanceList.addAll(attendanceList);
        }
        if (exams != null) {
            this.exams.addAll(exams);
        }
    }

    /**
     * Converts a given {@code Academic} into this class for Jackson use.
     */
    public JsonAdaptedAcademic(Student source) {
        questions.addAll(source.getQuestions().stream()
                .map(JsonAdaptedQuestion::new)
                .collect(Collectors.toList()));
        attendanceList.addAll(source.getAttendance().stream()
                .map(JsonAdaptedAttendance::new)
                .collect(Collectors.toList()));
        exams.addAll(source.getExams().stream()
                .map(JsonAdaptedExam::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted admin object into the model's {@code Admin} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted admin.
     */
    public Academic toModelType() throws IllegalValueException {
        List<Question> modelQuestions = new ArrayList<>();
        for (JsonAdaptedQuestion question : questions) {
            modelQuestions.add(question.toModelType());
        }

        List<Attendance> modelAttendance = new ArrayList<>();
        for (JsonAdaptedAttendance attendance : attendanceList) {
            modelAttendance.add(attendance.toModelType());
        }

        List<Exam> modelExams = new ArrayList<>();
        for (JsonAdaptedExam exam : exams) {
            modelExams.add(exam.toModelType());
        }

        return new Academic(modelQuestions, modelAttendance, modelExams);
    }
}
