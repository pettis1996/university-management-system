package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Course;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = ExamPercentageValid.class)
@Target({ElementType.FIELD, ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExamPercentageValidation {
    String message() default "Invalid exam weight, exam's weight should be greater than zero and be smaller than one or equal ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}