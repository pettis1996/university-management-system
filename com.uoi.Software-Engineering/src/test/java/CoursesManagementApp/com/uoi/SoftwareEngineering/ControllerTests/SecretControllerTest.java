package CoursesManagementApp.com.uoi.SoftwareEngineering.ControllerTests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class SecretControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateMockMvc() {
        Assert.assertNotNull(mockMvc);
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webApplicationContext.getBean("secretariatController"));
    }

    @Test
    public void testShowRegpageSecret() throws Exception {

        this.mockMvc.perform(get("/regpageSecretariat").contentType(MediaType.ALL)).andExpect(status().isOk()).andExpect(view().name("registrationSecret")).andExpect(model().attributeExists("secret"));
    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/startingDataToRunApplication.sql"),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, statements = "delete from instructor; delete from course;")
    })
    public void testLoginSecret() throws Exception {
        this.mockMvc.perform(get("/loginSecret")).andExpect(status().isOk()).andExpect(view().name("loginSecret")).andExpect(model().attributeExists("secret")).andExpect(model().attributeHasNoErrors("secret"));
    }

    @Test
    public void testAddCoursePage() throws Exception {
        this.mockMvc.perform(get("/addCoursepage")).andExpect(status().isOk()).andExpect(view().name("addCourse")).andExpect(model().attributeExists("course")).andExpect(model().attributeHasNoErrors("course"));
    }

    @Test
    public void testShowSecretprofile() throws Exception{
        this.mockMvc.perform(get("/secretprofile")).andExpect(status().isOk()).andExpect(view().name("secretprofile")).andExpect(model().attributeExists("secret")).andExpect(model().attributeHasNoErrors("secret"));
    }
}

