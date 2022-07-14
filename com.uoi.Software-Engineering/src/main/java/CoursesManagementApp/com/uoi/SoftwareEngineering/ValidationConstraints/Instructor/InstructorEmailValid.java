package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Instructor;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.Instructor;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.EmailValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InstructorEmailValid implements ConstraintValidator<EmailValidation, Instructor> {
    @Override
    public void initialize(EmailValidation studentValidation) {
    }

    @Override
    public boolean isValid(Instructor instructor ,
                           ConstraintValidatorContext cxt) {
        return   instructor.getEmail().startsWith("cs0")
                && (instructor.isEmailValid(instructor.getEmail(),instructor.getUserName()) == true);
    }
}
