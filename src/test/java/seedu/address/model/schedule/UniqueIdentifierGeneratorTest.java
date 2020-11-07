package seedu.address.model.schedule;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UniqueIdentifierGeneratorTest {
    private static final String DEFAULT_EVENT_NAME = "eevnt name";
    private static final String DEFAULT_EVENT_START = "2020-12-03T10:15:30";
    private static final String DEFAULT_EVENT_END = "2020-12-03T10:17:30";

    @Test
    public void generateUid_nullEventName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                UniqueIdentifierGenerator.generateUid(null, DEFAULT_EVENT_START,
                        DEFAULT_EVENT_END));
    }

    @Test
    public void generateUid_nullEventStart_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                UniqueIdentifierGenerator.generateUid(DEFAULT_EVENT_NAME, null,
                        DEFAULT_EVENT_END));
    }

    @Test
    public void generateUid_nullEventEnd_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                UniqueIdentifierGenerator.generateUid(DEFAULT_EVENT_NAME, DEFAULT_EVENT_START,
                        null));
    }

}
