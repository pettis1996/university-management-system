package CoursesManagementApp.com.uoi.SoftwareEngineering.DAOTests;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.Instructor;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Secretariat.Secretariat;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Secretariat.SecretariatRepo;
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
public class SecretRepoTest {
    @Autowired
    private SecretariatRepo secretRepo;

    @Test
    public void findByUserNameHappyDay(){

        secretRepo.deleteAll();
        Secretariat secret1=new Secretariat();
        secret1.setId(1);
        secret1.setUserName("css2345");
        secretRepo.save(secret1);
        Assert.assertNotNull(secretRepo.findByUserName("css2345"));
        secretRepo.deleteAll();
    }
    @Test
    public void findByUserNameWrongUserName(){
        secretRepo.deleteAll();
        Secretariat secret1=new Secretariat();
        secret1.setId(1);
        secret1.setUserName("cse2347");
        secretRepo.save(secret1);
        Secretariat secretRnd=secretRepo.findByUserName("csa2345");
        Assert.assertNull(secretRnd);
        secretRepo.deleteAll();
    }
}
