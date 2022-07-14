package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StudentAvgGradeValid.class)
@Target({ElementType.FIELD, ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StudentAvgGradeValidation {
    String message() default "Student's AvgGrade should between 5.0 and 10.0";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
