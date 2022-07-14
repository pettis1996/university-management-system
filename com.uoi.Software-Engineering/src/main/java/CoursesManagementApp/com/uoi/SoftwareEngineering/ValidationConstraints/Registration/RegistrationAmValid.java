package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Registration;

import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.AmValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegistrationAmValid implements ConstraintValidator<AmValidation, StudentRegistration> {
    @Override
    public void initialize(AmValidation registrationValidation) {
    }

    @Override
    public boolean isValid(StudentRegistration registration,
                           ConstraintValidatorContext cxt) {
        return registration.getAm()>0;
    }
}
