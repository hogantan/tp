package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.Student;
import seedu.address.model.student.Year;
import seedu.address.model.student.academic.Academic;
import seedu.address.model.student.admin.Admin;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String phone;
    private final String school;
    private final String year;

    @JsonProperty("admin")
    private final JsonAdaptedAdmin jsonAdaptedAdmin;

    @JsonProperty("academic")
    private final JsonAdaptedAcademic jsonAdaptedAcademic;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("school") String school, @JsonProperty("year") String year,
                              @JsonProperty("admin") JsonAdaptedAdmin admin,
                              @JsonProperty("academic") JsonAdaptedAcademic academic) {
        this.name = name;
        this.phone = phone;
        this.school = school;
        this.year = year;
        this.jsonAdaptedAdmin = admin;
        this.jsonAdaptedAcademic = academic;
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        school = source.getSchool().school;
        year = String.valueOf(source.getYear().toString());
        jsonAdaptedAdmin = new JsonAdaptedAdmin(source);
        jsonAdaptedAcademic = new JsonAdaptedAcademic(source);
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Student toModelType() throws IllegalValueException {
        final Name modelName = getModelName();
        final Phone modelPhone = getModelPhone();
        final School modelSchool = getModelSchool();
        final Year modelYear = getModelYear();
        final Admin modelAdmin = getModelAdmin();
        final Academic modelAcademic = getModelAcademic();

        return new Student(modelName, modelPhone, modelSchool, modelYear, modelAdmin, modelAcademic);
    }

    private Name getModelName() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }

    private Phone getModelPhone() throws IllegalValueException {
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(phone);
    }

    private School getModelSchool() throws IllegalValueException {
        if (school == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    School.class.getSimpleName()));
        }
        if (!School.isValidSchool(school)) {
            throw new IllegalValueException(School.MESSAGE_CONSTRAINTS);
        }
        return new School(school);
    }

    private Year getModelYear() throws IllegalValueException {
        if (year == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Year.class.getSimpleName()));
        }
        if (!Year.isValidYear(year)) {
            throw new IllegalValueException(Year.MESSAGE_CONSTRAINTS);
        }
        return new Year(year);
    }

    private Admin getModelAdmin() throws IllegalValueException {
        if (jsonAdaptedAdmin == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Admin.class.getSimpleName()));
        }
        return jsonAdaptedAdmin.toModelType();
    }

    private Academic getModelAcademic() throws IllegalValueException {
        if (jsonAdaptedAcademic == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Academic.class.getSimpleName()));
        }
        return jsonAdaptedAcademic.toModelType();
    }

}
