package seedu.address.model.student.admin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalStudents.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class AdminTest {

    @Test
    public void equals() {
        Admin aliceAdmin = ALICE.getAdmin();

        // same values should return true
        Admin adminCopy = new StudentBuilder(ALICE).build().getAdmin();
        assertTrue(adminCopy.equals(aliceAdmin));

        // same object should return true
        assertTrue(aliceAdmin.equals(aliceAdmin));

        // null returns false
        assertFalse(aliceAdmin.equals(null));

        // different types should return false
        assertFalse(aliceAdmin.equals(6));

        // different classVenue should return false
        Admin editedAliceAdmin = new StudentBuilder(ALICE).withClassVenue("Fairyland").build().getAdmin();
        assertFalse(adminCopy.equals(editedAliceAdmin));

        // different classTime should return false
        editedAliceAdmin = new StudentBuilder(ALICE).withClassTime("2 1200-1600").build().getAdmin();
        assertFalse(adminCopy.equals(editedAliceAdmin));

        // different fee should return false
        editedAliceAdmin = new StudentBuilder(ALICE).withFee("2").build().getAdmin();
        assertFalse(aliceAdmin.equals(editedAliceAdmin));

        // different paymentDate should return false
        editedAliceAdmin = new StudentBuilder(ALICE).withPaymentDate("28/2/19").build().getAdmin();
        assertFalse(aliceAdmin.equals(editedAliceAdmin));

        // different details should return false
        editedAliceAdmin = new StudentBuilder(ALICE).withDetails("nice", "schizophrenic").build().getAdmin();
        assertFalse(aliceAdmin.equals(editedAliceAdmin));
    }

    @Test
    public void hasClashingClassTime() {
        // same class time -> clash
        Admin admin = new StudentBuilder().build().getAdmin();
        assertTrue(admin.hasClashingClassTime(admin));

        // clashes -> return true
        Admin test = new StudentBuilder().withClassTime("1 2000-2100").build().getAdmin();
        assertFalse(admin.hasClashingClassTime(test));

        // does not clash -> return false
        test = new StudentBuilder().withClassTime("1 2130-2300").build().getAdmin();
        assertTrue(admin.hasClashingClassTime(test));
    }

}
