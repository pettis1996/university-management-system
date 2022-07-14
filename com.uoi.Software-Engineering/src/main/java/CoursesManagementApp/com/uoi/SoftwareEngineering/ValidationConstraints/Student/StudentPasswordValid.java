package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.EmailValidation;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.PasswordValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StudentPasswordValid implements ConstraintValidator<PasswordValidation, Student> {
    @Override
    public void initialize(PasswordValidation studentValidation) {
    }

    @Override
    public boolean isValid(Student student ,
                           ConstraintValidatorContext cxt) {
        return student.getPassWord().length()>=8 && student.checkPassword(student.getPassWord())==true;
    }
}
