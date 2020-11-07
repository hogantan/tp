package seedu.address.model.student.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DetailTest {

    static final Detail VALID_DETAIL = new Detail("smart");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Detail(null));
    }

    @Test
    public void constructor_invalidDetail_throwsIllegalArgumentException() {
        String invalidDetail = "";
        assertThrows(IllegalArgumentException.class, () -> new Detail(invalidDetail));
    }

    @Test
    public void isValidDetail() {
        // null detail
        assertThrows(NullPointerException.class, () -> Detail.isValidDetail(null));

        // invalid detail - only alphanumeric chars
        assertFalse(Detail.isValidDetail("@$!!!"));

        // valid detail
        assertTrue(Detail.isValidDetail("smart lad"));
    }

    @Test
    public void equals() {
        // different object
        assertNotEquals(VALID_DETAIL, "hey");

        // diff fields
        assertNotEquals(VALID_DETAIL, new Detail("dumb"));

        // same object
        assertEquals(VALID_DETAIL, VALID_DETAIL);

        // same fields
        assertEquals(VALID_DETAIL, new Detail("smart"));
    }


}
