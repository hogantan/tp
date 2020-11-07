package seedu.address.logic.parser;

import java.util.stream.Stream;

import seedu.address.logic.commands.Command;

/**
 * Represents a {@code Parser} that makes use of Prefixes in {@link CliSyntax}.
 */
public abstract class PrefixDependentParser<T extends Command> implements Parser<T> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    protected boolean areRequiredPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
