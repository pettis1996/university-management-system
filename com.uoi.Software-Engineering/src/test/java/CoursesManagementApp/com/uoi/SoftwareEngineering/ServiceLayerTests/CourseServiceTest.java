package CoursesManagementApp.com.uoi.SoftwareEngineering.ServiceLayerTests;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseRepository;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseServiceImpl;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationRepository;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/test-context.xml")
@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepo;

    @Mock
    private StudentRegistrationRepository registrationRepo;

    @Mock
    private StudentRegistrationService registrationService;

    @InjectMocks
    private CourseServiceImpl courseService;
    @Mock
    private Course softwareEngineering;
    @Test
    public void findCourseByIdNullCourse(){
        Integer courseId=10;

        Mockito.when(courseRepo.findByCourseId(courseId)).thenReturn(null);

        Course result=courseService.findCourseById(courseId);
        Assert.assertNull(result);
    }

    @Test
    public void findCourseByIdHappyDay(){
        Course softwareEngineering = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0.6,0.4);

        courseService.save(softwareEngineering);
        Mockito.when(courseRepo.findByCourseId(803)).thenReturn(softwareEngineering);

        Course result=courseService.findCourseById(803);
        Assert.assertEquals(result,softwareEngineering);
    }
    @Test
    public void testsave(){
        Course softwareEngineering = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0.6,0.4);
        courseService.save(softwareEngineering);
        verify(courseRepo).save(softwareEngineering);
    }

    @Test
    public void testDeleteHappyDay(){
        Course softwareEngineering = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0.6,0.4);
        courseService.save(softwareEngineering);
        courseService.delete(803);
        verify(courseRepo).deleteByCourseId(803);
        Assert.assertNull(courseService.findCourseById(803));
    }

    @Test
    public void testUpdate(){
        Course softwareEngineering = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0.6,0.4);
        given(courseRepo.save(softwareEngineering)).willReturn(softwareEngineering);

        softwareEngineering.setYear(5);
        courseService.update(softwareEngineering);
        verify(courseRepo).save(softwareEngineering);
    }
    @Test
    public void findCourseByInstructorLoginHappyDay(){
        Course softwareEngineering = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0.6,0.4);
        List<Course> courses=new ArrayList<Course>();
        courses.add(softwareEngineering);
        courseService.save(softwareEngineering);
        Mockito.when(courseRepo.findByInstructorLogin("User803")).thenReturn(courses);

        List<Course> result=courseService.findCourseByInstructorLogin("User803");
        Assert.assertEquals(result,courses);
    }

    @Test
    public void testgetAllCourses(){
        Course softwareEngineering = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0.6,0.4);
        Course softwaredevelopment = new Course(805,"Software Development","Welcome to Software Development",5,4,8,"User803",803,7.0,0.6,0.4);

        List<Course> courses=new ArrayList<Course>();
        courses.add(softwareEngineering);
        courses.add(softwaredevelopment);
        courseService.save(softwareEngineering);
        courseService.save(softwaredevelopment);
        Mockito.when(courseRepo.findAll()).thenReturn(courses);
        List<Course> result=courseService.getAllCourses();
        Assert.assertEquals(result,courses);
    }



    @Test
    public void testaddRegistrationsToCourseHappyDay(){
        //Course softwareEngineering = new Course(803,"Software Engineering","Welcome to Software Engineering",4,8,"User803",803,7.0,0.6,0.4);
        softwareEngineering.setCourseId(803);
        StudentRegistration reg = new StudentRegistration(6,3303,803,"cs03303@uoi.gr", 10.0,10.0,"Kostas",5,10,2016);
        courseService.save(softwareEngineering);
        registrationService.save(reg);
        Mockito.when(registrationRepo.findByRegId(6)).thenReturn(reg);
        Mockito.when(courseService.findCourseById(803)).thenReturn(softwareEngineering);
        Mockito.when(softwareEngineering.addStudentRegistration(reg)).thenReturn(true);
        boolean result=courseService.addRegistrationToCourse(6,803);
        Assert.assertTrue(result);
    }

    @Test
    public void testdeleteRegistrationsFromCourseHappyDay(){
        //Course softwareEngineering = new Course(803,"Software Engineering","Welcome to Software Engineering",4,8,"User803",803,7.0,0.6,0.4);
        softwareEngineering.setCourseId(803);
        StudentRegistration reg = new StudentRegistration(6,3303,803,"cs03303@uoi.gr", 10.0,10.0,"Kostas",5,10,2016);
        courseService.save(softwareEngineering);
        registrationService.save(reg);
        Mockito.when(courseService.findCourseById(803)).thenReturn(softwareEngineering);
        courseService.deleteStudentRegistrationsFromCourse(803,reg);
        verify(registrationService).delete(reg.getRegistrationId());
        verify(softwareEngineering).removeStudentRegistration(reg);
    }

    @Test
    public void testfindRegistrationsInCourse(){
        StudentRegistration reg = new StudentRegistration(6,3303,805,"cs03303@uoi.gr", 10.0,10.0,"Kostas",5,10,2016);
        Course softwaredevelopment = new Course(805,"Software Development","Welcome to Software Development",5,4,8,"User803",803,7.0,0.6,0.4);
        registrationService.save(reg);
        softwaredevelopment.addStudentRegistration(reg);
        List<StudentRegistration> registrations=new ArrayList<StudentRegistration>();
        registrations.add(reg);
        given(registrationRepo.findByCourseId(805)).willReturn(registrations);
        boolean result=courseService.findRegistrationsInCourse(softwaredevelopment.getCourseId()).contains(reg);
        Assert.assertTrue(result);
    }
    @Test
    public void testupdateCourseWeights(){
        Course softwareEngineering = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0.6,0.4);



        courseService.updatecourseWeights("User803",803,0.5,0.5);
        verify(courseRepo).findByInstructorLogin("User803");
        Assert.assertEquals(softwareEngineering.getExamPercentage(),0.6,0.0);
        Assert.assertEquals(softwareEngineering.getProjectPercentage(),0.4,0.0);
    }

    @Test
    public void testupdateCourseWeightsWrongInstroctorLogin(){
        Course softwareEngineering = new Course(803,"Software Engineering","Welcome to Software Engineering",5,4,8,"User803",803,7.0,0.6,0.4);
        List<Course> courses=new ArrayList<Course>();
        given(courseRepo.findByInstructorLogin("User807")).willReturn(courses);


        courseService.updatecourseWeights("User807",803,0.5,0.5);
        verify(courseRepo).findByInstructorLogin("User807");
        Assert.assertEquals(softwareEngineering.getExamPercentage(),0.6,0.0);
        Assert.assertEquals(softwareEngineering.getProjectPercentage(),0.4,0.0);
    }

}
