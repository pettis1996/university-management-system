package CoursesManagementApp.com.uoi.SoftwareEngineering.EntityTests;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.Instructor;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@SpringBootTest
@ContextConfiguration(locations = "/test-context.xml")
public class InstructorTests {


    @Test
    public void createInstructorHappyDay(){
        Instructor Katakouzinos = new Instructor(1,"Konstantinos Katakouzinos","cse67890","Cse@67890","cse67890@uoi.gr","Kosmitoras");
        assertThat(Katakouzinos.getName()).isEqualTo("Konstantinos Katakouzinos");
    }

    @Test
    public void createInstructorWrongusername(){
        try {
            Instructor Katakouzinos = new Instructor(1,"Konstantinos Katakouzinos","cse6789","Cse@67890","cse67890@uoi.gr","Proffessor");
            fail();
        }catch(IllegalArgumentException e){
            assertEquals("Instructor's email and username should start with cse",e.getMessage());
        }
    }

    @Test
    public void addCourseToInstructorWrongUsername() {
        Instructor Katakouzinos = new Instructor(1,"Konstantinos Katakouzinos","cse67890","Cse@67890","cse67890@uoi.gr","Proffessor");
        Course course = new Course(12, "Byzantynologia", "Welcome to Byzantinologia",5, 5, 9, "Kitchen",1,5.0, 1.0, 0.0);
        try {
            Katakouzinos.addCourseToInstructor(course);
        } catch (IllegalArgumentException e) {
            assertEquals("The course's instructorLogin should be equal with instructor's username", e.getMessage());
        }
    }
    @Test
    public void addCourseToInstructorHappyDay() {
        Instructor Katakouzinos = new Instructor(1,"Konstantinos Katakouzinos", "cse67890", "Cse@67890", "cse67890@uoi.gr","Proffessor");
        Course course = new Course(12, "Byzantynologia", "Welcome to Byzantinologia", 5,5, 9, "cse67890",1,6.0, 1.0, 0.0);
        Katakouzinos.addCourseToInstructor(course);
        assertThat(Katakouzinos.getMyCourses().contains(course)).isEqualTo(true);
    }
    @Test
    public void removeCourseFromInstructorHappyDay(){
        Instructor Katakouzinos = new Instructor(1,"Konstantinos Katakouzinos", "cse67890", "Cse@67890", "cse67890@uoi.gr","Kosmitoras");
        Course course = new Course(12, "Byzantynologia", "Welcome to Byzantinologia",5, 5, 9, "cse67890",1,5.0, 1.0, 0.0);
        Katakouzinos.addCourseToInstructor(course);
        Katakouzinos.removeCourseFromInstructor(course);
        assertThat(Katakouzinos.getMyCourses().size()).isEqualTo(0);
    }

    @Test
    public void removeCourseFromInstructorNotContained(){
        Instructor Katakouzinos = new Instructor(1,"Konstantinos Katakouzinos", "cse67890", "Cse@67890", "cse67890@uoi.gr","Prytanis");
        Course course = new Course(12, "Byzantynologia", "Welcome to Byzantinologia",5, 5, 9, "Kitchen",5,6.0, 1.0, 0.0);
        try{
            Katakouzinos.removeCourseFromInstructor(course);
            fail();
        }catch(IllegalArgumentException e){
            assertEquals("Instructor doesn't teach this course you are going to remove",e.getMessage());
        }
    }
    @Test
    public void createInstructorEmptyΝame(){
        try {
            Instructor Katakouzinos = new Instructor(1,"","cse6789","Cse@67890","cse67890@uoi.gr","Kosmitoras");
            fail();
        }catch(IllegalArgumentException e){
            assertEquals("Check again the name it's empty or null",e.getMessage());
        }
    }
    @Test
    public void createInstructorNullΝame(){
        try {
            Instructor Katakouzinos = new Instructor(1,null,"cse6789","Cse@67890","cse67890@uoi.gr","Prytanis");
            fail();
        }catch(IllegalArgumentException e){
            assertEquals("Check again the name it's empty or null",e.getMessage());
        }
    }
    @Test
    public void createInstructorWrongPassword(){
        try{
        Instructor Katakouzinos = new Instructor(1,"Konstantinos Katakouzinos","cse67890","Cse67890","cse67890@uoi.gr","Kosmitoras");
        fail();
    }catch(IllegalArgumentException e){
        assertEquals("Password has minimum length 8 and must contain at least one Uppercase,one lowercase,one number and one special character",e.getMessage());
    }
    }
    @Test
    public void createInstructorNullPassword(){
        try{
            Instructor Katakouzinos = new Instructor(1,"Konstantinos Katakouzinos","cse67890",null,"cse67890@uoi.gr","Kosmitoras");
            fail();
        }catch(IllegalArgumentException e){
            assertEquals("Password has minimum length 8 and must contain at least one Uppercase,one lowercase,one number and one special character",e.getMessage());
        }
    }
    @Test
    public void createInstructorEmptyPassword(){
        try{
            Instructor Katakouzinos = new Instructor(1,"Konstantinos Katakouzinos","cse67890","","cse67890@uoi.gr","Kosmitoras");
            fail();
        }catch(IllegalArgumentException e){
            assertEquals("Password has minimum length 8 and must contain at least one Uppercase,one lowercase,one number and one special character",e.getMessage());
        }
    }
    @Test
    public void createInstructorEmptyEmail(){
        try{
            Instructor Katakouzinos = new Instructor(1,"Konstantinos Katakouzinos","cse67890","Cse@67890","","Kosmitoras");
            fail();
        }catch(IllegalArgumentException e){
            assertEquals("Instructor's email and username should start with cse",e.getMessage());
        }
    }

    @Test
    public void createInstructorNullEmail(){
        try{
            Instructor Katakouzinos = new Instructor(1,"Konstantinos Katakouzinos","cse67890","Cse@67890",null,"Proffessor");
            fail();
        }catch(IllegalArgumentException e){
            assertEquals("Instructor's email and username should start with cse",e.getMessage());
        }
    }
}
