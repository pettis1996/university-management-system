package CoursesManagementApp.com.uoi.SoftwareEngineering.EntityTests;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Secretariat.Secretariat;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@ContextConfiguration(locations = "/test-context.xml")
public class SecretariatTest {

    @Test
    public void createSecretariatHappyDay(){
        Secretariat Jenny=new Secretariat("Jenny","cssJenny","Css@Jenny1","cssJenny@uoi.gr");
        assertThat(Jenny).isNotNull();
        assertThat(Jenny.getName()).isEqualTo("Jenny");
    }

    @Test
    public void createSecretariatWrongUsername(){
        try {
            Secretariat Jenny = new Secretariat("Jenny", "cseJenny", "Css@Jenny1", "cseJenny@uoi.gr");
        }catch(IllegalArgumentException e){
            assertEquals("Secretariat's username should start with css",e.getMessage());
        }
    }

}
