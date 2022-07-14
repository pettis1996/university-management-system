package CoursesManagementApp.com.uoi.SoftwareEngineering.ServiceLayerTests;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseRepository;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseServiceImpl;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.Instructor;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.InstructorRepo;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.InstructorService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.InstructorServiceImpl;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Secretariat.Secretariat;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Secretariat.SecretariatRepo;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Secretariat.SecretariatServiceImpl;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.StudentRepository;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/test-context.xml")
@RunWith(MockitoJUnitRunner.class)
public class SecretServiceTest {
    @Mock
    private StudentRegistrationService registrationService;
    @Mock
    private StudentRepository studentsRepo;

    @Mock
    private SecretariatRepo secretRepo;

    @InjectMocks
    private CourseServiceImpl courseService;
    @Mock
    private InstructorRepo instructorRepo;
    @InjectMocks
    private InstructorServiceImpl instructorService;
    @Mock
    private Course softwareEngineering;
    @InjectMocks
    private SecretariatServiceImpl secretService;

    @Mock
    private CourseRepository courseRepo;

    @Test
    public void testsave(){
        Course softwareEngineering = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0.6,0.4);
        courseService.save(softwareEngineering);
        verify(courseRepo).save(softwareEngineering);
    }

    @Test
    public void testDeleteCourse(){
        Course softwareEngineering = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0.6,0.4);
        courseService.save(softwareEngineering);
        courseService.delete(803);
        verify(courseRepo).deleteByCourseId(803);
        Assert.assertNull(courseService.findCourseById(803));
    }

    @Test
    public void testUpdateCourse(){
        Course softwareEngineering = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0.6,0.4);
        given(courseRepo.save(softwareEngineering)).willReturn(softwareEngineering);

        softwareEngineering.setYear(5);
        courseService.update(softwareEngineering);
        verify(courseRepo).save(softwareEngineering);
    }

    @Test
    public void testAddInstructor() {
        Instructor Katakouzinos = new Instructor(1, "Konstantinos Katakouzinos", "cse67890", "Cse@67890", "cse67890@uoi.gr","Proffessor");
        instructorService.addInstructor(Katakouzinos);
        verify(instructorRepo).save(Katakouzinos);
    }

    @Test
    public void findInstructorByIdHappyDay() {
        Instructor Katakouzinos = new Instructor(1, "Konstantinos Katakouzinos", "cse67890", "Cse@67890", "cse67890@uoi.gr","Proffessor");

        instructorService.addInstructor(Katakouzinos);
        Mockito.when(instructorRepo.findById(1)).thenReturn(Katakouzinos);

        Instructor result = instructorService.findInstructorById(1);
        Assert.assertEquals(result, Katakouzinos);
    }

    @Test
    public void testDeleteInstructor() {
        Instructor Katakouzinos = new Instructor(1, "Konstantinos Katakouzinos", "cse67890", "Cse@67890", "cse67890@uoi.gr","Proffessor");
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
    public void findSecretByUserNameHappyDay() {
        Secretariat Jenny=new Secretariat("Jenny","cssJenny","Css@Jenny1","cssJenny@uoi.gr");

        secretService.addSecretariat(Jenny);
        Mockito.when(secretRepo.findByUserName("cssJenny")).thenReturn(Jenny);

        Secretariat result = secretService.findSecretByUserName("cssJenny");
        Assert.assertEquals(result, Jenny);
    }
    @Test
    public void testDeleteSecret() {
        Secretariat Jenny=new Secretariat("Jenny","cssJenny","Css@Jenny1","cssJenny@uoi.gr");
        secretService.addSecretariat(Jenny);
        verify(secretRepo).save(Jenny);
        secretService.deleteSecret("cssJenny");
        verify(secretRepo).deleteByUserName("cssJenny");
        Assert.assertNull(secretService.findSecretByUserName("cssJenny"));
    }

    @Test
    public void testUpdateSecret() {
        Secretariat Jenny=new Secretariat("Jenny","cssJenny","Css@Jenny1","cssJenny@uoi.gr");

        secretService.updateSecretariat(Jenny);
        verify(secretRepo).save(Jenny);
    }

    @Test
    public void testgetAllSecrets() {
        Secretariat Jenny=new Secretariat("Jenny","cssJenny","Css@Jenny1","cssJenny@uoi.gr");
        Secretariat Fiona=new Secretariat("Fiona","cssFiona","Css@Fiona2","cssFiona@uoi.gr");
        List<Secretariat> secrets=new ArrayList<Secretariat>();
        secrets.add(Jenny);
        secrets.add(Fiona);
        secretService.addSecretariat(Fiona);
        secretService.addSecretariat(Jenny);
        Mockito.when(secretRepo.findAll()).thenReturn(secrets);
        List<Secretariat> result = secretService.getAllSecretariats();
        Assert.assertEquals(result, secrets);
    }

}
