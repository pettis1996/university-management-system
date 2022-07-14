package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Registration;

import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.AmValidation;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.CourseIdValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegistrationCourseIdValid implements ConstraintValidator<CourseIdValidation, StudentRegistration> {
    @Override
    public void initialize(CourseIdValidation registrationValidation) {
    }

    @Override
    public boolean isValid(StudentRegistration registration,
                           ConstraintValidatorContext cxt) {
        return registration.getCourseId()>0;
    }
}
