package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints;

import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Registration.RegistrationAmValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Registration.RegistrationCourseIdValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student.StudentAmValid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {RegistrationCourseIdValid.class})
@Target({ElementType.FIELD, ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseIdValidation {
    String message() default "Invalid courseId,courseId should be a positive Integer";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
