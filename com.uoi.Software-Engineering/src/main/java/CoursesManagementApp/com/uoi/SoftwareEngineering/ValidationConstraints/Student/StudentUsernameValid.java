package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.EmailValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StudentUsernameValid implements ConstraintValidator<StudentUsernameValidation, Student> {
    @Override
    public void initialize(StudentUsernameValidation studentValidation) {
        ConstraintValidator.super.initialize(studentValidation);
    }

    @Override
    public boolean isValid(Student student,ConstraintValidatorContext cxt) {
        return
                student.getUserName().equals("cs0"+student.getAm());
    }
}
