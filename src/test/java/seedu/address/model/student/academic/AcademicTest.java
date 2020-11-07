package seedu.address.model.student.academic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_FEEDBACK_AMY;
import static seedu.address.testutil.StudentBuilder.DEFAULT_EXAM_FYE;
import static seedu.address.testutil.StudentBuilder.DEFAULT_EXAM_MYE;
import static seedu.address.testutil.StudentBuilder.DEFAULT_QUESTION_MATH;
import static seedu.address.testutil.StudentBuilder.DEFAULT_QUESTION_NEWTON;
import static seedu.address.testutil.StudentBuilder.DEFAULT_SOLUTION;
import static seedu.address.testutil.TypicalStudents.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.academic.exam.Exam;
import seedu.address.model.student.academic.question.SolvedQuestion;
import seedu.address.model.student.academic.question.UnsolvedQuestion;
import seedu.address.testutil.StudentBuilder;

public class AcademicTest {

    @Test
    public void equals() {
        Academic aliceAcademic = ALICE.getAcademic();

        // same values should return true
        Academic academicCopy = new StudentBuilder(ALICE).build().getAcademic();
        assertTrue(aliceAcademic.equals(academicCopy));

        // same object return true
        assertTrue(aliceAcademic.equals(aliceAcademic));

        // null returns false
        assertFalse(aliceAcademic.equals(null));

        // different types should return false
        assertFalse(aliceAcademic.equals(6));

        // different attendance list returns false
        Academic editedAliceAcademic = new StudentBuilder(ALICE).withAttendances(new Attendance(ATTENDANCE_DATE_BOB,
                true, new Feedback("sleeping"))).build().getAcademic();
        assertFalse(aliceAcademic.equals(editedAliceAcademic));

        // different exam list returns false
        editedAliceAcademic = new StudentBuilder(ALICE).withExams(DEFAULT_EXAM_FYE).build().getAcademic();
        assertFalse(aliceAcademic.equals(editedAliceAcademic));

        // different question list returns false
        editedAliceAcademic = new StudentBuilder(ALICE).withQuestions().build().getAcademic();
        assertFalse(aliceAcademic.equals(editedAliceAcademic));
    }

    @Test
    public void containsQuestion() {
        String[] questions = new String[] {DEFAULT_QUESTION_MATH, DEFAULT_QUESTION_NEWTON};
        Academic aliceAcademic = ALICE.getAcademic();
        for (String question : questions) {
            assertTrue(aliceAcademic.containsQuestion(new UnsolvedQuestion(question)));
            assertTrue(aliceAcademic.containsQuestion(new SolvedQuestion(question, DEFAULT_SOLUTION)));
        }
    }

    @Test
    public void addQuestion() {
        Academic aliceAcademic = new StudentBuilder(ALICE).withQuestions().build().getAcademic();
        Academic acadWithQuestion = aliceAcademic.addQuestion(new UnsolvedQuestion(DEFAULT_QUESTION_MATH));
        Academic expected = new StudentBuilder(ALICE).withQuestions(DEFAULT_QUESTION_MATH).build().getAcademic();
        assertEquals(expected, acadWithQuestion);
    }

    @Test
    public void setQuestion() {
        Academic aliceAcademic = new StudentBuilder(ALICE).withQuestions(DEFAULT_QUESTION_MATH).build().getAcademic();
        Academic acadWithQuestion = aliceAcademic.setQuestion(new UnsolvedQuestion(DEFAULT_QUESTION_MATH),
                new SolvedQuestion(DEFAULT_QUESTION_MATH, DEFAULT_SOLUTION));
        Academic expected = new StudentBuilder(ALICE).withSolved(DEFAULT_SOLUTION, DEFAULT_QUESTION_MATH)
                .build().getAcademic();
        assertEquals(expected, acadWithQuestion);
    }

    @Test
    public void deleteQuestion() {
        Academic aliceAcademic = new StudentBuilder(ALICE).withQuestions(DEFAULT_QUESTION_MATH).build().getAcademic();
        Academic acadWithQuestion = aliceAcademic.deleteQuestion(new UnsolvedQuestion(DEFAULT_QUESTION_MATH));
        Academic expected = new StudentBuilder(ALICE).withQuestions().build().getAcademic();
        assertEquals(expected, acadWithQuestion);
    }

    @Test
    public void containsAttendance() {
        Feedback amyFeedback = new Feedback(VALID_ATTENDANCE_FEEDBACK_AMY);
        Attendance standard = new Attendance(ATTENDANCE_DATE_AMY, true, amyFeedback);
        Academic aliceAcademic = new StudentBuilder(ALICE).withAttendances(standard).build().getAcademic();
        assertTrue(aliceAcademic.containsAttendance(standard));

        Attendance test = new Attendance(ATTENDANCE_DATE_AMY, false, amyFeedback);
        assertTrue(aliceAcademic.containsAttendance(test));

        test = new Attendance(ATTENDANCE_DATE_AMY, true);
        assertTrue(aliceAcademic.containsAttendance(test));

        test = new Attendance(ATTENDANCE_DATE_BOB, true, amyFeedback);
        assertFalse(aliceAcademic.containsAttendance(test));
    }

    @Test
    public void getFormattedQuestions() {
        Academic aliceAcademic = new StudentBuilder(ALICE).withQuestions(DEFAULT_QUESTION_MATH,
                DEFAULT_QUESTION_NEWTON).build().getAcademic();
        String expectedText = "1. " + new UnsolvedQuestion(DEFAULT_QUESTION_MATH)
                + "\n2. " + new UnsolvedQuestion(DEFAULT_QUESTION_NEWTON) + "\n";
        assertEquals(expectedText, aliceAcademic.getFormattedQuestions());
    }

    @Test
    public void getFormattedAttendance() {
        Attendance amyAttendance = new Attendance(ATTENDANCE_DATE_AMY, true);
        Attendance bobAttendance = new Attendance(ATTENDANCE_DATE_BOB, true);
        Academic aliceAcademic = new StudentBuilder(ALICE).withAttendances(amyAttendance, bobAttendance)
                .build().getAcademic();
        String expectedText = "1. " + amyAttendance
                + "\n2. " + bobAttendance + "\n";
        assertEquals(expectedText, aliceAcademic.getFormattedAttendance());
    }

    @Test
    public void getFormattedExams() {
        Exam examFye = DEFAULT_EXAM_FYE;
        Exam examMye = DEFAULT_EXAM_MYE;
        Academic aliceAcademic = new StudentBuilder(ALICE).withExams(examFye, examMye)
                .build().getAcademic();
        String expectedText = "1." + examFye
                + "\n2." + examMye + "\n";
        assertEquals(expectedText, aliceAcademic.getFormattedExams());
    }


}
