package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.AmValidation;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.EmailValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StudentAmValid implements ConstraintValidator<AmValidation, Student> {
    @Override
    public void initialize(AmValidation studentValidation) {
    }

    @Override
    public boolean isValid(Student student ,
                           ConstraintValidatorContext cxt) {
                               return student.getAm()>0;
    }
}
