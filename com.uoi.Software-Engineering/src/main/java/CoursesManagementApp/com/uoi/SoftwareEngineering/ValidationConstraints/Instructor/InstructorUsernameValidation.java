package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Instructor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = InstructorUsernameValid.class)
@Target({ElementType.FIELD, ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InstructorUsernameValidation {
    String message() default "Invalid username,username should start with cse";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
