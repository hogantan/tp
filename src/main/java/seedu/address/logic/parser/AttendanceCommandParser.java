package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.ATTENDANCE_COMMAND_COMPULSORY_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.ATTENDANCE_COMMAND_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_FEEDBACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_STATUS;
import static seedu.address.logic.parser.ReeveParser.BASIC_COMMAND_FORMAT;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAttendanceCommand;
import seedu.address.logic.commands.AttendanceCommand;
import seedu.address.logic.commands.DeleteAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.academic.Attendance;
import seedu.address.model.student.academic.Feedback;

public class AttendanceCommandParser extends PrefixDependentParser<AttendanceCommand> {

    private static final String ERROR_ADD_ATTENDANCE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAttendanceCommand.MESSAGE_USAGE);
    private static final String ERROR_DEL_ATTENDANCE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAttendanceCommand.MESSAGE_USAGE);

    @Override
    public AttendanceCommand parse(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddAttendanceCommand.COMMAND_WORD:
            return parseAddAttendanceCommand(arguments);

        case DeleteAttendanceCommand.COMMAND_WORD:
            return parseDeleteAttendanceCommand(arguments);

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE));
        }
    }

    private AddAttendanceCommand parseAddAttendanceCommand(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, ATTENDANCE_COMMAND_PREFIXES);

        if (!areRequiredPrefixesPresent(argMultimap, ATTENDANCE_COMMAND_COMPULSORY_PREFIXES)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAttendanceCommand.MESSAGE_USAGE));
        }

        Index index;
        Attendance attendance;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            LocalDate attendanceDate =
                    ParserUtil.parseDate(argMultimap.getValue(PREFIX_ATTENDANCE_DATE).get());
            boolean isStudentPresent = ParserUtil.parseAttendanceStatus(
                    argMultimap.getValue(PREFIX_ATTENDANCE_STATUS).get());
            attendance = getAttendance(argMultimap, attendanceDate, isStudentPresent);
        } catch (ParseException pe) {
            throw new ParseException(ERROR_ADD_ATTENDANCE, pe);
        }

        return new AddAttendanceCommand(index, attendance);
    }

    private Attendance getAttendance(ArgumentMultimap argMultiMap, LocalDate date, boolean isPresent)
            throws ParseException {
        Optional<String> feedbackValue = argMultiMap.getValue(PREFIX_ATTENDANCE_FEEDBACK);

        if (feedbackValue.isEmpty()) {
            return new Attendance(date, isPresent);
        }

        try {
            Feedback feedback = ParserUtil.parseFeedback(feedbackValue.get());
            return new Attendance(date, isPresent, feedback);
        } catch (ParseException pe) {
            throw new ParseException(ERROR_ADD_ATTENDANCE, pe);
        }
    }

    private DeleteAttendanceCommand parseDeleteAttendanceCommand(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, ATTENDANCE_COMMAND_PREFIXES);

        if (!areRequiredPrefixesPresent(argMultimap, PREFIX_ATTENDANCE_DATE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteAttendanceCommand.MESSAGE_USAGE));
        }

        Index index;
        LocalDate lessonDate;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            lessonDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_ATTENDANCE_DATE).get());
        } catch (ParseException pe) {
            throw new ParseException(ERROR_DEL_ATTENDANCE, pe);
        }

        return new DeleteAttendanceCommand(index, lessonDate);
    }
}
