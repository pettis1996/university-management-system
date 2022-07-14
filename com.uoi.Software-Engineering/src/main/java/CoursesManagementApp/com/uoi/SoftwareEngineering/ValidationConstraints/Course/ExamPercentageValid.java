package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Course;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExamPercentageValid implements ConstraintValidator<ExamPercentageValidation, Course> {
    @Override
    public void initialize(ExamPercentageValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Course course, ConstraintValidatorContext constraintValidatorContext) {
        return
                course.getExamPercentage()>0 && course.getExamPercentage()<=1.0;

    }
}

