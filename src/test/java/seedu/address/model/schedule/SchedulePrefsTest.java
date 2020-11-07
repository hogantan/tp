package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class SchedulePrefsTest {

    private LocalDate date = LocalDate.of(2020, 10, 29);
    private SchedulePrefs schedulePrefs = new SchedulePrefs(ScheduleViewMode.WEEKLY, date);
    @Test
    public void setViewMode_nullViewMode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedulePrefs.setViewMode(null));
    }

    @Test
    public void setViewMode_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedulePrefs.setViewDate(null));
    }

    @Test
    public void equals() {
        SchedulePrefs otherSchedulePrefs = new SchedulePrefs(ScheduleViewMode.WEEKLY, date);

        // same object returns true
        assertTrue(schedulePrefs.equals(schedulePrefs));

        // same ScheduleViewMode and Date return true
        assertTrue(otherSchedulePrefs.equals(schedulePrefs));

        // null returns false
        assertFalse(schedulePrefs.equals(null));

        // same ScheduleViewMode different Date return false
        LocalDate diffDate = LocalDate.of(2020, 10, 20);
        SchedulePrefs diffDateSchedulePrefs = new SchedulePrefs(ScheduleViewMode.WEEKLY, diffDate);
        assertFalse(schedulePrefs.equals(diffDateSchedulePrefs));

        // same Date different ScheduleViewMode return false
        SchedulePrefs diffViewModePrefs = new SchedulePrefs(ScheduleViewMode.DAILY, date);
        assertFalse(schedulePrefs.equals(diffViewModePrefs));
    }


}
