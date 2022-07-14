package CoursesManagementApp.com.uoi.SoftwareEngineering.DAOTests;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.Instructor;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.InstructorRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class InstructorRepoTest {

    @Autowired
    private InstructorRepo instructorRepo;
    @Test
    public void findByUserNameHappyDay(){

        instructorRepo.deleteAll();
        Instructor instructor1=new Instructor();
        instructor1.setId(1);
        instructor1.setUserName("cse2345");
        instructorRepo.save(instructor1);
        Assert.assertNotNull(instructorRepo.findByUserName("cse2345"));
        instructorRepo.deleteAll();
    }
    @Test
    public void findByUserNameWrongUserName(){
        instructorRepo.deleteAll();
        Instructor instructor1=new Instructor();
        instructor1.setId(1);
        instructor1.setUserName("cse2347");
        instructorRepo.save(instructor1);
        Instructor instructorRnd=instructorRepo.findByUserName("csa2345");
        Assert.assertNull(instructorRnd);
        instructorRepo.deleteAll();
    }
}
