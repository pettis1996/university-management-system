package CoursesManagementApp.com.uoi.SoftwareEngineering.EntityTests;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@SpringBootTest
@ContextConfiguration(locations = "/test-context.xml")
public class StudentRegistrationEntityTest {
    private Course softwareEngineering;

    private StudentRegistration firstStudent;

    private StudentRegistration secondStudent;

    private StudentRegistration thirdStudent;

    @Before
    public void setup() {
        softwareEngineering = new Course(803, "Software Engineering", "Welcome to Software Engineering", 5,4, 8, "User803",803,8.0, 0.6, 0.4);
        firstStudent = new StudentRegistration(1,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",6,12,2016);
        secondStudent = new StudentRegistration(2,4285,801,"cs04285@uoi.gr", 10.0,10.0,"Stavros",6,12,2016);
        thirdStudent = new StudentRegistration(3,3136,801,"cs03136@uoi.gr", 10.0,10.0,"Paris",6,12,2016);
    }
    @Test
    public void createStudentRegistrationHappyDay() {
        StudentRegistration student = new StudentRegistration(4,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",6,12,2016);
        student.setExamGrade(10.0);
        assertThat(student.getExamGrade()).isEqualTo(10.0);
    }
    @Test
    public void createStudentRegistrationdNotValidProjectGradeException() {

        try {
            StudentRegistration student = new StudentRegistration(5,3303,801,"cs03303@uoi.gr", 10.0,11.0,"Kostas",5,10,2016);

            student.setProjectGrade(10.5);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("projectGrade  should be between 0.0 and 10.0", e.getMessage());
        }
    }
    @Test
    public void createStudentRegistrationdExamGradeHappyDay() {
            StudentRegistration student = new StudentRegistration(6,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",5,10,2016);
        student.setExamGrade(1.0);
            assertThat(student.getExamGrade()).isEqualTo(1.0);
    }
    @Test
    public void createStudentRegistrationdNotValidExamGradeException() {

        try {
            StudentRegistration student = new StudentRegistration(7,3303,801,"cs03303@uoi.gr", 11.0,10.0,"Kostas",5,10,2016);

            student.setExamGrade(10.5);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("examGrade  should be between 0.0 and 10.0", e.getMessage());
        }
    }

    @Test
    public void createStudentRegistrationdNotValidCourseIdException() {

        try {
            StudentRegistration student = new StudentRegistration(8,3303,-10,"cs03303@uoi.gr", 10.0,10.0,"Kostas",5,10,2016);

            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("courseId should be greater than or equal to one", e.getMessage());
        }
    }

    @Test
    public void createStudentRegistrationdNotValidYearOfRegistrationException() {

        try {
            StudentRegistration student = new StudentRegistration(9,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",5,10,2010);

            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("yearOfRegistration should be between 2013(year of foundation) and the current year", e.getMessage());
        }
    }

    @Test
    public void createStudentRegistrationdNotValidSemesterException() {

        try {
            StudentRegistration student = new StudentRegistration(10,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",5,17,2016);

            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("semester is not valid.Check it according to your yearOfStudies", e.getMessage());
        }
    }


    @Test
    public void createStudentRegistrationdNotValidYearOfStudiesException() {

        try {
            StudentRegistration student = new StudentRegistration(11,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",0,14,2016);

            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("yearOfStudies should be greater than or equal to one", e.getMessage());
        }
    }
    @Test
    public void createStudentRegistrationdNotUoiEmailException() {

        try {
            StudentRegistration student = new StudentRegistration(12,3303,801,"cs03303@uoa.gr", 10.0,10.0,"Kostas",5,12,2016);

            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Only emails of University of Ioannina are accepted", e.getMessage());
        }
    }

    @Test
    public void createStudentRegistrationdNotValidEmailException() {

        try {
            StudentRegistration student = new StudentRegistration(13,3303,801,"cs03302@uoi.gr", 10.0,10.0,"Kostas",5,12,2016);

            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Email must contain student's am", e.getMessage());
        }
    }
    @Test
    public void createStudentRegistrationNotValidAmException() {
        try {
            StudentRegistration student = new StudentRegistration(10,0,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",5,12,2016);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("am should be greater than zero", e.getMessage());
        }
    }
    @Test
    public void createStudentRegistrationEmptyNameException() {
        try {
            StudentRegistration student = new StudentRegistration(10,3303,801,"cs03303@uoi.gr", 10.0,10.0,"",5,10,2016);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("studentName should not be empty", e.getMessage());
        }
    }

}