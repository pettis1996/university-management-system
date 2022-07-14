package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.PasswordValidation;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.YearValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StudentYearValid implements ConstraintValidator<YearValidation, Student> {
    @Override
    public void initialize(YearValidation studentValidation) {
    }

    @Override
    public boolean isValid(Student student ,
                           ConstraintValidatorContext cxt) {
        return student.getYear()>0;
    }
}
