package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Secretariat.Secretariat;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Instructor.InstructorEmailValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Registration.RegistrationEmailValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Secretariat.SecretariatEmailValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student.StudentEmailValid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {StudentEmailValid.class, InstructorEmailValid.class, SecretariatEmailValid.class, RegistrationEmailValid.class})
@Target({ElementType.FIELD, ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValidation {
        String message() default "Invalid email address,email should have the format username@uoi.gr";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
}

