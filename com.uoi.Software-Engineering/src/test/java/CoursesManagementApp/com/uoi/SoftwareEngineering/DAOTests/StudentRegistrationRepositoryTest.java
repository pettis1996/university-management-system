package CoursesManagementApp.com.uoi.SoftwareEngineering.DAOTests;

import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class StudentRegistrationRepositoryTest {

    @Autowired
    private StudentRegistrationRepository registrationRepo;

    @Test
    public void findByCourseIdHappyDay(){
        registrationRepo.deleteAll();
        StudentRegistration registration=new StudentRegistration();
        StudentRegistration registration2=new StudentRegistration();
        StudentRegistration registration3=new StudentRegistration();
        registration.setRegistrationId(1);
        registration.setCourseId(10);
        registration2.setRegistrationId(2);
        registration2.setCourseId(10);
        registration3.setRegistrationId(3);
        registration3.setCourseId(15);
        registrationRepo.save(registration);
        registrationRepo.save(registration2);
        registrationRepo.save(registration3);
        List<StudentRegistration> list=new ArrayList<StudentRegistration>();
        list=registrationRepo.findByCourseId(10);
        Assert.assertNotNull(list);
        Assert.assertEquals(2,list.size());
        registrationRepo.deleteAll();
    }

    @Test
    public void findByCourseIdWrongId(){
        registrationRepo.deleteAll();
        StudentRegistration registration=new StudentRegistration(10,3303,801,"cs03303@uoi.gr", 10.0,10.0,"kyriakos",5,10,2016);
        StudentRegistration registration2=new StudentRegistration(10,3303,801,"cs03303@uoi.gr", 10.0,10.0,"kyriakos",5,10,2016);
        StudentRegistration registration3=new StudentRegistration(10,3303,801,"cs03303@uoi.gr", 10.0,10.0,"kyriakos",5,10,2016);
        registration.setCourseId(7);
        registration2.setCourseId(6);
        registration3.setCourseId(5);
        registrationRepo.save(registration);
        registrationRepo.save(registration2);
        registrationRepo.save(registration3);
        List<StudentRegistration> list=new ArrayList<StudentRegistration>();
        list=registrationRepo.findByCourseId(9);
        Assert.assertNotNull(list);
        Assert.assertEquals(0,list.size());
        registrationRepo.deleteAll();
    }
    @Test
    public void findByRegistrationIdHappyDay(){
        registrationRepo.deleteAll();
        StudentRegistration registration=new StudentRegistration(10,3303,801,"cs03303@uoi.gr", 10.0,10.0,"kyriakos",5,10,2016);
        StudentRegistration registration2=new StudentRegistration(10,3303,801,"cs03303@uoi.gr", 10.0,10.0,"kyriakos",5,10,2016);
        StudentRegistration registration3=new StudentRegistration(10,3303,801,"cs03303@uoi.gr", 10.0,10.0,"kyriakos",5,10,2016);
        /*registration.setCourseId(10);
        registration2.setCourseId(10);
        registration3.setCourseId(15);*/
        registrationRepo.save(registration);
        registrationRepo.save(registration2);
        registrationRepo.save(registration3);
        StudentRegistration registrationrnd=new StudentRegistration();
        registrationrnd=registrationRepo.findByRegId(registration.getRegistrationId());
        Assert.assertNotNull(registrationrnd);
        Assert.assertEquals(registrationrnd,registration);
        registrationRepo.deleteAll();
    }
    @Test
    public void findByRegistrationIdWrongId(){
        registrationRepo.deleteAll();
        StudentRegistration registration=new StudentRegistration(10,3303,801,"cs03303@uoi.gr", 10.0,10.0,"kyriakos",5,10,2016);
        StudentRegistration registration2=new StudentRegistration(10,3303,801,"cs03303@uoi.gr", 10.0,10.0,"kyriakos",5,10,2016);
        StudentRegistration registration3=new StudentRegistration(10,3303,801,"cs03303@uoi.gr", 10.0,10.0,"kyriakos",5,10,2016);
        registration.setCourseId(10);
        registration2.setCourseId(16);
        registration3.setCourseId(15);
        registrationRepo.save(registration);
        registrationRepo.save(registration2);
        registrationRepo.save(registration3);
        StudentRegistration registrationrnd=new StudentRegistration();
        registrationrnd=registrationRepo.findByRegId(16);
        Assert.assertNull(registrationrnd);
        registrationRepo.deleteAll();
    }

    @Test
    public void findByRegistrationAmHappyDay(){
        registrationRepo.deleteAll();
        StudentRegistration registration=new StudentRegistration(18,3303,801,"cs03303@uoi.gr", 10.0,10.0,"sakis",5,10,2016);
        StudentRegistration registration2=new StudentRegistration(17,3303,801,"cs03303@uoi.gr", 10.0,10.0,"makis",5,10,2016);
        StudentRegistration registration3=new StudentRegistration(10,3303,801,"cs03303@uoi.gr", 10.0,10.0,"lakis",5,10,2016);
        registration.setRegistrationId(1);
        registration.setAm(3303);
        registration.setRegistrationId(2);
        registration2.setAm(3136);
        registration.setRegistrationId(3);
        registration3.setAm(4285);
        registrationRepo.save(registration);
        registrationRepo.save(registration2);
        registrationRepo.save(registration3);
        List<StudentRegistration> registrationrnd=new ArrayList<StudentRegistration>();
        registrationrnd=registrationRepo.findByRegAm(registration.getAm());
        Assert.assertNotNull(registrationrnd);
        Assert.assertTrue(registrationrnd.contains(registration));
        registrationRepo.deleteAll();
    }

    @Test
    public void findByRegistrationAmWrongAm(){
        registrationRepo.deleteAll();
        StudentRegistration registration=new StudentRegistration(10,3303,801,"cs03303@uoi.gr", 10.0,10.0,"antreas",5,10,2016);
        StudentRegistration registration2=new StudentRegistration(10,3303,801,"cs03303@uoi.gr", 10.0,10.0,"mastoras",5,10,2016);
        StudentRegistration registration3=new StudentRegistration(10,3303,801,"cs03303@uoi.gr", 10.0,10.0,"kyriakos",5,10,2016);
        registration.setAm(3303);
        registration2.setAm(3136);
        registration3.setAm(4285);
        registrationRepo.save(registration);
        registrationRepo.save(registration2);
        registrationRepo.save(registration3);
        List<StudentRegistration> registrationrnd=new ArrayList<StudentRegistration>();
        registrationrnd=registrationRepo.findByRegAm(6000);
        Assert.assertNotNull(registrationrnd);
        Assert.assertFalse(registrationrnd.contains(registration));
        Assert.assertFalse(registrationrnd.contains(registration2));
        Assert.assertFalse(registrationrnd.contains(registration3));
        Assert.assertEquals(0,registrationrnd.size());
        registrationRepo.deleteAll();
    }
    @Test
    public void deleteByRegistrationIdHappyDay(){
        registrationRepo.deleteAll();
        StudentRegistration registration=new StudentRegistration();
        StudentRegistration registration2=new StudentRegistration();
        StudentRegistration registration3=new StudentRegistration();
        registration.setRegistrationId(1);
        registration2.setRegistrationId(2);
        registration3.setRegistrationId(3);
        registrationRepo.save(registration);
        registrationRepo.save(registration2);
        registrationRepo.save(registration3);
        registrationRepo.deleteByRegId(2);
        StudentRegistration registrationrnd=new StudentRegistration();
        registrationrnd=registrationRepo.findByRegId(2);
        Assert.assertNull(registrationrnd);
        registrationRepo.deleteAll();
    }
    @Test
    public void deleteByRegistrationIdWrongId(){
        registrationRepo.deleteAll();
        StudentRegistration registration=new StudentRegistration();
        StudentRegistration registration2=new StudentRegistration();
        StudentRegistration registration3=new StudentRegistration();
        registration.setRegistrationId(1);
        registration2.setRegistrationId(2);
        registration3.setRegistrationId(3);
        registrationRepo.save(registration);
        registrationRepo.save(registration2);
        registrationRepo.save(registration3);
        registrationRepo.deleteByRegId(5);
        List<StudentRegistration> registrationrnd=new ArrayList<StudentRegistration>();
        registrationrnd=registrationRepo.findAll();
        Assert.assertEquals(registrationrnd.size(),3);
        registrationRepo.deleteAll();
    }
    @Test
    public void updateRegistrationHappyDay(){
        registrationRepo.deleteAll();
        StudentRegistration registration=new StudentRegistration();
        StudentRegistration registration2=new StudentRegistration();
        StudentRegistration registration3=new StudentRegistration();
        registration.setRegistrationId(1);
        registration.setAm(3303);
        registration.setCourseId(15);
        registrationRepo.save(registration);
        StudentRegistration registrationrnd=new StudentRegistration();
        registrationrnd.setRegistrationId(1);
        registrationrnd.setAm(3136);
        registrationrnd.setCourseId(15);
        registrationRepo.save(registrationrnd);
        List<StudentRegistration> list=new ArrayList<StudentRegistration>();
        list=registrationRepo.findByCourseId(15);
        Assert.assertNotNull(registrationRepo.findByRegAm(3136));
        Assert.assertNotNull(registrationRepo.findByCourseId(15));
        registrationRepo.deleteAll();
    }
    @Test
    public void updateRegistrationWrongId(){
        registrationRepo.deleteAll();
        StudentRegistration registration=new StudentRegistration();
        StudentRegistration registration2=new StudentRegistration();
        StudentRegistration registration3=new StudentRegistration();
        registration.setRegistrationId(1);
        registration.setAm(3303);
        registration.setCourseId(15);
        registrationRepo.save(registration);
        StudentRegistration registrationrnd=new StudentRegistration();
        registrationrnd.setRegistrationId(2);
        registrationrnd.setAm(3136);
        registrationrnd.setCourseId(15);
        registrationRepo.save(registrationrnd);
        List<StudentRegistration> list=new ArrayList<StudentRegistration>();
        list=registrationRepo.findByCourseId(15);
        Assert.assertNotNull(registrationRepo.findByRegAm(3136));
        Assert.assertNotNull(registrationRepo.findByCourseId(15));
        Assert.assertEquals(2,list.size());
        registrationRepo.deleteAll();
    }
}
