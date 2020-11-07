package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateUtilTest {

    @Test
    public void isValidDate_format() {
        // d-M-yy
        assertTrue(DateUtil.isValidDate("2/9/20"));
        assertTrue(DateUtil.isValidDate("2/09/20"));
        assertTrue(DateUtil.isValidDate("02/9/20"));
        assertTrue(DateUtil.isValidDate("02/09/20"));

        // d-M-yyyy
        assertTrue(DateUtil.isValidDate("2/9/2020"));
        assertTrue(DateUtil.isValidDate("2/09/2020"));
        assertTrue(DateUtil.isValidDate("02/9/2020"));
        assertTrue(DateUtil.isValidDate("02/09/2020"));

        // invalid format
        assertFalse(DateUtil.isValidDate("")); // blank
        assertFalse(DateUtil.isValidDate("abc")); // not even a date
        assertFalse(DateUtil.isValidDate("2-9-20")); // d-M-yy format
        assertFalse(DateUtil.isValidDate("2.9.20")); // d.M.yy format
        assertFalse(DateUtil.isValidDate("2/9/2")); // d/M/y format
        assertFalse(DateUtil.isValidDate("2020/9/2")); // yyyy-M-d format
    }

    @Test
    public void isValidDate_date() {
        assertTrue(DateUtil.isValidDate("1/10/20"));
        assertTrue(DateUtil.isValidDate("31/10/20"));
        assertTrue(DateUtil.isValidDate("28/2/19"));
        assertTrue(DateUtil.isValidDate("29/2/20"));
        assertTrue(DateUtil.isValidDate("30/11/20"));

        assertFalse(DateUtil.isValidDate("0/10/20"));
        assertFalse(DateUtil.isValidDate("32/10/20"));
        assertFalse(DateUtil.isValidDate("29/2/19"));
        assertFalse(DateUtil.isValidDate("30/2/20"));
        assertFalse(DateUtil.isValidDate("31/11/20"));
    }

    @Test
    public void parseToDate_invalidArgument_throwsException() {
        String invalidFormat = " ";
        String invalidDate = "32/3/2020";

        assertThrows(IllegalArgumentException.class, () -> DateUtil.parseToDate(invalidFormat));
        assertThrows(IllegalArgumentException.class, () -> DateUtil.parseToDate(invalidDate));
    }

    @Test
    public void parseToDate_validArgument_success() {
        String validDate = "28/2/2020";
        LocalDate expected = LocalDate.parse(validDate, DateUtil.FORMAT_IN);
        assertEquals(expected, DateUtil.parseToDate(validDate));

        validDate = "28/2/20";
        assertEquals(expected, DateUtil.parseToDate(validDate));
    }

    @Test
    public void print() {
        LocalDate validDate = DateUtil.parseToDate("28/2/20");
        String expected = validDate.format(DateUtil.FORMAT_OUT);
        assertEquals(expected, DateUtil.print(validDate));
    }

    @Test
    public void getUserInput() {
        LocalDate validDate = DateUtil.parseToDate("28/2/20");
        String expected = validDate.format(DateUtil.FORMAT_IN);
        assertEquals(expected, DateUtil.getInputFormat(validDate));
    }
}
