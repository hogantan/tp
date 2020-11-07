package seedu.address.model.student.admin;

import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.address.model.student.Student;

/**
 * Tests that a {@code Student}'s {@code PaymentDate} is more than a month earlier than the current date.
 */
public class OverdueFeePredicate implements Predicate<Student> {

    private final LocalDate currentDate;

    /**
     * Constructs a new OverdueFeePredicate.
     */
    public OverdueFeePredicate() {
        currentDate = LocalDate.now();
    }

    /**
     * Returns true if the student's date of last payment is more than one month from the current date.
     */
    @Override
    public boolean test(Student student) {
        return student.getPaymentDate().lastPaid.isBefore(currentDate.minusMonths(1))
                && student.getAdmin().getFee().amount > 0;
    }
}
