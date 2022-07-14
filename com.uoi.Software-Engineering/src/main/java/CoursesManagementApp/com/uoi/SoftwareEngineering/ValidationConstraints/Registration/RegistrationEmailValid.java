package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Registration;

import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.EmailValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegistrationEmailValid implements ConstraintValidator<EmailValidation, StudentRegistration> {
    @Override
    public void initialize(EmailValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(StudentRegistration registration, ConstraintValidatorContext constraintValidatorContext) {
        return
                registration.isEmailValid(registration.getEmail(), registration.getAm());
    }
}