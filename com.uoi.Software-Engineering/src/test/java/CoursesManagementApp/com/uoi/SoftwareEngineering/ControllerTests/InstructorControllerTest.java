package CoursesManagementApp.com.uoi.SoftwareEngineering.ControllerTests;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.*;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Secretariat.SecretariatService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.BDDAssumptions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.engine.JupiterTestEngine;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class InstructorControllerTest {


        @Autowired
        private WebApplicationContext webApplicationContext;


        @Autowired
        private MockMvc mockMvc;

        @InjectMocks
        private InstructorServiceImpl instructorService;

        @Mock
        private InstructorRepo instructorRepo;

        @MockBean
        private CourseService courseService;

        @MockBean
        private StudentRegistrationService registrationService;

        @MockBean
        private SecretariatService secretService;

        @InjectMocks
        private InstructorController instructorController;

        @Test
        public void shouldCreateMockMvc(){
            Assert.assertNotNull(mockMvc);
        }

        @Test
        public void givenWac_whenServletContext_thenItProvidesGreetController() {
            ServletContext servletContext = webApplicationContext.getServletContext();

            Assert.assertNotNull(servletContext);
            Assert.assertTrue(servletContext instanceof MockServletContext);
            Assert.assertNotNull(webApplicationContext.getBean("instructorController"));
        }

        @Test
        public void testShowRegpageInstructor() throws Exception {

            this.mockMvc.perform(get("/regpageInstructor").contentType(MediaType.ALL)).andExpect(status().isOk()).andExpect(view().name("registrationInstructor")).andExpect(model().attributeExists("instructor"));
        }

        @Test
        @SqlGroup({
                @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/startingDataToRunApplication.sql"),
                @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, statements = "delete from instructor; delete from course;")
        })
        public void testShowInstructorCourses() throws Exception {

                this.mockMvc.perform(get("/instructorCourses")).andExpect(status().isOk()).andExpect(view().name("instructorCourses")).
                        andExpect(MockMvcResultMatchers.model().attribute("myCourses", Matchers.notNullValue()));
        }

        @Test
        @SqlGroup({
                @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/startingDataToRunApplication.sql"),
                @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, statements = "delete from instructor; delete from course;")
        })
        public void testLoginInstructor() throws Exception {
            this.mockMvc.perform(get("/loginInstructor")).andExpect(status().isOk()).andExpect(model().attributeExists("instructor")).andExpect(view().name("loginInstructor")).andExpect(model().attributeExists("instructor")).andExpect(model().attributeHasNoErrors("instructor"));
        }


        @Test
        public void testShowInstructorHomePage() throws Exception {
                this.mockMvc.perform(get("/homepageInstructor/{userName}","cse4520")).andExpect(status().isOk()).andExpect(view().name("home_page_instructor")).
                        andExpect(MockMvcResultMatchers.model().attribute("instructors", Matchers.notNullValue()));
        }
}

