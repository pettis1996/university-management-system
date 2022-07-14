package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints;

import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Instructor.InstructorEmailValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Registration.RegistrationAmValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Registration.RegistrationEmailValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Secretariat.SecretariatEmailValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student.StudentAmValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student.StudentEmailValid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {StudentAmValid.class, RegistrationAmValid.class})
@Target({ElementType.FIELD, ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AmValidation {
    String message() default "Invalid Am,Am should be a positive Integer";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
