package CoursesManagementApp.com.uoi.SoftwareEngineering.MVCController;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.InstructorService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Secretariat.SecretariatService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController

public class CourseMgtAppController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentRegistrationService registrationService;
    @Autowired
    private SecretariatService secretService;

    @Autowired
    private InstructorService instructorService;

    @RequestMapping("/welcome")
    public ModelAndView locale()
    {   ModelAndView m=new ModelAndView();
        m.setViewName("welcome");
        return m;
    }

}
