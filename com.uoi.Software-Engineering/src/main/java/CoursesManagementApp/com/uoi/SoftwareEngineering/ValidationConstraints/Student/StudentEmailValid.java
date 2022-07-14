package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.EmailValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StudentEmailValid implements ConstraintValidator<EmailValidation, Student> {
    @Override
    public void initialize(EmailValidation studentValidation) {
        ConstraintValidator.super.initialize(studentValidation);
    }

    @Override
    public boolean isValid(Student student ,
                           ConstraintValidatorContext cxt) {
            return   student.getEmail().startsWith("cs0")
                      && (student.isEmailValid(student.getEmail(), student.getUserName()) == true);
    }
}
