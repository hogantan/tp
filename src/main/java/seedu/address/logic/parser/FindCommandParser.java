package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FIND_SUPPORTED_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.SchoolContainsKeywordsPredicate;
import seedu.address.model.student.Year;
import seedu.address.model.student.YearMatchPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, FIND_SUPPORTED_PREFIXES);

        if (!anyPrefixesPresent(argMultimap, FIND_SUPPORTED_PREFIXES)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        FindCommand.FindStudentDescriptor findStudentDescriptor = new FindCommand.FindStudentDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String name = argMultimap.getValue(PREFIX_NAME).get();
            String[] nameKeywords = name.split("\\s+");
            List<String> nameKeywordsList = List.of(nameKeywords);
            findStudentDescriptor.setNamePredicate(new NameContainsKeywordsPredicate(nameKeywordsList));
        }
        if (argMultimap.getValue(PREFIX_SCHOOL).isPresent()) {
            String school = argMultimap.getValue(PREFIX_SCHOOL).get();
            String[] schoolKeywords = school.split("\\s+");
            List<String> schoolKeywordsList = List.of(schoolKeywords);
            findStudentDescriptor.setSchoolPredicate(new SchoolContainsKeywordsPredicate(schoolKeywordsList));
        }
        if (argMultimap.getValue(PREFIX_YEAR).isPresent()) {
            String stringYear = argMultimap.getValue(PREFIX_YEAR).get();
            Year year = ParserUtil.parseYear(stringYear);
            findStudentDescriptor.setYearPredicate(new YearMatchPredicate(year));
        }

        if (!findStudentDescriptor.isAnyPredicatePresent()) {
            throw new ParseException(FindCommand.FIELD_NOT_GIVEN);
        }

        return new FindCommand(findStudentDescriptor);
    }

    /**
     * Returns true if not all of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
