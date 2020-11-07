package seedu.address.model.student.admin;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.DateUtil.getInputFormat;
import static seedu.address.commons.util.DateUtil.parseToDate;
import static seedu.address.commons.util.DateUtil.print;

import java.time.LocalDate;

/**
 * Represents the last time a Student paid his tuition fees.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class PaymentDate {

    public static final String MESSAGE_CONSTRAINTS = "Payment dates should be a valid date in the form dd/mm/yy, "
            + "should not be blank, "
            + "and should not be future-dated";

    public final LocalDate lastPaid;

    /**
     * Constructs a {@code PaymentDate}.
     *
     * @param lastPaid A valid payment date.
     */
    public PaymentDate(String lastPaid) {
        requireNonNull(lastPaid);
        checkArgument(isValidDate(lastPaid), MESSAGE_CONSTRAINTS);
        this.lastPaid = parseToDate(lastPaid);
    }

    /**
     * Returns true if a given string is in the correct date format.
     */
    public static boolean isValidDate(String test) {
        try {
            return !parseToDate(test).isAfter(LocalDate.now());
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public String getUserInputDate() {
        return getInputFormat(lastPaid);
    }

    @Override
    public String toString() {
        return print(lastPaid);
    }

    @Override
    public boolean equals(Object obj) {
        return (this == obj) // short circuit if same object
                || (obj instanceof PaymentDate) // instanceof handles nulls
                && lastPaid.equals(((PaymentDate) obj).lastPaid); // state check
    }

    @Override
    public int hashCode() {
        return lastPaid.hashCode();
    }

}
