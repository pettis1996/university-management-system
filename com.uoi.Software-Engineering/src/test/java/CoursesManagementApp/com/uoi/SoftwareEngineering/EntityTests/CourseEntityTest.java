package CoursesManagementApp.com.uoi.SoftwareEngineering.EntityTests;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


@SpringBootTest
@ContextConfiguration(locations = "/test-context.xml")
public class CourseEntityTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();
    private Course softwareEngineering;
    private StudentRegistration firstStudent;
    private StudentRegistration secondStudent;
    private StudentRegistration thirdStudent;
    @Before
    public void setup(){
        softwareEngineering = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0.6,0.4);
        firstStudent = new StudentRegistration(1,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",6,12,2016);
        secondStudent = new StudentRegistration(3,4285,801,"cs04285@uoi.gr", 10.0,10.0,"Stavros",6,12,2016);
        thirdStudent=  new StudentRegistration(4,3136,801,"cs03136@uoi.gr", 10.0,10.0,"Paris",6,12,2016);
    }

    @Test
    public void createCourseSoftwareEngineeringEmptyNameException() {

        try {
            Course course = new Course(803,"","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0.6,0.4);
            fail();
        }
        catch(IllegalArgumentException e){
            assertEquals("Check again courseName,it must not be empty",e.getMessage());
        }
    }

   @Test
   public void createCourseHappyDay() {
       Course course = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0.6,0.4);
        assertThat(course).isNotNull();
        assertThat(course.getCourseId()).isEqualTo(803);
   }
    @Test
    public void createCourseSoftwareEngineeringNegativeId() {

        try {
            Course course = new Course(-803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0.6,0.4);
            fail();
        }
        catch(IllegalArgumentException e){
            assertEquals("Check again courseId,it must be a positive integer",e.getMessage());
        }
    }

    @Test
    public void createCourseSoftwareEngineeringZeroId() {

        try {
            Course course = new Course(0,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0.6,0.4);
            fail();
        }
        catch(IllegalArgumentException e){
            assertEquals("Check again courseId,it must be a positive integer",e.getMessage());
        }
    }

    @Test
    public void createCourseSoftwareEngineeringEmptySyllabus() {

        try {
            Course course = new Course(803,"Software Engineering","",5,4,8,"User803",803,7.0,0.6,0.4);
            fail();
        }
        catch(IllegalArgumentException e){
            assertEquals("Check again the syllabus,it's empty",e.getMessage());
        }
    }
    @Test
    public void createCourseSoftwareEngineeringNullSyllabus() {

        try {
            Course course = new Course(803,"Software Engineering",null,5,4,8,"User803",803,7.0,0.6,0.4);
            fail();
        }
        catch(IllegalArgumentException e){
            assertEquals("Check again the syllabus,it's empty",e.getMessage());
        }
    }

    @Test
    public void createCourseSoftwareEngineeringZeroYear() {

        try {
            Course course = new Course(803,"Software Engineering","Welcome to Software Engineering",5,0,8,"User803",803,7.0,0.6,0.4);
            fail();
        }
        catch(IllegalArgumentException e){
            assertEquals("Check again the year,it must  not be less than one and more than 5",e.getMessage());
        }
    }

    @Test
    public void createCourseSoftwareEngineeringMoreThan5Years() {

        try {
            Course course = new Course(803,"Software Engineering","Welcome to Software Engineering",5,6,8,"User803",803,7.0,0.6,0.4);
            fail();
        }
        catch(IllegalArgumentException e){
            assertEquals("Check again the year,it must  not be less than one and more than 5",e.getMessage());
        }
    }

    @Test
    public void createCourseSoftwareEngineeringBiggerSemesterThanAccepted() {

        try {
            Course course = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,9,"User803",803,7.0,0.6,0.4);
            fail();
        }
        catch(IllegalArgumentException e){
            assertEquals("Check again the semester,it is not valid",e.getMessage());
        }
    }
    @Test
    public void createCourseSoftwareEngineeringSmallerSemesterThanAccepted() {

        try {
            Course course = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,6,"User803",803,7.0,0.6,0.4);
            fail();
        }
        catch(IllegalArgumentException e){
            assertEquals("Check again the semester,it is not valid",e.getMessage());
        }
    }
    @Test
    public void createCourseSoftwareEngineeringEmptyInstructorLogin() {

        try {
            Course course = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"",803,7.0,0.6,0.4);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Check again the instructorLogin,it's empty", e.getMessage());
        }
    }
    @Test
    public void createCourseSoftwareEngineeringNullInstructorLogin() {

        try {
            Course course = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,null,803,7.0,0.6,0.4);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Check again the instructorLogin,it's empty", e.getMessage());
        }
    }

    @Test
    public void createCourseSoftwareEngineeringZeroExamPercentage() {

        try {
            Course course = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0,0.4);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Check again the examPercentage,it's not valid", e.getMessage());
        }
    }

    @Test
    public void createCourseSoftwareEngineeringBiggerThanMaxExamPercentage(){
        try {
            Course course = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,1.6,0.4);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Check again the examPercentage,it's not valid", e.getMessage());
        }
    }
    @Test
    public void createCourseSoftwareEngineeringNegativeProjectPercentage(){
        try {
            Course course = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0.6,-0.4);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Check again the projectPercentage,it's not valid", e.getMessage());
        }
    }
    @Test
    public void createCourseSoftwareEngineeringBiggerThanMaxProjectPercentage(){
        try {
            Course course = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0.6,1.0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Check again the projectPercentage,it's not valid", e.getMessage());
        }
    }
    @Test
    public void addStudentsRegistrationToCourseHappyDAY(){
        softwareEngineering.addStudentRegistration(firstStudent);
        softwareEngineering.addStudentRegistration(secondStudent);
        assertThat(softwareEngineering.getRegistrations().size()).isEqualTo(2);
    }



    @Test
    public void addListOfStudentRegistrationsToCourseHappyDay(){
        List<StudentRegistration> studentRegistrationList=new ArrayList<StudentRegistration>();
        studentRegistrationList.add(firstStudent);
        studentRegistrationList.add(secondStudent);
        studentRegistrationList.add(thirdStudent);
        List<StudentRegistration> check=softwareEngineering.addManyStudentRegistrations(studentRegistrationList);
        assertThat(softwareEngineering.getRegistrations().size()).isEqualTo(check.size());
    }
    @Test
    @DisplayName("This test should pass if we add wrong number of studentRegistrations")
    public void addListOfStudentRegistrationsToCourseFail(){
        List<StudentRegistration> studentRegistrationList=new ArrayList<StudentRegistration>();
        studentRegistrationList.add(firstStudent);
        studentRegistrationList.add(secondStudent);
        studentRegistrationList.add(thirdStudent);
        List<StudentRegistration> check=softwareEngineering.addManyStudentRegistrations(studentRegistrationList);
        if(check.size()!=studentRegistrationList.size()) {
            fail("The number of elements in the check list should be the same as the number of elements in studentRegistrationList");
        }
    }
    @Test
    public void checkUniqueStudentRegistrationsPerCourseHappyDay(){
        softwareEngineering = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0.6,0.4);
        softwareEngineering.addStudentRegistration(firstStudent);
        softwareEngineering.addStudentRegistration(secondStudent);
        softwareEngineering.addStudentRegistration(thirdStudent);
        assertThat(softwareEngineering.checkUniqueStudentRegistrationsPerCourse()).isEqualTo(true);
    }

    @Test
    public void removeSudentRegistrationHappyDay(){
        softwareEngineering.addStudentRegistration(firstStudent);
        softwareEngineering.addStudentRegistration(secondStudent);
        softwareEngineering.removeStudentRegistration(secondStudent);
        assertThat(softwareEngineering.getRegistrations().size()).isEqualTo(1);
        assertThat(softwareEngineering.getRegistrations().get(0).getAm()).isEqualTo(3303);
    }
}