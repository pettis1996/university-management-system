package CoursesManagementApp.com.uoi.SoftwareEngineering.Student;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.Instructor;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.InstructorService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    @Autowired
    private PersonalStudentService studentService;

    @Autowired
    private StudentRegistrationService registrationService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorService instructorService;

    @GetMapping("/coursesPassed")
    public String showCoursesPassed(@ModelAttribute Student student,Model attr){
        /*List<String> names=new ArrayList<String>();
        for(String s:studentService.findPassedCourses(student.getAm())){
            names.add(course.getCourseName());
        }*/
        attr.addAttribute("names",studentService.findPassedCourses(student.getAm()));
        return "coursesPassed";
    }

    @GetMapping("/semesterStudentCourses/{am}/{semester}")
    public String showCoursesThisSemesterpage(@ModelAttribute("student") Student student,Model attr){
        List<Student> students=new ArrayList<Student>();
        students.add(studentService.findStudentByAm(student.getAm()));
        attr.addAttribute("students",students);
        List<Course> current=studentService.currentSemester(student.getAm(),student.getSemester());
        for(Course c :current){

            if (c==null){
                System.out.println("test");
                return "mycourses_courses_student";
            }
        }
        attr.addAttribute("currentSemester",current);
        List<Course> passed=studentService.findPassedCourses(student.getAm());
        for(Course c :passed){
            if (c==null){
                return "mycourses_courses_student";
            }
        }
        attr.addAttribute("passed",passed);
        return "mycourses_courses_student";
    }
    @GetMapping("/myaverageGrade/{am}")
    public double showAvgGrade(@PathVariable("am") int am){
        return studentService.calculateAvgGradeOfAllCoursesPassed(am);
    }


    @GetMapping("/registrationStudent")
    public String showRegistrationForm(@ModelAttribute("student") Student student, BindingResult bindingResult, Model model){

        return "register_student";
    }
    @PostMapping("/registerStudent")
    public String registerStudentAccount(@ModelAttribute("student") Student student, RedirectAttributes ra){
        List<String> errors=new ArrayList<String>();
        if(!(student.getUserName()==null && student.getName()== null && student.getEmail()==null && student.getPassWord() == null && student.getYear()== 0 && student.getSemester()==0)) {
            if (student.getAm() < 0) {
                errors.add("Invalid Am,Am should be a positive Integer");
                ra.addFlashAttribute("errors", errors);
                return "redirect:/registrationStudent";
            }
            if (!student.getUserName().equals("cs0" + student.getAm())) {
                errors.add("Invalid username,username should be in the format cs0 and Am");
                ra.addFlashAttribute("errors", errors);
                return "redirect:/registrationStudent";
            }
            if (!student.getEmail().startsWith("cs0")
                    || !(student.isEmailValid(student.getEmail(), student.getUserName()) == true)) {
                errors.add("Invalid email address,email should have the format username@uoi.gr");
                ra.addFlashAttribute("errors", errors);
                return "redirect:/registrationStudent";
            }
            if (!(student.getPassWord().length() >= 8) || student.checkPassword(student.getPassWord()) == false) {
                errors.add("Password has minimum length 8 and must contain at least one Uppercase,one lowercase,one number and one special character");
                ra.addFlashAttribute("errors", errors);
                return "redirect:/registrationStudent";
            }
            if (student.getYear() <= 0) {
                errors.add("Year of studies should be an Integer greater than zero");
                ra.addFlashAttribute("errors", errors);
                return "redirect:/registrationStudent";
            }
            if (student.getSemester() > (student.getYear() * 2) || student.getSemester() < ((student.getYear() * 2) - 1)) {
                errors.add("Semester is not valid.Check it according to your Year");
                ra.addFlashAttribute("errors", errors);
                return "redirect:/registrationStudent";
            }
            student.setCheck("-");
            studentService.saveStudent(student);
            return "redirect:/registrationStudent?success";
        }
        errors.add("Missing fields,fill all the required fields");
        ra.addFlashAttribute("errors",errors);
        return "redirect:/registrationStudent";
    }

    @GetMapping("/loginStudent")
    public String loginStudent(@ModelAttribute("student") Student student){
        return "login_page_student";
    }

    @GetMapping("/menuStudent")
    public String showStudentMenu(@ModelAttribute("student") Student student,RedirectAttributes ra){
        List<String> errors=new ArrayList<String>();
        int passedCourses=studentService.findPassedCourses(student.getAm()).size();
        int checkusername=0;
        int checkpassword=0;
        int am=0;
        for(Student std: studentService.getAllStudents()) {
            if (std.getUserName().equals(student.getUserName())) {
                checkusername = 1;
                if(std.getPassWord().equals(student.getPassWord())){
                    checkpassword=1;
                    am= std.getAm();
                    student.setName(std.getName());
                    student.setCoursePassed(std.getCoursePassed());
                }
            }
        }
        if(checkusername==0){
            String error="Invalid username,try Again";
            errors.add(error);
            ra.addFlashAttribute("errors",errors);
            return "redirect:/loginStudent";
        }

        if(checkpassword==0){
            String error="Invalid password,try again";
            errors.add(error);
            ra.addFlashAttribute("errors",errors);
            return "redirect:/loginStudent";
        }
        ra.addFlashAttribute("am",student.getAm());
        List<Student> students=new ArrayList<Student>();
        students.add(studentService.findStudentByAm(am));
        String rlink="redirect:/index/"+am;
        ra.addFlashAttribute("students",students);
        ra.addFlashAttribute("std",student);
        return rlink ;
    }
    @RequestMapping(value="/index/{am}",method=RequestMethod.GET)
    public String showStudentIndex(@PathVariable("am") int am,Model model,RedirectAttributes ra,@ModelAttribute("student") Student student){

        if(studentService.findStudentByAm(student.getAm())!=null) {
            List<Student> students = new ArrayList<Student>();
            students.add(studentService.findStudentByAm(student.getAm()));

            model.addAttribute("students", students);
            ra.addFlashAttribute("student", student);
            return "home_page_student";
        }
        return "/loginpage";
    }



    @GetMapping("/Syllabus/{am}")
    public String showCoursesFromSyllabus(Model model,@ModelAttribute("student") Student student) {
    List<Course> allCourses=courseService.getAllCourses();
    model.addAttribute("allCourses",allCourses);
    return "syllabus_currentsemester_student";
    }

    @GetMapping("/showCoursesFromSyllabus/{am}")
    public String showCoursesFromSyllabus(@ModelAttribute("student")Student student,Model model,@ModelAttribute("registration") StudentRegistration registration,@PathVariable("am") int am){
        List<Course> availableCourses=courseService.getAllCourses();
        List<Student> students=new ArrayList<Student>();
        students.add(studentService.findStudentByAm(am));
        model.addAttribute("students",students);
        for(Course c: studentService.findPassedCourses(am)){
            availableCourses.remove(c);
        }
        for(Course c: courseService.getAllCourses()){
            for(StudentRegistration reg : registrationService.getRegistrationsByAM(am)){
                if(c.getCourseId()== reg.getCourseId()){
                    availableCourses.remove(c);
                }
            }
        }
        model.addAttribute("available",availableCourses);
        return "mycourses_courseregistration_student";
    }
    @GetMapping("/courseDescription/{courseId}")
    public String courseDescription(@ModelAttribute("course") Course course,Model model){
        List<Course> courses=new ArrayList<Course>();
        courses.add(courseService.findCourseById(course.getCourseId()));
        model.addAttribute("courses",courses);
        List<StudentRegistration> regs=
                courseService.findRegistrationsInCourse(course.getCourseId());
        model.addAttribute("regs",regs);
        return "course_details_others";
    }
    @GetMapping("/addRegistrationpage")
    public String addStudentRegistrationpage(@ModelAttribute("registration")StudentRegistration registration){
        return "addRegistrationpage";
    }
    @PostMapping("/addRegistration/{am}")
    public String addStudentRegistration(@ModelAttribute("registration") StudentRegistration registration,Model model,RedirectAttributes ra,@PathVariable("am") int am){
        List<Student> students=new ArrayList<Student>();
        students.add(studentService.findStudentByAm(am));
        model.addAttribute("students",students);
        List<String> errors=new ArrayList<String>();
        int checkAm=0;
        int checkCourse=0;
        if(registration.getRegistrationId()<=0) {
            errors.add("Invalid id,registrationId should be a positive Integer");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/showCoursesFromSyllabus/"+registration.getAm();
        }
        if(registrationService.findByRegId(registration.getRegId())!= null){
            errors.add("Invalid id,a registration with this id already exists in this department");
            ra.addFlashAttribute("errors",errors);
            return "redirect:/showCoursesFromSyllabus/"+registration.getAm();
        }
        for(Student std: studentService.getAllStudents()) {
            if (registration.getAm() == std.getAm()) {
                checkAm = 1;
            }
        }
        if(checkAm==0){
            errors.add("Invalid Am,it doesn't exist anyone student with this am in this department");
            ra.addFlashAttribute("errors",errors);
            return "redirect:/showCoursesFromSyllabus/"+registration.getAm();
        }
        for(Course course:courseService.getAllCourses()){
            if(course.getCourseId()==registration.getCourseId()){
                checkCourse=1;
            }
        }
        if(checkCourse==0){
            errors.add("Invalid courseId,it doesn't exist any course with this id in this department");
            ra.addFlashAttribute("errors",errors);
            return "redirect:/showCoursesFromSyllabus/"+registration.getAm();
        }
        if(!registration.getEmail().startsWith("cs0")
                || !(registration.isEmailValid(registration.getEmail(),registration.getAm()) == true)) {
            errors.add("Invalid email address,email should have the format username@uoi.gr");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/showCoursesFromSyllabus/"+registration.getAm();
        }
        if(registration.getYearOfStudies()<courseService.findCourseById(registration.getCourseId()).getYear()) {
            errors.add("Year of studies should be an Integer greater than zero");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/showCoursesFromSyllabus/"+registration.getAm();
        }
        if(registration.getSemester()<courseService.findCourseById(registration.getCourseId()).getSemester()||registration.getSemester()>(registration.getYearOfStudies()*2) || registration.getSemester()<((registration.getYearOfStudies()*2)-1)) {
            errors.add("Semester is not valid.Check it according to your Year");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/showCoursesFromSyllabus/"+registration.getAm();
        }
        studentService.findStudentByAm(registration.getAm()).addRegistrationToStudent(registration);
        courseService.findCourseById(registration.getCourseId()).addStudentRegistration(registration);
        registrationService.save(registration);
        return  "redirect:/showCoursesFromSyllabus/"+registration.getAm()+"?success";
    }

    @GetMapping("/deleteRegistrationpage/{am}")
    public String deleteRegistrationpage(@ModelAttribute("registration") StudentRegistration registration,Model model,@ModelAttribute("student")Student student,@PathVariable("am") int am) {
        List<Student> students=new ArrayList<Student>();
        students.add(studentService.findStudentByAm(am));
        model.addAttribute("students",students);
        int sem=0;
        for(Student st:students){
            sem=st.getSemester();
        }
        Student std=studentService.findStudentByAm(am);
        model.addAttribute("student",student);
        List<Course> current=studentService.currentSemester(student.getAm(),sem);
        for(Course c :current){
            if (c==null){
                return "mycourses_deletecourse_student";
            }
        }
        model.addAttribute("currentSemester",current);
        return "mycourses_deletecourse_student";
    }

    @GetMapping("/confirmdeleteRegistration/{am}")
    public String confirmdeleteRegistration(@ModelAttribute("registration")StudentRegistration registration,RedirectAttributes ra,@PathVariable("am") int am){
        List<String> errors = new ArrayList<String>();

        if (registrationService.getRegistrationsByAM(registration.getAm()) == null) {
            String error = "Invalid am,this am doesn't belong to any student in this department";
            errors.add(error);
            ra.addFlashAttribute("errors", errors);
            return "redirect:/deleteRegistrationpage"+"/"+am;
        }
        if (courseService.findCourseById(registration.getCourseId())==null) {
            String error = "Invalid id,this student is not concluded in the course's list of students";
            errors.add(error);
            ra.addFlashAttribute("errors", errors);
            return "redirect:/deleteRegistrationpage"+"/"+am;
        }
        if(registration.getCheck().equals("YES")){

            studentService.findStudentByAm(registration.getAm()).removeRegistrationFromStudent(registration);
            courseService.findCourseById(registration.getCourseId()).removeStudentRegistration(registration);
            int regId=0;
            for(StudentRegistration reg:registrationService.getRegistrationsByAM(am)){
                if(reg.getCourseId()==registration.getCourseId()){
                    regId=reg.getRegId();
                }
            }
            courseService.deleteStudentRegistrationsFromCourse(registration.getCourseId(),registrationService.findByRegId(regId));
            ra.addFlashAttribute("registration",registration);
            return "redirect:/deleteRegistrationpage"+"/"+am+"?success";
        }
        ra.addFlashAttribute("registration",registration);
        return "redirect:/deleteRegistrationpage" +"/"+am;
    }
    @GetMapping("/updateRegistrationpage")
    public String updateStudentRegistrationpage(@ModelAttribute("registration")StudentRegistration registration){
        return "updateRegistrationpage";
    }
    @PostMapping("/updateRegistration")
    public String updateStudentRegistration(@ModelAttribute("registration") StudentRegistration registration,RedirectAttributes ra){
        List<String> errors=new ArrayList<String>();
        int checkAm=0;
        int checkCourse=0;
        if(registration.getRegistrationId()<=0){
            errors.add("Invalid id,registrationId should be a positive Integer");
            ra.addFlashAttribute("errors",errors);
            return "redirect:/updateRegistrationpage";
        }
        if(registrationService.findByRegId(registration.getRegId())== null){
            errors.add("Invalid id,it doesn't exist any registration with this id in this department");
            ra.addFlashAttribute("errors",errors);
            return "redirect:/updateRegistrationpage";
        }
        for(Student std: studentService.getAllStudents()) {
            if (registration.getAm() == std.getAm()) {
                checkAm = 1;
            }
        }
        if(checkAm==0){
            errors.add("Invalid Am,it doesn't exist anyone student with this am in this department");
            ra.addFlashAttribute("errors",errors);
            return "redirect:/updateRegistrationpage";
        }
        for(Course course:courseService.getAllCourses()){
            if(course.getCourseId()==registration.getCourseId()){
                checkCourse=1;
            }
        }
        if(checkCourse==0){
            errors.add("Invalid courseId,it doesn't exist any course with this id in this department");
            ra.addFlashAttribute("errors",errors);
            return "redirect:/updateRegistrationpage";
        }
        if(!registration.getEmail().startsWith("cs0")
                || !(registration.isEmailValid(registration.getEmail(),registration.getAm()) == true)) {
            errors.add("Invalid email address,email should have the format username@uoi.gr");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/updateRegistrationpage";
        }
        if(registration.getYearOfStudies()<courseService.findCourseById(registration.getCourseId()).getYear()) {
            errors.add("Year of studies should be an Integer greater than zero");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/updateRegistrationpage";
        }
        if(registration.getSemester()<courseService.findCourseById(registration.getCourseId()).getSemester()||registration.getSemester()>(registration.getYearOfStudies()*2) || registration.getSemester()<((registration.getYearOfStudies()*2)-1)) {
            errors.add("Semester is not valid.Check it according to your Year");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/updateRegistrationpage";
        }
        studentService.findStudentByAm(registration.getAm()).addRegistrationToStudent(registration);
        registrationService.save(registration);
        studentService.updateStudent(studentService.findStudentByAm(registration.getAm()));
        return  "redirect:/updateRegistrationpage?success";
    }

}
