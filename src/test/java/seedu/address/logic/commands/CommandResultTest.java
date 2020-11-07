package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));

        assertTrue(commandResult.equals(new CommandResult("feedback", false, false,
                false, null)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, false, null)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, false, null)));

        // different toggle value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, true, null)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false, false, null).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true, false, null).hashCode());

        // different toggle value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, true, null).hashCode());

        // different toggle value -> returns different hashcode
        Student dummyStudent = new StudentBuilder().withName("Alice").build();
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false,
                false, false, dummyStudent).hashCode());
    }

    @Test
    public void getFeedbackToUser() {
        // different feedback input -> returns different feedback
        assertNotEquals("feedback", new CommandResult("heya").getFeedbackToUser());
        // same feedback input -> returns same feedback
        assertEquals("feedback", new CommandResult("feedback").getFeedbackToUser());
    }

    @Test
    public void getSelectedStudent() {
        Student dummyStudent = new StudentBuilder().withName("Alice").build();
        Student dummyStudentTwo = new StudentBuilder().withName("Timothy").build();

        // different students -> returns different student
        assertNotEquals(dummyStudent, new CommandResult("feedback", dummyStudentTwo)
                .getSelectedStudent());
        // same students -> returns same student
        assertEquals(dummyStudent, new CommandResult("feedback", dummyStudent).getSelectedStudent());

        // different students -> returns different student
        assertNotEquals(dummyStudentTwo, new CommandResult("feedback", false,
                false, false, dummyStudent).getSelectedStudent());
        // same students -> returns same student
        assertEquals(dummyStudent, new CommandResult("feedback", false,
                false, false, dummyStudent).getSelectedStudent());
    }

    @Test
    public void isShowHelp() {
        // no showHelp input -> default is false -> returns false
        assertNotEquals(true, new CommandResult("feedback").isShouldShowHelp());
        // no showHelp input -> default is false -> returns false
        assertEquals(false, new CommandResult("feedback").isShouldShowHelp());

        Student dummyStudent = new StudentBuilder().withName("Alice").build();
        // different showHelp -> returns different showHelp
        assertNotEquals(true, new CommandResult("feedback", false,
                false, false, dummyStudent).isShouldShowHelp());
        // same showHelp -> returns same showHelp
        assertEquals(true, new CommandResult("feedback", true,
                false, false, dummyStudent).isShouldShowHelp());
    }

    @Test
    public void isExit() {
        // no exit input -> default is false -> returns false
        assertNotEquals(true, new CommandResult("feedback").isShouldExit());
        // no exit input -> default is false -> returns false
        assertEquals(false, new CommandResult("feedback").isShouldExit());

        Student dummyStudent = new StudentBuilder().withName("Alice").build();
        // different exit -> returns different exit
        assertNotEquals(true, new CommandResult("feedback", false,
                false, false, dummyStudent).isShouldExit());
        // same exit -> returns same exit
        assertEquals(true, new CommandResult("feedback", false,
                true, false, dummyStudent).isShouldExit());
    }

    @Test
    public void isSchedule() {
        // no showSchedule input -> default is false -> returns false
        assertNotEquals(true, new CommandResult("feedback").isShouldShowSchedule());
        // no showSchedule input -> default is false -> returns false
        assertEquals(false, new CommandResult("feedback").isShouldShowSchedule());

        // different showSchedule -> returns different showSchedule
        assertNotEquals(true, new CommandResult("feedback", false,
                false, false, false).isShouldShowSchedule());
        // same showSchedule -> returns same showSchedule
        assertEquals(true, new CommandResult("feedback", false,
                false, true, false).isShouldShowSchedule());
    }

    @Test
    public void isToggleStudentCard() {
        // no toggleStudentCard input -> default is false -> returns false
        assertNotEquals(true, new CommandResult("feedback").isToggleStudentCard());
        // no toggleStudentCard input -> default is false -> returns false
        assertEquals(false, new CommandResult("feedback").isToggleStudentCard());

        // different toggleStudentCard -> returns different toggleStudentCard
        assertNotEquals(true, new CommandResult("feedback", false,
                false, false, false).isToggleStudentCard());
        // same toggleStudentCard -> returns same toggleStudentCard
        assertEquals(true, new CommandResult("feedback", false,
                false, false, true).isToggleStudentCard());
    }

    @Test
    public void isExamStats() {
        // no student input -> isExamStats should be false
        assertNotEquals(true, new CommandResult("feedback").isExamStats());
        assertEquals(false, new CommandResult("feedback").isExamStats());

        Student dummyStudent = new StudentBuilder().withName("Alice").build();

        // student input -> isExamStats should be true
        assertNotEquals(false, new CommandResult("feedback", false,
                false, false, dummyStudent).isExamStats());
        assertEquals(true, new CommandResult("feedback", false,
                false, false, dummyStudent).isExamStats());
    }

    @Test
    public void toString_test() {
        assertNotEquals("heya", new CommandResult("feedback").toString());
        assertEquals("feedback", new CommandResult("feedback").toString());
    }
}
