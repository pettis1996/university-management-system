package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Course;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = ProjectPercentageValid.class)
@Target({ElementType.FIELD, ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProjectPercentageValidation {
    String message() default "Invalid project weight, project's weight should be greater than zero or equal and be smaller than one";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}