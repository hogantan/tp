package seedu.address.model.student.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

public class ClassTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClassTime(null));
    }

    @Test
    public void constructor_invalidClassTime_throwsIllegalArgumentException() {
        // Completely empty input
        String emptyClassTime = "";
        assertThrows(IllegalArgumentException.class, () -> new ClassTime(emptyClassTime));

        // Invalid input
        String invalidInput = "2 hey sup";
        assertThrows(IllegalArgumentException.class, () -> new ClassTime(invalidInput));

        // Invalid Day of week (must be 1 - 7)
        String invalidDayOfWeek1 = "0 1000-1230";
        assertThrows(IllegalArgumentException.class, () -> new ClassTime(invalidDayOfWeek1));

        String invalidDayOfWeek2 = "9 1000-1200";
        assertThrows(IllegalArgumentException.class, () -> new ClassTime(invalidDayOfWeek2));

        // Invalid Start time
        String invalidStartTime = "0 2500-1230";
        assertThrows(IllegalArgumentException.class, () -> new ClassTime(invalidStartTime));

        // Invalid End time
        String invalidEndTime = "9 1000-0090";
        assertThrows(IllegalArgumentException.class, () -> new ClassTime(invalidEndTime));
    }

    @Test
    public void isValidClassTime() {
        // null class time
        assertThrows(NullPointerException.class, () -> ClassTime.isValidClassTime(null));

        // invalid class times
        assertFalse(ClassTime.isValidClassTime("")); // empty string
        assertFalse(ClassTime.isValidClassTime(" ")); // spaces only
        assertFalse(ClassTime.isValidClassTime("0 1000-1230")); // invalid day of week
        assertFalse(ClassTime.isValidClassTime("10 1000-1230")); // invalid day of week
        assertFalse(ClassTime.isValidClassTime("1 1090-1230")); // invalid start time
        assertFalse(ClassTime.isValidClassTime("2 1000-2930")); // invalid day of week

        // valid class times
        assertTrue(ClassTime.isValidClassTime("2 1000-2230"));
        assertTrue(ClassTime.isValidClassTime("7 0000-2359"));
    }

    @Test
    public void isSameDay() {
        ClassTime t1 = new ClassTime("2 1000-2230");
        DayOfWeek daySameAst1 = DayOfWeek.TUESDAY;
        DayOfWeek dayDifferentFromt1 = DayOfWeek.MONDAY;

        assertTrue(t1.isSameDay(daySameAst1));
        assertFalse(t1.isSameDay(dayDifferentFromt1));
        assertFalse(t1.isSameDay(null)); // equals method on null should return false
    }

    @Test
    public void isValidStartAndEndTime() {
        // invalid start and end times
        assertFalse(ClassTime.isValidStartAndEndTime("2 0900-0100")); // end time before start time
        assertFalse(ClassTime.isValidStartAndEndTime("6 1200-0900")); // end time before start time

        // valid start and end times
        assertTrue(ClassTime.isValidStartAndEndTime("2 0900-1200"));
        assertTrue(ClassTime.isValidStartAndEndTime("4 1100-1600"));

    }

    @Test
    public void clashesWith() {
        // same class time -> true
        ClassTime classTime = new ClassTime("5 1500-1700");
        assertTrue(classTime.clashesWith(classTime));

        // end after classTime start -> true
        ClassTime test = new ClassTime("5 1400-1501");
        assertTrue(classTime.clashesWith(test));

        // start before classTime end -> true
        test = new ClassTime("5 1659-1800");
        assertTrue(classTime.clashesWith(test));

        // complete overlap -> true
        test = new ClassTime("5 1459-1701");
        assertTrue(classTime.clashesWith(test));

        // different day -> false
        test = new ClassTime("3 1500-1700");
        assertFalse(classTime.clashesWith(test));

        // end just as classTime start
        test = new ClassTime("5 1400-1500");
        assertFalse(classTime.clashesWith(test));

        // start just as classTime end
        test = new ClassTime("5 1700-1800");
        assertFalse(classTime.clashesWith(test));
    }

    @Test
    public void compareTo() {
        ClassTime time = new ClassTime("2 1500-1700");
        ClassTime laterDayOfWeek = new ClassTime("3 1500-1700");
        ClassTime sameDayOfWeekEarlierTime = new ClassTime("2 1400-1500");

        assertEquals(-1, time.compareTo(laterDayOfWeek)); // earlier dayOfWeek

        assertEquals(1, time.compareTo(sameDayOfWeekEarlierTime)); // same dayOfWeek but later time

        assertThrows(NullPointerException.class, () -> time.compareTo(null)); // null throws exception

    }
}
