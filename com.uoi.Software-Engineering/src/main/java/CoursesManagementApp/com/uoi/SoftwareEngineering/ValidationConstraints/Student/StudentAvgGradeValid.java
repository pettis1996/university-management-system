package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.SemesterValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StudentAvgGradeValid implements ConstraintValidator<StudentAvgGradeValidation, Student> {
    @Override
    public void initialize(StudentAvgGradeValidation studentValidation) {
    }

    @Override
    public boolean isValid(Student student ,
                           ConstraintValidatorContext cxt) {
        return student.getAvgGrade()<=10 && student.getAvgGrade()>=5;
    }
}
