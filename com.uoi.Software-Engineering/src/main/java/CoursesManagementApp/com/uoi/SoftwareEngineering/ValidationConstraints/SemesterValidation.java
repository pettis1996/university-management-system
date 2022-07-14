package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints;

import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Course.CourseSemesterValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Instructor.InstructorPasswordValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Registration.RegistrationSemesterValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Secretariat.SecretariatPasswordValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student.StudentPasswordValid;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student.StudentSemesterValid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {StudentSemesterValid.class, RegistrationSemesterValid.class, CourseSemesterValid.class})
@Target({ElementType.FIELD, ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SemesterValidation{
    String message() default "Semester is not valid.Check it according to your Year";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

