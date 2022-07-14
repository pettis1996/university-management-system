package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Course;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CourseYearValid.class)
@Target({ElementType.FIELD, ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseYearValidation  {
    String message() default "Invalid Course Year,it should be between 1 and 5";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
