package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Secretariat;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Secretariat.Secretariat;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.PasswordValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SecretariatPasswordValid implements ConstraintValidator<PasswordValidation, Secretariat> {
    @Override
    public void initialize(PasswordValidation secretariatValidation) {
    }

    @Override
    public boolean isValid(Secretariat secretariat ,
                           ConstraintValidatorContext cxt) {
        return secretariat.getPassWord().length()>=8 && secretariat.checkPassword(secretariat.getPassWord())==true;
    }
}
