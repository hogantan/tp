package seedu.address.model.schedule;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;

/**
 * A utility for generating unique identifier strings for Event as used by VEvent.
 */
public class UniqueIdentifierGenerator {
    /**
     * Generates a unique string identifier for the Events object as required by VEvent.
     */
    public static String generateUid(String eventName, String eventStartDateTime, String eventEndDateTime) {
        requireAllNonNull(eventName, eventStartDateTime, eventEndDateTime);
        StringBuilder builder = new StringBuilder();
        builder.append(LocalDateTime.now().toString())
                .append("/")
                .append(eventName)
                .append("/")
                .append(eventStartDateTime)
                .append("/")
                .append(eventEndDateTime);
        return builder.toString();
    }
}
