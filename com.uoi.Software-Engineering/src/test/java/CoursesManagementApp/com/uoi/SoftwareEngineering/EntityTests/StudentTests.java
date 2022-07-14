package CoursesManagementApp.com.uoi.SoftwareEngineering.EntityTests;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@SpringBootTest
@ContextConfiguration(locations = "/test-context.xml")
public class StudentTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();



    @Test
    public void createStudentHappyDay() {
        Student student = new Student("Stavros", "cs04285", "Cs0@4285", "cs04285@uoi.gr", 4285, 5, 10, 5.5);
        assertThat(student).isNotNull();
        assertThat(student.getName()).isEqualTo("Stavros");
        assertThat(student.getUserName()).isEqualTo("cs04285");
        assertThat(student.getPassWord()).isEqualTo("Cs0@4285");
        assertThat(student.getEmail()).isEqualTo("cs04285@uoi.gr");
        assertThat(student.getAm()).isEqualTo(4285);
        assertThat(student.getYear()).isEqualTo(5);
        assertThat(student.getSemester()).isEqualTo(10);
        assertThat(student.getAvgGrade()).isEqualTo(5.5);
    }
    @Test
    public void createStudentAvgGradeBiggerThan10() {
        try {
            Student George = new Student("George", "cs04555", "Cs0@45555", "cs04555@uoi.gr", 45555, 4, 8, 11.0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("AvgGrade should be between 5 and 10", e.getMessage());
        }
    }
    @Test
    public void createStudentAvgGradeLessThan5() {
        try {
            Student George = new Student("George", "cs04555", "Cs0@45555", "cs04555@uoi.gr", 45555, 4, 8, 4.5);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("AvgGrade should be between 5 and 10", e.getMessage());
        }
    }

    @Test
    public void createStudentYearLessThanOne(){
        try {
            Student George = new Student("George", "cs04555", "Cs0@45555", "cs04555@uoi.gr", 45555, 0, 8, 4.5);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("yearOfStudies should be greater than or equal to one", e.getMessage());
        }
    }
    @Test
    public void createStudentSemesterLessThanAccepted(){
        try {
            Student George = new Student("George", "cs04555", "Cs0@45555", "cs04555@uoi.gr", 45555, 4, 6, 5.0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Semester is not valid.Check it according to your yearOfStudies", e.getMessage());
        }
    }

    @Test
    public void createStudentSemesterBiggerThanAccepted(){
        try {
            Student George = new Student("George", "cs04555", "Cs0@45555", "cs04555@uoi.gr", 45555, 4, 9, 5.0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Semester is not valid.Check it according to your yearOfStudies", e.getMessage());
        }
    }
    @Test
    public void addRegistrationToStudentHappyDay(){
        StudentRegistration registration=new StudentRegistration(15,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",6,12,2016);
        Student Kostas=new Student("Kostas", "cs03303", "Cs0@3303",  "cs03303@uoi.gr",3303,6,12,7.0);
        Kostas.addRegistrationToStudent(registration);
        assertThat(Kostas.getMyRegistrations().size()).isEqualTo(1);
    }
    @Test
    public void removeRegistrationFromStudentHappyDay(){
        StudentRegistration registration=new StudentRegistration(17,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",6,12,2016);
        Student Kostas=new Student("Kostas", "cs03303", "Cs0@3303",  "cs03303@uoi.gr",3303,6,12,7.0);
        Kostas.addRegistrationToStudent(registration);
        StudentRegistration registration2=new StudentRegistration(18,3303,810,"cs03303@uoi.gr", 8.0,9.0,"Kostas",6,12,2016);
        Kostas.addRegistrationToStudent(registration2);
        Kostas.removeRegistrationFromStudent(registration);
        assertThat(Kostas.getMyRegistrations().size()).isEqualTo(1);
    }
    @Test
    public void addManyRegistrationsToStudentHappyDay(){
        Student Kostas=new Student("Kostas", "cs03303", "Cs0@3303",  "cs03303@uoi.gr",3303,6,12,7.0);
        StudentRegistration registration=new StudentRegistration(20,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",6,12,2016);
        StudentRegistration registration2=new StudentRegistration(22,3303,821,"cs03303@uoi.gr", 10.0,10.0,"Kostas",6,12,2016);
        StudentRegistration registration3=new StudentRegistration(24,3303,845,"cs03303@uoi.gr", 9.0,10.0,"Kostas",6,12,2016);
        List<StudentRegistration> registrations=new ArrayList<StudentRegistration>();
        registrations.add(registration);
        registrations.add(registration2);
        registrations.add(registration3);
        Kostas.addManyRegistrationsToStudent(registrations);
        assertThat(Kostas.getMyRegistrations().size()).isEqualTo(3);
    }
    public void removeManyRegistrationsFromStudentHappyDay(){
        Student Kostas=new Student("Kostas", "cs03303", "Cs0@3303",  "cs03303@uoi.gr",3303,6,12,7.0);
        StudentRegistration registration=new StudentRegistration(27,3303,821,"cs03303@uoi.gr", 10.0,10.0,"Kostas",6,12,2016);
        StudentRegistration registration2=new StudentRegistration(28,3303,851,"cs03303@uoi.gr", 8.0,6.0,"Kostas",6,12,2016);
        StudentRegistration registration3=new StudentRegistration(30,3303,891,"cs03303@uoi.gr", 9.0,5.0,"Kostas",6,12,2016);
        List<StudentRegistration> registrations=new ArrayList<StudentRegistration>();
        registrations.add(registration);
        registrations.add(registration2);
        registrations.add(registration3);
        Kostas.addManyRegistrationsToStudent(registrations);
        assertThat(Kostas.getMyRegistrations().size()).isEqualTo(3);
        Kostas.removeManyRegistrationsFromStudent(registrations);
        assertThat(Kostas.getMyRegistrations().size()).isEqualTo(0);
    }
    @Test
    public void addStudentRegistrationToStudentWrongAm(){
        StudentRegistration registration=new StudentRegistration(32,3136,831,"cs03136@uoi.gr", 10.0,10.0,"Kostas",6,12,2016);
        Student Kostas=new Student("Kostas", "cs03303", "Cs0@3303",  "cs03303@uoi.gr",3303,6,12,7.0);
        try {
            Kostas.addRegistrationToStudent(registration);
            fail();
        }catch(IllegalArgumentException e){
            assertEquals("Registration must have same AM with Student",e.getMessage());
        }
    }

    @Test
    public void addManyStudentRegistrationsToStudentWrongAm(){
        StudentRegistration registration=new StudentRegistration(13,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",6,12,2016);
        Student Kostas=new Student("Kostas", "cs03303", "Cs0@3303",  "cs03303@uoi.gr",3303,6,12,7.0);
        StudentRegistration registration2=new StudentRegistration(18,4285,851,"cs04285@uoi.gr", 10.0,10.0,"Kostas",6,12,2016);
        StudentRegistration registration3=new StudentRegistration(20,3303,886,"cs03303@uoi.gr", 9.5,9.0,"Kostas",6,12,2016);
        List<StudentRegistration> registrations=new ArrayList<StudentRegistration>();
        registrations.add(registration);
        registrations.add(registration2);
        registrations.add(registration3);
        try {
            Kostas.addManyRegistrationsToStudent(registrations);
            fail();
        }catch(IllegalArgumentException e){
            assertEquals("Registrations must have same AM with Student",e.getMessage());
        }
    }
    @Test
    public void removeStudentRegistrationFromStudentWrongAm(){
        StudentRegistration registrationWrong=new StudentRegistration(25,3302,801,"cs03302@uoi.gr", 10.0,10.0,"Kostas",6,12,2016);
        StudentRegistration registration=new StudentRegistration(29,3303,505,"cs03303@uoi.gr", 10.0,10.0,"Kostas",6,12,2016);
        Student Kostas=new Student("Kostas", "cs03303", "Cs0@3303",  "cs03303@uoi.gr",3303,6,12,7.0);
        Kostas.addRegistrationToStudent(registration);
        try{
            Kostas.removeRegistrationFromStudent(registrationWrong);
            fail();
        }catch(IllegalArgumentException e){
            assertEquals("Registration must have same AM with Student.You can't remove undeclared registrations",e.getMessage());
        }
    }
    @Test
    public void removeManyRegistrationsFromStudentWrongAm() {
        Student Kostas = new Student("Kostas", "cs03303", "Cs0@3303", "cs03303@uoi.gr", 3303, 6, 12, 7.0);
        StudentRegistration registration = new StudentRegistration(56,3302,871,"cs03302@uoi.gr", 9.0,8.0,"Kostas",6,12,2016);
        StudentRegistration registration2 = new StudentRegistration(67,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",6,12,2016);
        StudentRegistration registration3 = new StudentRegistration(89,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",6,12,2016);
        List<StudentRegistration> registrations = new ArrayList<StudentRegistration>();
        registrations.add(registration3);
        registrations.add(registration2);
        Kostas.addManyRegistrationsToStudent(registrations);
        registrations.add(registration);
        try {
            Kostas.removeManyRegistrationsFromStudent(registrations);
        }catch(IllegalArgumentException e){
            assertEquals("Registrations must have same AM with Student.You can't remove undeclared registrations",e.getMessage());
        }
    }

    @Test
    public void removeManyRegistrationsFromStudentNotContained() {
        Student Kostas = new Student("Kostas", "cs03303", "Cs0@3303", "cs03303@uoi.gr", 3303, 6, 12, 7.0);
        StudentRegistration registration = new StudentRegistration(88,3303,871,"cs03303@uoi.gr", 10.0,10.0,"Kostas",6,12,2016);
        StudentRegistration registration2 = new StudentRegistration(34,3303,861,"cs03303@uoi.gr", 9.0,9.5,"Kostas",5,10,2016);
        StudentRegistration registration3 = new StudentRegistration(25,3303,841,"cs03303@uoi.gr", 10.0,10.0,"Kostas",6,12,2016);
        List<StudentRegistration> registrations = new ArrayList<StudentRegistration>();
        registrations.add(registration3);
        registrations.add(registration2);
        Kostas.addManyRegistrationsToStudent(registrations);
        try {
            Kostas.removeManyRegistrationsFromStudent(registrations);
        }catch(IllegalArgumentException e){
            assertEquals("Registrations must have same AM with Student.You can't remove undeclared registrations",e.getMessage());
        }
    }
    @Test
    public void removeStudentRegistrationFromStudentNotContained(){
        StudentRegistration registrationWrong=new StudentRegistration(80,3302,801,"cs03302@uoi.gr", 9.0,8.5,"Kostas",6,12,2016);
        StudentRegistration registration=new StudentRegistration(32,3303,871,"cs03303@uoi.gr", 9.5,7.9,"Kostas",6,12,2016);
        Student Kostas=new Student("Kostas", "cs03303", "Cs0@3303",  "cs03303@uoi.gr",3303,6,12,7.0);
        try{
            Kostas.removeRegistrationFromStudent(registrationWrong);
            fail();
        }catch(IllegalArgumentException e){
            assertEquals("Registration must have same AM with Student.You can't remove undeclared registrations",e.getMessage());
        }
    }
}
