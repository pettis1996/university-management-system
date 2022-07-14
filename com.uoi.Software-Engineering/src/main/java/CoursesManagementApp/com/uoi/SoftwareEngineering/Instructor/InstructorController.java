package CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Secretariat.SecretariatService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class InstructorController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentRegistrationService registrationService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private InstructorRepo instructorRepo;

    @Autowired
    private SecretariatService secretService;

    @GetMapping("/CoursesPerInstructor/{instructorLogin}")
    public List<Course> showCoursesPerInstructor(@PathVariable("instructorLogin") String instructorLogin) {
        return courseService.findCourseByInstructorLogin(instructorLogin);
    }

    @GetMapping("/RegistrationsPerCourse/{courseId}")
    public List<StudentRegistration> showRegistrationsPerCourse(@PathVariable("courseId") int courseId) {
        return registrationService.findRegistrationByCourse(courseId);
    }

    @DeleteMapping("/DeleteRegistrationPerCourse/{courseId}/{registrationId}")
    public void deleteRegistrationsPerCourse(@PathVariable("courseId") int courseId, @PathVariable("registrationId") int registrationId) {
        instructorService.deleteRegistrationPerCourse(courseId, registrationId);
    }

    @PutMapping("/registerGradesToStudent/{studentId}/{courseId}/{examGrade}/{projectGrade}")
    public void registerGradesToStudent(@PathVariable("studentId") int studentId, @PathVariable("courseId") int courseId, @PathVariable("examGrade") float examGrade, @PathVariable("projectGrade") float projectGrade) {
        instructorService.registerGradesToStudent(studentId, courseId, examGrade, projectGrade);
    }

    @GetMapping("/InstructoroverallPerCourse/{courseId}")
    public String showOverallPerCourse(@PathVariable("courseId") int courseId, Model model) {
        ArrayList overalls = new ArrayList<String>();
        for (Integer overall : secretService.showOverallGradesPerStudentInCourse(courseId).keySet()) {
            overalls.add(overall + ": " + secretService.showOverallGradesPerStudentInCourse(courseId).get(overall));
        }
        model.addAttribute("overalls", overalls);
        return "studentOveralls";
    }

    @GetMapping("/statistics/{username}/Statistics/{courseId}")
    public String showStatisticsPerCourse(@ModelAttribute("instructor") Instructor instructor, Model model,@PathVariable("username")String userName) {
        List<String> statistics = new ArrayList<String>();
        List<Instructor> instructors=new ArrayList<Instructor>();
        instructors.add(instructorService.findByInstructorUserName(userName));
        model.addAttribute("instructors",instructors);
        for (String stat : courseService.calculateCourseStatistics(instructor.getCourseId()).keySet()) {
            statistics.add(stat + ": " + courseService.calculateCourseStatistics(instructor.getCourseId()).get(stat));
        }
        model.addAttribute("statistics", statistics);
        return "statistics_instructor_course";

    }

    @PostMapping("/computeOverall/{userName}/AverageGrade/{courseId}")
    public String computeAvgGrade(@ModelAttribute("instructor") Instructor instructor, RedirectAttributes ra) {

        if (courseService.findCourseById(instructor.getCourseId()) != null) {
            return "redirect:/statistics/" + instructor.getUserName() + "/AverageGrade/" + instructor.getCourseId();
        }
        List<String> errors = new ArrayList<String>();
        errors.add("Invalid courseId,this course does not exist");
        ra.addFlashAttribute("errors", errors);
        return "redirect:/statisticsPage/" + instructor.getUserName();
    }

    @PostMapping("/computeStatistics/{userName}/Statistics/{courseId}")
    public String computeStatistics(@ModelAttribute("instructor") Instructor instructor, RedirectAttributes ra) {

        if (courseService.findCourseById(instructor.getCourseId()) != null) {
            return "redirect:/statistics/" + instructor.getUserName() + "/Statistics/" + instructor.getCourseId();
        }
        List<String> errors = new ArrayList<String>();
        errors.add("Invalid courseId,this course does not exist");
        ra.addFlashAttribute("errors", errors);
        return "redirect:/statisticsPage/" + instructor.getUserName();
    }

    @GetMapping("/SyllabusInstructor/{userName}")
    public String showCoursesFromSyllabus(Model model, @ModelAttribute("instructor") Instructor instructor) {
        List<Instructor> instructors = new ArrayList<Instructor>();
        instructors.add(instructorService.findByInstructorUserName(instructor.getUserName()));
        List<Course> allCourses = courseService.getAllCourses();
        model.addAttribute("allCourses", allCourses);
        model.addAttribute("instructors", instructors);
        return "syllabus_currentsemester_instructor";
    }

    @GetMapping("/statistics/{userName}/AverageGrade/{courseId}")
    public String showAvgGradeOfCourse(@ModelAttribute("instructor") Instructor instructor, Model model) {
        ArrayList<String> overalls = new ArrayList<String>();
        for (StudentRegistration reg : registrationService.findRegistrationByCourse(instructor.getCourseId())) {
            overalls.add(reg.getAm() + ": " + registrationService.findOverallOfStudentByCourse(reg.getAm(), instructor.getCourseId()));

        }
        model.addAttribute("overalls", overalls);
        return "statistics_instructor_average";
    }

    @PostMapping("/statisticsManager/{userName}/{courseId}")
    public String statisticsmanager(@ModelAttribute("instructor") Instructor instructor, RedirectAttributes ra, @ModelAttribute("course") Course course) {
        if (courseService.findCourseByInstructorLogin(instructor.getUserName()).contains(courseService.findCourseById(instructor.getCourseId()))) {
            if (instructor.getStat().equals("AverageGrade")) {
                ra.addFlashAttribute("instructor", instructor);
                return "redirect:/statistics/" + instructor.getUserName() + "/AverageGrade" + "/" + course.getCourseId();
            } else if (instructor.getStat().equals("Statistics")) {
                ra.addFlashAttribute("instructor", instructor);
                return "redirect:/statistics/" + instructor.getUserName() + "/Statistics/" + course.getCourseId();
            }
        }
        return "redirect:/statisticsPage/" + instructor.getUserName() + "";
    }

    @GetMapping("/statisticsPage/{userName}")
    public String showStatisticsPage(Model model, @ModelAttribute("instructor") Instructor instructor) {
        List<String> stats = new ArrayList<String>();
        stats.add("AverageGrade");
        stats.add("Statistics");
        model.addAttribute("stats", stats);
        return "mycourses_statistics_instructor";
    }

    @PostMapping("/saveInstructor")
    public String saveInstructor(@ModelAttribute("instructor") Instructor instructor, RedirectAttributes ra) {
        List<String> errors = new ArrayList<String>();
        if (instructor.getId() == 0 || instructor.getUserName() == null || instructor.getEmail() == null || instructor.getPassWord() == null) {
            errors.add("Missing fields,fill all the required fields");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/regpageInstructor";
        }
        if (instructorService.findInstructorById(instructor.getId()) != null) {
            errors.add("Invalid id,this id is already used by another instructor");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/regpageInstructor";
        }
        if (instructor.getId() <= 0) {
            errors.add("Invalid id,instructor's id must be positive");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/regpageInstructor";
        }
        if (!instructor.getUserName().startsWith("cse")) {
            errors.add("Invalid username,username should start with cse");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/regpageInstructor";
        }
        if (!(instructor.isEmailValid(instructor.getEmail(), instructor.getUserName()) == true)) {
            errors.add(" Invalid email address,email should have the format username@uoi.gr");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/regpageInstructor";
        }
        if (!(instructor.getPassWord().length() >= 8) || instructor.checkPassword(instructor.getPassWord()) == false) {
            errors.add("Password has minimum length 8 and must contain at least one Uppercase,one lowercase,one number and one special character");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/regpageInstructor";
        }
        instructorService.addInstructor(instructor);
        instructor.setMyCoursesString("courses:");
        instructor.setCheck("-");
        return "redirect:/regpageInstructor?success";
    }

    @GetMapping("/regpageInstructor")
    public String showRegisterInstructorForm(@ModelAttribute("instructor") Instructor instructor) {

        return "register_instructor";
    }

    @GetMapping("/loginInstructor")
    public String login(@ModelAttribute("instructor") Instructor instructor) {
        return "login_page_instructor";
    }

    @GetMapping("/menuInstructor")
    public String showInstructorMenu(@ModelAttribute("instructor") Instructor instructor, RedirectAttributes ra) {
        List<String> errors = new ArrayList<String>();
        int checkusername = 0;
        int checkpassword = 0;
        String loginUser = "";
        for (Instructor ins : instructorService.getAllInstructors()) {
            if (ins.getUserName().equals(instructor.getUserName())) {
                checkusername = 1;
                loginUser = ins.getUserName();
                if (ins.getPassWord().equals(instructor.getPassWord())) {
                    checkpassword = 1;
                }
            }
        }
        if (checkusername == 0) {
            String error = "Invalid username,try Again";
            errors.add(error);
            ra.addFlashAttribute("errors", errors);
            return "redirect:/loginInstructor";
        }

        if (checkpassword == 0) {
            String error = "Invalid password,try again";
            errors.add(error);
            ra.addFlashAttribute("errors", errors);
            return "redirect:/loginInstructor";
        }
        List<Instructor> instructors = new ArrayList<Instructor>();
        instructors.add(instructorService.findByInstructorUserName(instructor.getUserName()));
        ra.addFlashAttribute("instructor", instructor);
        ra.addFlashAttribute("userName", loginUser);
        ra.addFlashAttribute("instructors", instructors);
        return "redirect:/homepageInstructor/" + loginUser;
    }

    @RequestMapping(value = "/homepageInstructor/{userName}", method = RequestMethod.GET)
    public String showInstructorpage(@ModelAttribute("instructor") Instructor instructor, @PathVariable("userName") String userName, Model model) {
        if(instructorService.findByInstructorUserName(instructor.getUserName())!=null) {
            List<Instructor> instructors = new ArrayList<Instructor>();
            instructors.add(instructorService.findByInstructorUserName(instructor.getUserName()));
            model.addAttribute("instructors", instructors);
            return "home_page_instructor";
        }
        return "redirect:/loginpage";
    }

    @GetMapping("/instructorCourses/{userName}")
    public String showListOfMyCourses(@ModelAttribute("instructor") Instructor instructor, Model model, @ModelAttribute("course") Course course, RedirectAttributes ra,@PathVariable("userName")String userName) {
        if(instructorService.findByInstructorUserName(userName)!=null) {
            List<Course> myCourses = courseService.findCourseByInstructorLogin(userName);
            model.addAttribute("myCourses", myCourses);
            List<Instructor> instructors = new ArrayList<Instructor>();
            instructors.add(instructorService.findByInstructorUserName(userName));
            model.addAttribute("instructors", instructors);
            List<Integer> ids = new ArrayList<Integer>();
            model.addAttribute("ids", ids);
            //ra.addFlashAttribute("myCourses",myCourses);
            ra.addFlashAttribute("ids", ids);
            ra.addFlashAttribute("instructor", instructor);
            return "mycourses_teaching_instructor";
        }
        return  "redirect:/loginpage";
    }

    @GetMapping("/instructorAddCourse/{userName}")
    public String addCourseToInstructorpage(@ModelAttribute("instructor") Instructor instructor, Model model) {
        List<Course> available = courseService.getAllCourses();

        for (Course c : courseService.findCourseByInstructorLogin(instructor.getUserName())) {
            available.remove(c);
        }
        model.addAttribute("available", available);
        return "mycourses_courseregistration_instructor";
    }

    @PostMapping("/addCourseInstructor/{userName}")
    public String addCourseInstructor(@ModelAttribute("instructor") Instructor instructor, RedirectAttributes ra) {
        if (courseService.findCourseById(instructor.getCourseId()) != null) {
            courseService.findCourseById(instructor.getCourseId()).setInstructorLogin(instructor.getUserName());
            courseService.update(courseService.findCourseById(instructor.getCourseId()));

            secretService.addCourseToInstructor(instructor.getCourseId(), instructor.getId());
            return "redirect:/instructorAddCourse/" + instructor.getUserName() + "?success";
        } else if (courseService.findCourseById(instructor.getCourseId()) != null && (courseService.findCourseById(instructor.getCourseId()).getInstructorLogin().equals(instructor.getUserName()))) {
            List<String> errors = new ArrayList<String>();
            errors.add("Invalid courseId,you are already teaching this course");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/instructorAddCourse/" + instructor.getUserName();
        } else {
            List<String> errors = new ArrayList<String>();
            errors.add("Invalid courseId,this course doesn't exist in syllabus");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/instructorAddCourse/" + instructor.getUserName();
        }

    }

    @GetMapping("/deleteCourseInstructor/{userName}")
    public String deleteCourseInstructor(@ModelAttribute("instructor") Instructor instructor, RedirectAttributes ra, @PathVariable("userName") String userName) {
        if (courseService.findCourseById(instructor.getCourseId()) != null && (courseService.findCourseById(instructor.getCourseId()).getInstructorLogin().equals(instructor.getUserName()))) {
            for(StudentRegistration reg : courseService.findRegistrationsInCourse(instructor.getCourseId())){
                registrationService.delete(reg.getRegistrationId());
            }
            secretService.deleteCourseFromInstructor(instructor.getCourseId(), instructor.getId());
            courseService.delete(instructor.getCourseId());
            instructor.setUserName(userName);
            ra.addFlashAttribute("instructor", instructor);
            return "redirect:/instructordeleteCourse/" + instructor.getUserName() + "?success";
        } else if (courseService.findCourseById(instructor.getCourseId()) != null && !(courseService.findCourseById(instructor.getCourseId()).getInstructorLogin().equals(instructor.getUserName()))) {
            List<String> errors = new ArrayList<String>();
            errors.add("Invalid courseId,you are not teaching this course");
            ra.addFlashAttribute("errors", errors);
            instructor.setUserName(userName);
            ra.addFlashAttribute("instructor", instructor);
            return "redirect:/instructordeleteCourse/" + instructor.getUserName();
        } else {
            List<String> errors = new ArrayList<String>();
            errors.add("Invalid courseId,this course doesn't exist in syllabus");
            ra.addFlashAttribute("errors", errors);
            instructor.setUserName(userName);
            ra.addFlashAttribute("instructor", instructor);
            return "redirect:/instructordeleteCourse/" + instructor.getUserName();
        }
    }

    @GetMapping("/instructordeleteCourse/{userName}")
    public String deleteCourseToInstructorpage(@ModelAttribute("instructor") Instructor instructor, Model model) {
        List<Course> myCourses = courseService.findCourseByInstructorLogin(instructor.getUserName());
        model.addAttribute("myCourses", myCourses);
        return "mycourses_deletecourse_instructor";
    }

    @GetMapping("/courseDescription/{userName}/{courseId}")
    public String courseDescription(@ModelAttribute("course") Course course,@ModelAttribute("registration") StudentRegistration registration, Model model,@PathVariable("userName") String userName,@PathVariable("courseId") int courseId) {
        List<Course> courses = new ArrayList<Course>();
        List<Instructor> instructors=new ArrayList<Instructor>();
        instructors.add(instructorService.findByInstructorUserName(userName));
        courses.add(courseService.findCourseById(courseId));
        model.addAttribute("courses", courses);
        model.addAttribute("instructors",instructors);
        model.addAttribute("instructor",instructorService.findByInstructorUserName(userName));
        List<StudentRegistration> regs =
                courseService.findRegistrationsInCourse(courseId);
        model.addAttribute("regs", regs);
        return "course_details";
    }
    @PostMapping("/updateGrades/{userName}/{courseId}")
    public String updateGradesInstructor(@ModelAttribute("reg") StudentRegistration reg,@ModelAttribute("course")Course course, RedirectAttributes ra,@PathVariable("userName") String userName,@PathVariable("courseId") int courseId) {
        StudentRegistration regi=registrationService.findByRegId(reg.getRegId());
//        if (registrationService.findByRegId(reg.getRegId())==null) {
//            List<String> errors = new ArrayList<String>();
//            errors.add("Invalid regId");
//            ra.addFlashAttribute("errors", errors);
//            return "redirect:/courseDescription/" + userName + "/" + course.getCourseId();
//        }
//        if(reg.getProjectGrade()>10.0 || reg.getProjectGrade()<=0){
//            List<String> errors = new ArrayList<String>();
//            errors.add("Invalid projectGrade");
//            ra.addFlashAttribute("errors", errors);
//            return "redirect:/courseDescription/" + userName + "/" + course.getCourseId();
//        }
//        if(reg.getExamGrade()>10.0 || reg.getExamGrade()<=0){
//            List<String> errors = new ArrayList<String>();
//            errors.add("Invalid examGrade");
//            ra.addFlashAttribute("errors", errors);
//            return "redirect:/courseDescription/" + userName + "/" + course.getCourseId() ;
//        }

        regi.setExamGrade(reg.getExamGrade());
        regi.setProjectGrade(reg.getProjectGrade());
        regi.setOverall((float) registrationService.findOverallOfStudentByCourse(regi.getAm(), regi.getCourseId()));

        registrationService.update(regi);
        return "redirect:/courseDescription/" + userName + "/" + regi.getCourseId() + "?success";
    }
    @PostMapping("/updateWeights/{userName}/{courseId}")
    public String updateWeightsInstructor(@ModelAttribute("reg") StudentRegistration reg,@ModelAttribute("course")Course course, RedirectAttributes ra,@PathVariable("userName") String userName,@PathVariable("courseId") int courseId) {
        if(course.getExamPercentage()>10.0 || course.getExamPercentage()<=0){
            List<String> errors = new ArrayList<String>();
            errors.add("Invalid exam's Weight");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/courseDescription/" + userName + "/" + course.getCourseId();
        }
        Course coursehelp=courseService.findCourseById(course.getCourseId());
        coursehelp.setExamPercentage(course.getExamPercentage());
        coursehelp.setProjectPercentage(1-course.getExamPercentage());

        for(StudentRegistration regi:courseService.findRegistrationsInCourse(coursehelp.getCourseId())){
            regi.setOverall((float) registrationService.findOverallOfStudentByCourse(regi.getAm(), regi.getCourseId()));
        }
        courseService.save(coursehelp);
        return "redirect:/courseDescription/" + userName + "/" + coursehelp.getCourseId() + "?success";
    }
}