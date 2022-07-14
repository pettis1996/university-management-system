package CoursesManagementApp.com.uoi.SoftwareEngineering.DAOTests;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Application;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;


@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepo;
    @Test
    public void findByCourseIdHappyDay(){
        Course course =new Course();
        course.setCourseId(10);
        course.setCourseName("Texnologia");
        course.setYear(4);
        courseRepo.save(course);

        Course dbcourse=courseRepo.findByCourseId(10);
        Assert.assertNotNull(dbcourse);
        Assert.assertEquals("Texnologia",dbcourse.getCourseName());
        Assert.assertEquals(4,dbcourse.getYear());
    }

    @Test
    public void findByCourseIdNull(){
        Course course =new Course();
        course.setCourseId(10);
        course.setCourseName("Texnologia");
        course.setYear(4);
        courseRepo.save(course);
        Course dbCourse=new Course();
        dbCourse = courseRepo.findByCourseId(99);
        Assert.assertNull(dbCourse);
    }
    @Test
    public void findByInstructorLoginHappyDay(){
        Course course =new Course();
        course.setCourseId(10);
        course.setCourseName("Texnologia");
        course.setYear(4);
        course.setInstructorLogin("User806");
        courseRepo.save(course);
        List<Course> dbcourses=new ArrayList<Course>();
        dbcourses=courseRepo.findByInstructorLogin("User806");
        Assert.assertNotNull(dbcourses);
        Assert.assertEquals(dbcourses.get(0).getInstructorLogin(),"User806");
        Assert.assertEquals(1,dbcourses.size());

    }

    @Test
    public void findByInstructorLoginNull(){
        Course course =new Course();
        course.setCourseId(10);
        course.setCourseName("Texnologia");
        course.setYear(4);
        course.setInstructorLogin("User804");
        courseRepo.save(course);
        List<Course> dbcourses=courseRepo.findByInstructorLogin(null);

        Assert.assertEquals(0,dbcourses.size());
    }
    @Test
    public void findByInstructorLoginEmpty(){
        Course course =new Course();
        course.setCourseId(10);
        course.setCourseName("Texnologia");
        course.setYear(4);
        course.setInstructorLogin("User803");
        courseRepo.save(course);
        Course[] emptyArray= new Course[0];
        List<Course> dbcourses=courseRepo.findByInstructorLogin("");
        Assert.assertEquals(dbcourses.toArray(),emptyArray);
        Assert.assertEquals(0,dbcourses.size());
    }

    @Test
    public void deleteByCourseIdHappyDay(){
        Course course =new Course();
        course.setCourseId(10);
        course.setCourseName("Texnologia");
        course.setYear(4);
        course.setInstructorLogin("User805");
        Course course2=new Course();
        course2.setCourseId(15);
        course2.setCourseName("anaktisi");
        course2.setInstructorLogin("User805");
        courseRepo.save(course);
        courseRepo.save(course2);
        Course[] emptyArray= new Course[0];
        courseRepo.deleteByCourseId(10);
        List<Course> dbcourses=courseRepo.findByInstructorLogin("User805");
        Assert.assertEquals(dbcourses.get(0).getCourseId(),15);
        Assert.assertEquals(1,dbcourses.size());
    }
    @Test
    public void deleteByCourseIdWrongId(){
        Course course =new Course();
        course.setCourseId(10);
        course.setCourseName("Texnologia");
        course.setYear(4);
        course.setInstructorLogin("User805");
        Course course2=new Course();
        course2.setCourseId(15);
        course2.setCourseName("anaktisi");
        course2.setInstructorLogin("User805");
        courseRepo.save(course);
        courseRepo.save(course2);
        Course[] emptyArray= new Course[0];
        courseRepo.deleteByCourseId(17);
        List<Course> dbcourses=new ArrayList<Course>();
        dbcourses=courseRepo.findByInstructorLogin("User805");
        Assert.assertNotEquals(1,dbcourses.size());
    }
    @Test
    public void updateCourseHappyDay(){
        Course course =new Course();
        course.setCourseId(10);
        course.setCourseName("Texnologia");
        course.setYear(4);
        course.setInstructorLogin("User805a");

        courseRepo.save(course);
        Course course2=new Course();
        course2.setCourseId(10);
        course2.setCourseName("anaktisi");
        course2.setInstructorLogin("User805a");
        courseRepo.save(course2);
        List<Course> dbcourses=courseRepo.findByInstructorLogin("User805a");
        Assert.assertEquals(dbcourses.size(),1);
        Assert.assertEquals(dbcourses.get(0).getCourseName(),course2.getCourseName());
    }
    @Test
    public void updateCourseDifferentCourseId(){
        Course course =new Course();
        course.setCourseId(10);
        course.setCourseName("Texnologia");
        course.setYear(4);
        course.setInstructorLogin("User805b");

        courseRepo.save(course);
        Course course2=new Course();
        course2.setCourseId(11);
        course2.setCourseName("anaktisi");
        course2.setInstructorLogin("User805b");
        courseRepo.save(course2);
        List<Course> dbcourses=courseRepo.findByInstructorLogin("User805b");
        Assert.assertNotEquals(dbcourses.size(),1);
    }

}
