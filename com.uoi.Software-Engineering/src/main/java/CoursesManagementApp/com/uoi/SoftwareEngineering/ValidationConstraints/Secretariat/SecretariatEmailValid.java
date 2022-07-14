package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Secretariat;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Secretariat.Secretariat;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.EmailValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SecretariatEmailValid implements ConstraintValidator<EmailValidation, Secretariat> {
@Override
public void initialize(EmailValidation secreatariatValidation) {
        }

@Override
public boolean isValid(Secretariat secretariat ,
                       ConstraintValidatorContext cxt) {
        return   secretariat.getEmail().startsWith("cs0")
        && (secretariat.isEmailValid(secretariat.getEmail(), secretariat.getUserName()) == true);
        }
}
