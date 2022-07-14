package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Registration;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.YearValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegistrationYearValid  implements ConstraintValidator<YearValidation, StudentRegistration> {
    @Override
    public void initialize(YearValidation registrationValidation) {
    }

    @Override
    public boolean isValid(StudentRegistration registration,
                           ConstraintValidatorContext cxt) {
        return registration.getYearOfStudies()>0;
    }
}
