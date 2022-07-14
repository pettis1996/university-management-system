package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Secretariat;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Secretariat.Secretariat;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SecretariatUsernameValid implements ConstraintValidator<SecretariatUsernameValidation, Secretariat> {
    @Override
    public void initialize(SecretariatUsernameValidation secretariatValidation) {
    }

    @Override
    public boolean isValid(Secretariat student ,
                           ConstraintValidatorContext cxt) {
        return student.getUserName().startsWith("css");
    }
}
