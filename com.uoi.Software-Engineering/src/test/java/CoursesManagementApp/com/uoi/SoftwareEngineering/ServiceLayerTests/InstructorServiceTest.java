package CoursesManagementApp.com.uoi.SoftwareEngineering.ServiceLayerTests;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseRepository;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseServiceImpl;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.Instructor;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.InstructorRepo;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.InstructorService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.InstructorServiceImpl;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.PersonalStudentServiceImpl;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationRepository;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/test-context.xml")
@RunWith(MockitoJUnitRunner.class)
public class InstructorServiceTest {

    @Mock
    private InstructorRepo instructorRepo;

    @InjectMocks
    private InstructorServiceImpl instructorService;

    @Mock
    private CourseRepository courseRepo;
    @Mock
    private CourseServiceImpl courseService;
    @Mock
    private PersonalStudentServiceImpl studentService;
    @Mock
    private StudentRegistrationRepository regRepo;

    @InjectMocks
    private StudentRegistrationServiceImpl regService;

    @Test
    public void testAddInstructor() {
        Instructor Katakouzinos = new Instructor(1, "Konstantinos Katakouzinos", "cse67890", "Cse@67890", "cse67890@uoi.gr","Kosmitoras");
        instructorService.addInstructor(Katakouzinos);
        verify(instructorRepo).save(Katakouzinos);
    }

    @Test
    public void testgetAllInstructors() {
        Instructor Katakouzinos = new Instructor(1, "Konstantinos Katakouzinos", "cse67890", "Cse@67890", "cse67890@uoi.gr","Proffessor");
        Instructor Fatseas = new Instructor(2, "Fatseas", "cse678", "Cse@67890", "cse678@uoi.gr","Kosmitoras");

        List<Instructor> instructors = new ArrayList<Instructor>();
        instructors.add(Katakouzinos);
        instructors.add(Fatseas);
        instructorService.addInstructor(Katakouzinos);
        instructorService.addInstructor(Fatseas);
        Mockito.when(instructorRepo.findAll()).thenReturn(instructors);
        List<Instructor> result = instructorService.getAllInstructors();
        Assert.assertEquals(result, instructors);
    }

    @Test
    public void findInstructorByIdHappyDay() {
        Instructor Katakouzinos = new Instructor(1, "Konstantinos Katakouzinos", "cse67890", "Cse@67890", "cse67890@uoi.gr","Kosmitoras");

        instructorService.addInstructor(Katakouzinos);
        Mockito.when(instructorRepo.findById(1)).thenReturn(Katakouzinos);

        Instructor result = instructorService.findInstructorById(1);
        Assert.assertEquals(result, Katakouzinos);
    }

    @Test
    public void findInstructorByUserNameHappyDay() {
        Instructor Katakouzinos = new Instructor(1, "Konstantinos Katakouzinos", "cse67890", "Cse@67890", "cse67890@uoi.gr","Proffessor");

        instructorService.addInstructor(Katakouzinos);
        Mockito.when(instructorRepo.findByUserName("cse67890")).thenReturn(Katakouzinos);

        Instructor result = instructorService.findByInstructorUserName("cse67890");
        Assert.assertEquals(result, Katakouzinos);
    }

    @Test
    public void testDeleteInstructor() {
        Instructor Katakouzinos = new Instructor(1, "Konstantinos Katakouzinos", "cse67890", "Cse@67890", "cse67890@uoi.gr","Kosmitoras");
        instructorService.addInstructor(Katakouzinos);
        verify(instructorRepo).save(Katakouzinos);
        instructorService.deleteInstructor(1);
        verify(instructorRepo).deleteById(1);
        Assert.assertNull(instructorService.findInstructorById(1));
    }

    @Test
    public void testUpdate() {
        Instructor Katakouzinos = new Instructor(1, "Konstantinos Katakouzinos", "cse67890", "Cse@67890", "cse67890@uoi.gr","Proffessor");
        Katakouzinos.setName("Vlaxara");
        instructorService.updateInstructor(Katakouzinos);
        verify(instructorRepo).save(Katakouzinos);
    }


    @Test
    public void testShowMyCourses() {
        Instructor Katakouzinos = new Instructor(1, "Konstantinos Katakouzinos", "cse67890", "Cse@67890", "cse67890@uoi.gr","Proffessor");
        Course course = new Course(803, "Software Engineering", "Welcome to Software Engineering", 5,4, 8, "cse67890", 803, 7.0, 0.6, 0.4);
        Course course2 = new Course(805, "Software Development", "Welcome to Software Development",5, 4, 8, "cse67890", 803, 7.0, 0.6, 0.4);
        List<Course> courses = new ArrayList<Course>();
        courses.add(course);
        courses.add(course2);
        instructorService.addInstructor(Katakouzinos);
        verify(instructorRepo).save(Katakouzinos);
        Mockito.when(courseService.findCourseByInstructorLogin("cse67890")).thenReturn(courses);
        List<Course> result = instructorService.showMyCourses("cse67890");
        Assert.assertEquals(result, courses);
    }

    @Test
    public void testShowRegistrationsInMyCourse() {
        Instructor Katakouzinos = new Instructor(1, "Konstantinos Katakouzinos", "cse67890", "Cse@67890", "cse67890@uoi.gr","Kosmitoras");
        Course course = new Course(803, "Software Engineering", "Welcome to Software Engineering", 5,4, 8, "cse67890", 803, 7.0, 0.6, 0.4);
        List<StudentRegistration> regs = new ArrayList<StudentRegistration>();
        StudentRegistration reg = new StudentRegistration(6, 3303, 803, "cs03303@uoi.gr", 10.0, 10.0, "Kostas", 6, 12, 2016);
        StudentRegistration reg2 = new StudentRegistration(7, 3302, 803, "cs03302@uoi.gr", 10.0, 10.0, "Kostas", 6, 12, 2016);
        regs.add(reg);
        regs.add(reg2);
        Mockito.when(courseService.findCourseById(803)).thenReturn(course);
        Mockito.when(courseService.findRegistrationsInCourse(course.getCourseId())).thenReturn(regs);
        List<StudentRegistration> result = instructorService.showRegistrationsInMyCourses("cse67890", 803);
        Assert.assertEquals(result, regs);
    }

}