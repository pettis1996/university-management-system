package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints;

import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Instructor.InstructorEmailValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Instructor.InstructorPasswordValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Registration.RegistrationEmailValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Secretariat.SecretariatEmailValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Secretariat.SecretariatPasswordValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student.StudentEmailValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student.StudentPasswordValid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {StudentPasswordValid.class, InstructorPasswordValid.class, SecretariatPasswordValid.class,})
@Target({ElementType.FIELD, ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidation {
    String message() default "Password has minimum length 8 and must contain at least one Uppercase,one lowercase,one number and one special character";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}