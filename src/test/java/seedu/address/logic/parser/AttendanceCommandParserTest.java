package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_DESC_WITH_FEEDBACK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_FEEDBACK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_STATUS_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_FEEDBACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAttendanceCommand;
import seedu.address.logic.commands.AttendanceCommand;
import seedu.address.logic.commands.DeleteAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.academic.Attendance;
import seedu.address.model.student.academic.Feedback;

public class AttendanceCommandParserTest {

    private static final String ADD_ATTENDANCE_DESC = AddAttendanceCommand.COMMAND_WORD + " ";
    private static final String DELETE_ATTENDANCE_DESC = DeleteAttendanceCommand.COMMAND_WORD + " ";
    private static final Feedback VALID_FEEDBACK = new Feedback(VALID_ATTENDANCE_FEEDBACK_AMY);

    private static final Attendance VALID_ATTENDANCE_AMY =
            new Attendance(ATTENDANCE_DATE_AMY, true, VALID_FEEDBACK);
    private static final Attendance VALID_ATTENDANCE_BOB = new Attendance(ATTENDANCE_DATE_BOB, false);

    private final AttendanceCommandParser parser = new AttendanceCommandParser();

    @Test
    public void parse_addAttendanceCompulsoryFieldsPresent_success() {
        Index targetStudentIndex = INDEX_SECOND_PERSON;
        String userInput = ADD_ATTENDANCE_DESC + targetStudentIndex.getOneBased() + ATTENDANCE_DESC_WITH_FEEDBACK_AMY;
        AddAttendanceCommand expectedCommand = new AddAttendanceCommand(targetStudentIndex, VALID_ATTENDANCE_AMY);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addAttendanceAllFieldsPresent_success() {
        Index targetStudentIndex = INDEX_SECOND_PERSON;
        String userInput = ADD_ATTENDANCE_DESC + targetStudentIndex.getOneBased() + ATTENDANCE_DESC_BOB;
        AddAttendanceCommand expectedCommand = new AddAttendanceCommand(targetStudentIndex, VALID_ATTENDANCE_BOB);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addAttendanceMissingParts_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddAttendanceCommand.MESSAGE_USAGE);
        String attendanceDateDesc = " " + PREFIX_ATTENDANCE_DATE + VALID_ATTENDANCE_AMY;
        String isPresentDesc = " " + PREFIX_ATTENDANCE_STATUS + VALID_ATTENDANCE_STATUS_AMY;
        String targetStudentIndex = INDEX_SECOND_PERSON.toString();

        // missing index and prefix
        assertParseFailure(parser, ADD_ATTENDANCE_DESC, expectedMessage);

        // missing both prefixes
        assertParseFailure(parser, ADD_ATTENDANCE_DESC + targetStudentIndex, expectedMessage);
        // missing attendance presence prefix
        assertParseFailure(parser, ADD_ATTENDANCE_DESC + targetStudentIndex + attendanceDateDesc, expectedMessage);
        // missing date prefix
        assertParseFailure(parser, ADD_ATTENDANCE_DESC + targetStudentIndex + isPresentDesc, expectedMessage);

        // missing index, valid prefix
        assertParseFailure(parser, ADD_ATTENDANCE_DESC + ATTENDANCE_DESC_WITH_FEEDBACK_AMY, expectedMessage);
    }

    @Test
    public void parse_addAttendanceInvalidFeedback_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAttendanceCommand.MESSAGE_USAGE);
        String targetStudentIndex = "1 ";
        String invalidFeedbackDesc = ADD_ATTENDANCE_DESC + targetStudentIndex + ATTENDANCE_DESC_AMY + " "
                + PREFIX_ATTENDANCE_FEEDBACK + "#48as8@3$$$!!!"; // invalid special characters
        assertParseFailure(parser, invalidFeedbackDesc, expectedMessage);
    }

    @Test
    public void parse_deleteAttendanceAllFieldsPresent_success() {
        Index targetStudentIndex = INDEX_SECOND_PERSON;
        String targetAttendanceDate = " " + PREFIX_ATTENDANCE_DATE + VALID_ATTENDANCE_DATE_AMY;
        String userInput = DELETE_ATTENDANCE_DESC + targetStudentIndex.getOneBased() + targetAttendanceDate;
        DeleteAttendanceCommand expectedCommand = new DeleteAttendanceCommand(targetStudentIndex, ATTENDANCE_DATE_AMY);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_deleteAttendanceMissingParts_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAttendanceCommand.MESSAGE_USAGE);

        // missing 2 arguments
        assertParseFailure(parser, DELETE_ATTENDANCE_DESC, expectedMessage);

        // missing 1 argument
        assertParseFailure(parser, DELETE_ATTENDANCE_DESC + "2", expectedMessage);

        String targetAttendanceDate = String.format("%1$s%2$s", PREFIX_ATTENDANCE_DATE, VALID_ATTENDANCE_DATE_AMY);
        assertParseFailure(parser, DELETE_ATTENDANCE_DESC + targetAttendanceDate, expectedMessage);

        // wrong date format
        String invalidDate = " " + PREFIX_ATTENDANCE_DATE + "2020-03-22";
        assertParseFailure(parser, DELETE_ATTENDANCE_DESC + "2" + invalidDate, expectedMessage);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, expectedMessage, () -> parser.parse(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, expectedMessage, () -> parser.parse("unknownCommand"));
    }
}
