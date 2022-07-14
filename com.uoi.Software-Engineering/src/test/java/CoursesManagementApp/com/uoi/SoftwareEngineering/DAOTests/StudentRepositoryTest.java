package CoursesManagementApp.com.uoi.SoftwareEngineering.DAOTests;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.StudentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepo;

    @Test
    public void findByAmHappyDay(){
        studentRepo.deleteAll();
        Student std=new Student();
        std.setAm(3303);
        Student std2 = new Student();
        std2.setAm(4285);
        studentRepo.save(std);
        studentRepo.save(std2);
        Student stdrnd=studentRepo.findByAm(3303);
        Assert.assertNotNull(stdrnd);
        Assert.assertEquals(stdrnd.getAm(),3303);
        studentRepo.deleteAll();
    }
    @Test
    public void findByAmWrongAm(){
        studentRepo.deleteAll();
        Student std=new Student();
        std.setAm(3303);
        Student std2 = new Student();
        std2.setAm(4285);
        studentRepo.save(std);
        studentRepo.save(std2);
        Student stdrnd=studentRepo.findByAm(3302);
        Assert.assertNull(stdrnd);
        studentRepo.deleteAll();
    }

    @Test
    public void findByEmailHappyDay(){
        studentRepo.deleteAll();
        Student std=new Student();
        std.setAm(3303);
        std.setEmail("cs03303@uoi.gr");
        Student std2 = new Student();
        std2.setAm(4285);
        studentRepo.save(std);
        studentRepo.save(std2);
        Student stdrnd=studentRepo.findByEmail("cs03303@uoi.gr");
        Assert.assertNotNull(stdrnd);
        Assert.assertEquals(stdrnd.getEmail(),"cs03303@uoi.gr");
        studentRepo.deleteAll();
    }

    @Test
    public void findByEmailWrongEmail(){
        studentRepo.deleteAll();
        Student std=new Student();
        std.setAm(3303);
        std.setEmail("cs03303@uoi.gr");
        Student std2 = new Student();
        std2.setAm(4285);
        studentRepo.save(std);
        studentRepo.save(std2);
        Student stdrnd=studentRepo.findByEmail("cse3303@uoi.gr");
        Assert.assertNull(stdrnd);
        studentRepo.deleteAll();
    }
}
