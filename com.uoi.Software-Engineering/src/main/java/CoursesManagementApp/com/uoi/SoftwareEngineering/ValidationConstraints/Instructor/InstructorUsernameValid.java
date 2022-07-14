package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Instructor;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.Instructor;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InstructorUsernameValid implements ConstraintValidator<InstructorUsernameValidation,Instructor> {
    @Override
    public void initialize(InstructorUsernameValidation InstructorValidation) {
    }

    @Override
    public boolean isValid(Instructor instructor ,
                           ConstraintValidatorContext cxt) {
        return instructor.getUserName().startsWith("cse");
    }
}
