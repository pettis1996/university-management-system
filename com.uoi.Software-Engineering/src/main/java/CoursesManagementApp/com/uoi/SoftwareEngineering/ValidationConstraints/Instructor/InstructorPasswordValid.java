package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Instructor;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.Instructor;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.EmailValidation;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.PasswordValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InstructorPasswordValid implements ConstraintValidator<PasswordValidation, Instructor> {
    @Override
    public void initialize(PasswordValidation instructorValidation) {

    }

    @Override
    public boolean isValid(Instructor instructor,
                           ConstraintValidatorContext cxt) {
        return instructor.getPassWord().length()>=8 && instructor.checkPassword(instructor.getPassWord())==true;
    }
}
