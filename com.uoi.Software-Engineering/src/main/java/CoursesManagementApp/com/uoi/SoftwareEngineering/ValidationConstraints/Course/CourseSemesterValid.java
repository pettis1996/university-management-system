package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Course;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.SemesterValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseSemesterValid implements ConstraintValidator<SemesterValidation, Course> {
    @Override
    public void initialize(SemesterValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Course course, ConstraintValidatorContext constraintValidatorContext) {
        return
                course.getYear()>=1 && course.getYear()<=5
                && course.getSemester() < (2 * course.getYear() - 1) && course.getSemester() > 2 * course.getYear();

    }
}
