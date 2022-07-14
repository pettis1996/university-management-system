package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.SemesterValidation;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.YearValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StudentSemesterValid implements ConstraintValidator<SemesterValidation, Student> {
    @Override
    public void initialize(SemesterValidation studentValidation) {
    }

    @Override
    public boolean isValid(Student student ,
                           ConstraintValidatorContext cxt) {
        return student.getSemester()<=(student.getYear()*2) && student.getSemester()>=((student.getYear()*2)-1);
    }
}
