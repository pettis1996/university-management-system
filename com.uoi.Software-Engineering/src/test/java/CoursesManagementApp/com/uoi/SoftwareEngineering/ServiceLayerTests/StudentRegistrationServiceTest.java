package CoursesManagementApp.com.uoi.SoftwareEngineering.ServiceLayerTests;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseRepository;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseServiceImpl;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.PersonalStudentService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.PersonalStudentServiceImpl;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.StudentRepository;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationRepository;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/test-context.xml")
@RunWith(MockitoJUnitRunner.class)
public class StudentRegistrationServiceTest {

    @Mock
    private StudentRegistrationRepository regRepo;

    @Mock
    private CourseRepository courseRepo;

    @InjectMocks
    private CourseServiceImpl courseService;
    @Mock
    private StudentRepository studentRepo;
    @InjectMocks
    private PersonalStudentServiceImpl studentService;

    @InjectMocks
    private StudentRegistrationServiceImpl regService;

    @Test
    public void testFindRegistrationsByRegId(){
        StudentRegistration reg = new StudentRegistration(6,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",5,10,2016);

        regService.save(reg);
        Mockito.when(regRepo.findByRegId(6)).thenReturn(reg);

        StudentRegistration result=regService.findByRegId(6);
        Assert.assertEquals(result,reg);
    }
    @Test
    public void testFindRegistrationsByRegAm(){
        StudentRegistration reg = new StudentRegistration(6,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",5,10,2016);
        List<StudentRegistration> regs=new ArrayList<StudentRegistration>();
        regs.add(reg);
        regService.save(reg);
        Mockito.when(regRepo.findByRegAm(3303)).thenReturn(regs);

        List<StudentRegistration> result=regService.getRegistrationsByAM(3303);
        Assert.assertEquals(result,regs);
    }

    @Test
    public void testgetAllRegistrations(){
        StudentRegistration reg = new StudentRegistration(6,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",5,10,2016);
        StudentRegistration reg2 = new StudentRegistration(8,3890,801,"cs03890@uoi.gr", 10.0,10.0,"Kostas",5,10,2016);

        List<StudentRegistration> regs=new ArrayList<StudentRegistration>();
        regs.add(reg);
        regs.add(reg2);
        regService.save(reg);
        regService.save(reg2);
        Mockito.when(regRepo.findAll()).thenReturn(regs);
        List<StudentRegistration> result=regService.getAllRegistrations();
        Assert.assertEquals(result,regs);
    }
    @Test
    public void testsave(){
        StudentRegistration reg = new StudentRegistration(6,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",5,10,2016);
        regService.save(reg);
        verify(regRepo).save(reg);

    }

    @Test
    public void testDeleteById(){
        StudentRegistration reg = new StudentRegistration(6,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",5,10,2016);
        regService.save(reg);
        regService.delete(6);
        verify(regRepo).deleteByRegId(6);
        Assert.assertNull(regService.findByRegId(6));
    }

    @Test
    public void testDeleteByAm(){
        StudentRegistration reg = new StudentRegistration(6,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",5,10,2016);
        regService.save(reg);
        regService.deleteByRegAm(3303);
        verify(regRepo).deleteByRegAm(3303);
        Assert.assertFalse(regService.getRegistrationsByAM(3303).contains(reg));
    }

    @Test
    public void testUpdate(){
        StudentRegistration reg = new StudentRegistration(6,3303,801,"cs03303@uoi.gr", 10.0,10.0,"Kostas",5,10,2016);
        given(regRepo.save(reg)).willReturn(reg);
        reg.setAm(5090);
        regService.update(reg);
        verify(regRepo).save(reg);

    }
    @Test
    public void testfindRegistrationsByCourse(){
        StudentRegistration reg = new StudentRegistration(6, 3303, 801, "cs03303@uoi.gr", 10.0, 10.0, "Kostas", 5, 10, 2016);
        List<StudentRegistration> regs = new ArrayList<StudentRegistration>();
        regs.add(reg);
        regService.save(reg);
        Mockito.when(regRepo.findByCourseId(801)).thenReturn(regs);

        List<StudentRegistration> result = regService.findRegistrationByCourse(801);
        Assert.assertEquals(result, regs);
    }
    @Test
    public void testFindOverallOfStudentByCourse(){
        StudentRegistration reg = new StudentRegistration(6, 3303, 801, "cs03303@uoi.gr", 10.0, 10.0, "Kostas", 5, 10, 2016);
        Student Kostas=new Student("Kostas", "cs03303", "Cs0@3303",  "cs03303@uoi.gr",3303,6,12,7.0);
        Course softwareEngineering = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",801,7.0,0.6,0.4);
        regService.save(reg);
        studentService.saveStudent(Kostas);
        courseService.save(softwareEngineering);
        given(regRepo.findByRegId(6)).willReturn(reg);
        given(courseRepo.findByCourseId(801)).willReturn(softwareEngineering);
        double result=regService.findOverallOfStudentByCourse(6,801);
        Assert.assertEquals(result,10.0,0.0);
    }
}
