package CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Registration;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.SemesterValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegistrationSemesterValid implements ConstraintValidator<SemesterValidation, StudentRegistration> {
    @Override
    public void initialize(SemesterValidation registrationValidation) {
    }

    @Override
    public boolean isValid(StudentRegistration registration,
                           ConstraintValidatorContext cxt) {
        return registration.getSemester()<=(registration.getYearOfStudies()*2) && registration.getSemester()>=((registration.getYearOfStudies()*2)-1);
    }
}
