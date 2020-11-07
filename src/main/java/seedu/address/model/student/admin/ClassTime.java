package seedu.address.model.student.admin;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

/**
 * Represents a ClassTime in Reeve.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassTime(String)}
 */
public class ClassTime implements Comparable<ClassTime> {

    public static final String MESSAGE_CONSTRAINTS =
            "Class Time should follow the following format: {int: day_of_week} {int: start_time}-{int: end_time}";
    public static final String TIME_CONSTRAINTS = "End time should always be after Start time";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String TIME_VALIDATION = "([01]?[0-9]|2[0-3])[0-5][0-9]";
    public static final String VALIDATION_REGEX = "([1-7])[\\s]" + TIME_VALIDATION + "-" + TIME_VALIDATION;
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHm");
    public static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("HHmm");

    public final DayOfWeek dayOfWeek;
    public final LocalTime startTime;
    public final LocalTime endTime;


    /**
     * Constructs a {@code ClassTime}.
     *
     * @param inputTime A valid ClassTime.
     */
    public ClassTime(String inputTime) {
        requireNonNull(inputTime);
        checkArgument(isValidClassTime(inputTime), MESSAGE_CONSTRAINTS);
        checkArgument(isValidStartAndEndTime(inputTime), TIME_CONSTRAINTS);
        this.dayOfWeek = extractDay(inputTime);
        this.startTime = extractStartTime(inputTime);
        this.endTime = extractEndTime(inputTime);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidClassTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    private static DayOfWeek extractDay(String input) {
        char day = input.charAt(0);
        return DayOfWeek.of(Integer.parseInt(String.valueOf(day)));
    }

    private static LocalTime extractStartTime(String input) {
        String startTime = input.substring(2, 6);
        return LocalTime.parse(startTime, TIME_FORMATTER);
    }

    private static LocalTime extractEndTime(String input) {
        String endTime = input.substring(7);
        return LocalTime.parse(endTime, TIME_FORMATTER);
    }

    public boolean isSameDay(DayOfWeek otherDay) {
        return this.dayOfWeek.equals(otherDay);
    }

    /**
     * Checks if input end time is after start time.
     * @param input Start and end times input by user.
     * @return True if times are valid, False if otherwise.
     */
    public static boolean isValidStartAndEndTime(String input) {
        LocalTime startTime = extractStartTime(input);
        LocalTime endTime = extractEndTime(input);

        return endTime.isAfter(startTime);
    }

    /**
     * Returns true if the two ClassTimes clash.
     */
    public boolean clashesWith(ClassTime other) {
        if (!dayOfWeek.equals(other.dayOfWeek)) {
            return false;
        }
        return endTime.isAfter(other.startTime) && startTime.isBefore(other.endTime);
    }

    /**
     * Converts a {@code classTime} object back to a user input string
     */
    public String convertClassTimeToUserInputString() {
        String startTimeString = this.startTime.format(OUTPUT);
        String endTimeString = this.endTime.format(OUTPUT);
        int dayOfWeek = this.dayOfWeek.getValue();
        final StringBuilder builder = new StringBuilder();
        builder.append(dayOfWeek)
                .append(" ")
                .append(startTimeString)
                .append("-")
                .append(endTimeString);
        return builder.toString();
    }

    @Override
    public String toString() {
        String dayDisplayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        return String.format("%1$s (%2$s - %3$s)",
                dayDisplayName,
                this.startTime.format(OUTPUT),
                this.endTime.format(OUTPUT));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassTime // instanceof handles nulls
                && dayOfWeek.equals(((ClassTime) other).dayOfWeek)
                && startTime.equals(((ClassTime) other).startTime)
                && endTime.equals(((ClassTime) other).endTime)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeek, startTime, endTime);
    }

    @Override
    public int compareTo(ClassTime o) {
        // same dayOfWeek => need to compare specific time
        if (this.dayOfWeek.equals(o.dayOfWeek)) {

            if (this.startTime.isBefore(o.startTime)) {
                return -1; // start time is earlier
            }
            // Here we are assuming there are no overlapping classTimes
            // i.e. there are no clash
            return 1; // start time is later

        }
        return this.dayOfWeek.getValue() - o.dayOfWeek.getValue();
    }
}
