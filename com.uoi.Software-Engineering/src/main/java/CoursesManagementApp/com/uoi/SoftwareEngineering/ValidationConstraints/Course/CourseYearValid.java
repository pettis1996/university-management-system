package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Course;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseYearValid implements ConstraintValidator<CourseYearValidation, Course> {
    @Override
    public void initialize(CourseYearValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Course course, ConstraintValidatorContext constraintValidatorContext) {
        return
                course.getYear()>=1 && course.getYear()<=5;

    }
}
