package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;

/**
 * Wraps all data at the Reeve level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class Reeve implements ReadOnlyReeve {

    private final UniqueStudentList students;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
    }

    public Reeve() {}

    /**
     * Creates a Reeve using the Students in the {@code toBeCopied}
     */
    public Reeve(ReadOnlyReeve toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Resets the existing data of this {@code Reeve} with {@code newData}.
     */
    public void resetData(ReadOnlyReeve newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in Reeve.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to Reeve.
     * The student must not already exist in Reeve.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in Reeve.
     * The student identity of {@code editedStudent} must not be the same as another existing student
     * in Reeve.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code Reeve}.
     * {@code key} must exist in Reeve.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    /**
     * Returns true if the given student clashes with another student's lesson time.
     */
    public boolean hasClashingClassTimeWith(Student test) {
        return students.hasClashingClassTimeWith(test);
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Reeve // instanceof handles nulls
                && students.equals(((Reeve) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
