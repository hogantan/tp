package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helper functions for handling dates
 */
public class DateUtil {

    public static final String DATE_CONSTRAINTS = "Dates should be a valid date in the form dd/mm/yyyy, "
            + "and should not be blank";

    public static final Pattern DATE_PATTERN =
            Pattern.compile("(?<day>[0-9]{1,2})(/)(?<month>[0-9]{1,2})(/)(?<year>[0-9]{2}|[0-9]{4})");

    // Input format for dates
    public static final DateTimeFormatter FORMAT_IN = DateTimeFormatter.ofPattern("d/M/yyyy");
    // Output format for printing
    public static final DateTimeFormatter FORMAT_OUT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public static final String TODAY = LocalDate.now().format(FORMAT_IN);

    /**
     * Returns true if the input string provided is a valid date format.
     */
    public static boolean isValidDate(String test) {
        requireNonNull(test);
        try {
            parseToDate(test);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Attempts to parse a given input string into a valid {@code LocalDate} object.
     * This is the more stringent method of date parsing, and can be used for validating inputs.
     *
     * @throws IllegalArgumentException if input string is not a valid date.
     */
    public static LocalDate parseToDate(String input) {
        requireNonNull(input);

        Matcher matcher = DATE_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(DATE_CONSTRAINTS);
        }

        String day = matcher.group("day");
        String month = matcher.group("month");
        String year = matcher.group("year");
        return convertToDate(day, month, year);
    }

    /**
     * Returns a "dd MMM yyyy" representation of a date.
     * This is for displaying in a GUI. For example, "21/3/2020" will be formatted as "21 Mar 2020".
     */
    public static String print(LocalDate date) {
        requireNonNull(date);
        return date.format(FORMAT_OUT);
    }

    /**
     * Returns a String representing the date in "d/M/yyyy" form.
     * This is to facilitate saving date objects as strings to be parsed again in future.
     */
    public static String getInputFormat(LocalDate date) {
        requireNonNull(date);
        return date.format(FORMAT_IN);
    }

    /**
     * Converts a given day, month and year into a {@code LocalDate} object.
     * This is a stricter version of using {@code LocalDate.parse()} since it does not allow inputs such as
     * 31 Feb to be parsed.
     *
     * @throws IllegalArgumentException if inputs do not form a valid date.
     */
    private static LocalDate convertToDate(String day, String month, String year) {
        requireAllNonNull(day, month, year);

        int dayInt = Integer.parseInt(day);
        int monthInt = Integer.parseInt(month);
        int yearInt = year.length() == 2 ? Integer.parseInt("20" + year) : Integer.parseInt(year);

        try {
            return LocalDate.of(yearInt, monthInt, dayInt);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(DATE_CONSTRAINTS);
        }
    }
}
