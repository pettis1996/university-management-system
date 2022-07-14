package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StudentUsernameValid.class)
@Target({ElementType.FIELD, ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StudentUsernameValidation {
    String message() default "Invalid username,username should be in the format cs0 and Am";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
