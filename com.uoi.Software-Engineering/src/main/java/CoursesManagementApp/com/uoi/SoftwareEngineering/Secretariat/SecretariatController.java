package CoursesManagementApp.com.uoi.SoftwareEngineering.Secretariat;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.Instructor;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.InstructorService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.PersonalStudentService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.AmValidation;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.EmailValidation;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.PasswordValidation;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student.StudentUsernameValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class SecretariatController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentRegistrationService registrationService;
    @Autowired
    private SecretariatService secretService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private PersonalStudentService studentService;

    @DeleteMapping("/delete/{id}/{instructorId}")
    public void deleteCourseFromInstructor(@PathVariable("id") int courseId, @PathVariable("instructorId") int instructorId) {
        secretService.deleteCourseFromInstructor(courseId,instructorId);
    }
    @PostMapping("/addCourseToInstructor/{id}/{instructorId}")
    public void addCourseToInstructor(@PathVariable("id") int courseId, @PathVariable("instructorId") int instructorId) {
        secretService.addCourseToInstructor(courseId,instructorId);
    }


    @GetMapping("/instructors")
    public String getInstructors(Model model) {
        List<Instructor> instructors=instructorService.getAllInstructors();
        model.addAttribute("instructors",instructors);
        return "instructors";
    }
    @GetMapping("/secrets")
    public String getSecrets(Model model) {
        List<Secretariat> secrets=secretService.getAllSecretariats();
        model.addAttribute("secrets",secrets);
        return "secrets";
    }

    @DeleteMapping("/deleteCourse/{id}")
    public void deleteCourse(@PathVariable int courseId) {
        courseService.delete(courseId);
    }

    @GetMapping("/ByLogin/{LoginName}")
    public List<Course> findCourseByInstructorLogin(@PathVariable String LoginName) {
        return courseService.findCourseByInstructorLogin(LoginName);
    }


    @PutMapping("/updateStudentRegistration")
    public void updateStudentRegistration(@RequestBody StudentRegistration student) {
        registrationService.update(student);
    }

    @DeleteMapping(value = "/deleteStudentRegistration/{id}")
    public void deleteStudentRegistration(@PathVariable int studentId) {
        registrationService.delete(studentId);
    }

    @GetMapping("/studentRegistrations/{id}")
    public List<StudentRegistration> findRegistrationByCourse(@PathVariable int courseId) {
        return registrationService.findRegistrationByCourse(courseId);
    }

    @GetMapping("/registrations")
    public String getAllRegistrations(Model model) {
        List<StudentRegistration> listRegistrations=registrationService.getAllRegistrations();
        model.addAttribute("registrations",listRegistrations);
        return "registrations";
    }

    @GetMapping("/students")
    public String getAllStudents(Model model) {

        for(Student student :studentService.getAllStudents()){
            studentService.calculateAvgGradeOfAllCoursesPassed(student.getAm());
        }
        List<Student> students=studentService.getAllStudents();
        model.addAttribute("students",students);
        return "students";
    }
    @PostMapping("/saveStudent")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }
    @PutMapping("/updateStudent")
    public void updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
    }
    @DeleteMapping("/deleteStudent/{id}")
    public void deleteStudent(@PathVariable int studentId) {
        studentService.deleteStudent(studentId);
    }

    @GetMapping("/overallPerCourse/{courseId}")
    public String showOverallPerCourse(@PathVariable("courseId") int courseId,Model model){
        ArrayList overalls=new ArrayList<String>();
        for(Integer overall :secretService.showOverallGradesPerStudentInCourse(courseId).keySet()){
            overalls.add(overall +": " +secretService.showOverallGradesPerStudentInCourse(courseId).get(overall));
        }
        model.addAttribute("overalls",overalls);
        return "studentOveralls";
    }
    @GetMapping("/StatisticsPerCourse/{courseId}")
    public String showStatisticsPerCourse(@PathVariable("courseId") int courseId,Model model){
        List<String> statistics=new ArrayList<String>();

        for(String stat:courseService.calculateCourseStatistics(courseId).keySet()){
            statistics.add(stat +": "+courseService.calculateCourseStatistics(courseId).get(stat));
        }
        model.addAttribute("statistics",statistics);
        return "courseStatistics";

    }
    @PutMapping("/addRegistrationToCourse/{regId}/{courseId}")
    public void addRegistrationToCourse(@PathVariable("regId") int regId,@PathVariable("courseId") int courseId){
        courseService.addRegistrationToCourse(regId,courseId);
    }
    @PostMapping("/saveSecret")
    public String saveSecret(@ModelAttribute("secret") Secretariat secret, RedirectAttributes ra) {
        List<String> errors=new ArrayList<String>();
        if(secret.getId()==0 || secret.getUserName()==null || secret.getEmail()==null ||secret.getPassWord()==null ){
            errors.add("Missing fields,fill all the required fields");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/regpageSecretariat";
        }
        if(secretService.findSecretById(secret.getId())!=null){
            errors.add("Invalid id,this id is already used by another secretariat");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/regpageSecretariat";
        }
        if(secret.getId()<=0){
            errors.add("Invalid id,secretary's id must be positive");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/regpageSecretariat";
        }
        if(!secret.getUserName().startsWith("css")){
            errors.add("Invalid username,username should start with css");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/regpageSecretariat";
        }
        if(!(secret.isEmailValid(secret.getEmail(),secret.getUserName()) == true)){
            errors.add(" Invalid email address,email should have the format username@uoi.gr");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/regpageSecretariat";
        }
        if(!(secret.getPassWord().length()>=8) || secret.checkPassword(secret.getPassWord())==false){
            errors.add("Password has minimum length 8 and must contain at least one Uppercase,one lowercase,one number and one special character");
            ra.addFlashAttribute("errors",errors);
            return "redirect:/regpageSecretariat";
        }
        if(secret.getFloor().length()!=1 || secret.getFloor().compareTo("A")<0||secret.getFloor().compareTo("Z")>0){
            errors.add("Floor must a capital letter between A and Z");
            ra.addFlashAttribute("errors",errors);
            return "redirect:/regpageSecretariat";
        }
        if(!(secret.getOffice().startsWith(secret.getFloor())||secret.getOffice().startsWith(secret.getFloor()) && (secret.getOffice().split(secret.getFloor())[1].contains("[a-zA-Z]+")))){
            errors.add("Office must start with Floor's capital letter and be continued by an integer");
            ra.addFlashAttribute("errors",errors);
            return "redirect:/regpageSecretariat";
        }

        secretService.addSecretariat(secret);
        return "redirect:/regpageSecretariat?success";
    }
    @GetMapping("/regpageSecretariat")
    public String showRegisterSecretForm( @ModelAttribute("secret") Secretariat secret){

        return "register_secretary";
    }

    @GetMapping("/loginpage")
    public String loginpage(){
        return "login_page";
    }
    @GetMapping("/loginSecret")
    public String loginSecret(@ModelAttribute("secret") Secretariat secret){
        return "login_page_secretary";
    }
    @GetMapping("/menuSecret")
    public String showSecretMenu(@ModelAttribute("secret") Secretariat secret,RedirectAttributes ra){
        List<String> errors=new ArrayList<String>();
        int checkusername=0;
        int checkpassword=0;
        String loginUser="";
        for(Secretariat sec: secretService.getAllSecretariats()) {
            if (sec.getUserName().equals(secret.getUserName())) {
                checkusername = 1;
                if(sec.getPassWord().equals(secret.getPassWord())){
                    checkpassword=1;
                    loginUser=sec.getUserName();
                }
            }
        }
        if(checkusername==0){
            String error="Invalid username,try Again";
            errors.add(error);
            ra.addFlashAttribute("errors",errors);
            return "redirect:/loginSecret";
        }
        if(checkpassword==0){
            String error="Invalid password,try again";
            errors.add(error);
            ra.addFlashAttribute("errors",errors);
            return "redirect:/loginSecret";
        }
        ra.addFlashAttribute("secret",secret);
        ra.addFlashAttribute("loginUser",loginUser);
        return "redirect:/home_page_secret/"+loginUser;
    }

    @GetMapping("/home_page_secret/{userName}")
    public String showSecretprofile(@ModelAttribute("secret")Secretariat secret,Model model){
       if(secretService.findSecretByUserName(secret.getUserName())!=null) {
           List<Secretariat> secrets = new ArrayList<Secretariat>();
           secrets.add(secretService.findSecretByUserName(secret.getUserName()));
           model.addAttribute("secrets", secrets);
           return "home_page_secretary";
       }
       return "/loginpage";
    }
    @GetMapping("/addCoursepage")
    public String addCoursepage(@ModelAttribute("course") Course course){
        return "addCourse";
    }

    @PostMapping("/addCourse/{userName}")
    public String addCourse(@ModelAttribute("course") Course course,RedirectAttributes ra,@PathVariable("userName") String userName){
        List<String> errors=new ArrayList<String>();
        if(course.getCourseId()<=0){
            String error="Invalid courseId,courseId must be a positive Integer";
            errors.add(error);
            ra.addFlashAttribute("errors",errors);
            return "redirect:/EditSyllabusSecret/"+userName;
        }
        if(courseService.findCourseById(course.getCourseId())!=null){
            String error="Invalid courseId,this courseId is already used!";
            errors.add(error);
            ra.addFlashAttribute("errors",errors);
            return "redirect:/EditSyllabusSecret/"+userName;
        }
        if(course.getYear()<1){
            String error="Invalid year,year must be a positive integer!";
            errors.add(error);
            ra.addFlashAttribute("errors",errors);
            return "redirect:/EditSyllabusSecret/"+userName;
        }
        if(course.getSemester()>(course.getYear()*2) || course.getSemester()<((course.getYear()*2)-1)) {
            errors.add("Semester is not valid.Check it according to your Year");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/EditSyllabusSecret/"+userName;
        }
        if(course.getHours()>=7 || course.getHours()<1) {
            errors.add("Course's teaching hours should be at least 1 and at most 7");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/EditSyllabusSecret/"+userName;
        }
        if(course.getProjectPercentage()>1.0 || course.getProjectPercentage()<0.0) {
            errors.add("Project weight should be a float between 0 and 1.0");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/EditSyllabusSecret/"+userName;
        }
        if(instructorService.getAllInstructors().contains(instructorService.findByInstructorUserName(course.getInstructorLogin()))==false) {
            errors.add("this instructor's name does not belong to any instructor of our department");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/EditSyllabusSecret/"+userName;
        }
        course.setExamPercentage(1- course.getProjectPercentage());
        courseService.save(course);
        secretService.addCourseToInstructor(course.getCourseId(),instructorService.findByInstructorUserName(course.getInstructorLogin()).getId());
        instructorService.updateInstructor(instructorService.findByInstructorUserName(course.getInstructorLogin()));
        return "redirect:/EditSyllabusSecret/"+userName+"?success";
    }
    @GetMapping("/deleteCourse/{userName}")
    public String deleteCourse(@ModelAttribute("course") Course course,RedirectAttributes ra,@PathVariable("userName") String userName){
        List<String> errors=new ArrayList<String>();
        if(course.getCourseId()<=0){
            String error="Invalid courseId,courseId must be a positive Integer";
            errors.add(error);
            ra.addFlashAttribute("errors",errors);
            return "redirect:/EditSyllabusSecret/"+userName;
        }
        if(courseService.findCourseById(course.getCourseId())==null){
            String error="Invalid courseId,this courseId is not already used!";
            errors.add(error);
            ra.addFlashAttribute("errors",errors);
            return "redirect:/EditSyllabusSecret/"+userName;
        }
        for(StudentRegistration reg : courseService.findRegistrationsInCourse(course.getCourseId())){
            registrationService.delete(reg.getRegistrationId());
        }
        courseService.delete(course.getCourseId());
        return "redirect:/EditSyllabusSecret/"+userName+"?success";
    }
    @GetMapping("/updateCoursepage")
    public String updateCoursepage(@ModelAttribute("course") Course course){
        return "updateCourse";
    }
    @PostMapping("/updateCourse")
    public String updateCourse(@ModelAttribute("course") Course course,RedirectAttributes ra){
        List<String> errors=new ArrayList<String>();
        boolean validCourse=false;
        if(course.getCourseId()<=0){
            String error="Invalid courseId,courseId must be a positive Integer";
            errors.add(error);
            ra.addFlashAttribute("errors",errors);
            return "redirect:/updateCoursepage";
        }
        if(courseService.findCourseById(course.getCourseId())==null){
            String error="Invalid courseId,this courseId doesn't exist!";
            errors.add(error);
            ra.addFlashAttribute("errors",errors);
            return "redirect:/updateCoursepage";
        }
        for(Course c1:instructorService.findInstructorById(course.getInstructorId()).getMyCourses()){
            if(c1.getCourseId()== course.getCourseId()){
                validCourse=true;
            }
        }
        if(!validCourse){
            String error="Invalid courseId,you are not authorized to edit this course.";
            errors.add(error);
            ra.addFlashAttribute("errors",errors);
            return "redirect:/updateCoursepage";
        }
        if(course.getYear()<1){
            String error="Invalid year,year must be a positive integer!";
            errors.add(error);
            ra.addFlashAttribute("errors",errors);
            return "redirect:/updateCoursepage";
        }
        if(course.getSemester()>(course.getYear()*2) || course.getSemester()<((course.getYear()*2)-1)) {
            errors.add("Semester is not valid.Check it according to your Year");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/updateCoursepage";
        }
        if(instructorService.getAllInstructors().contains(instructorService.findByInstructorUserName(course.getInstructorLogin()))==false) {
            errors.add("this instructor's name does not belong to any instructor of our department");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/updateCoursepage";
        }
        courseService.update(course);
        return "redirect:/updateCoursepage?success";
    }
    @GetMapping("/courses")
    public String getAllCourses(Model model) {
        List<Course> courses=courseService.getAllCourses();
        model.addAttribute("courses",courses);
        return "courses";
    }

    @GetMapping("/updateInstructorpage")
    public String updateInstructorPage(@ModelAttribute("instructor") Instructor instructor){
        return "updateInstructor";
    }
    @PostMapping("/updateInstructor")
    public String updateInstructor(@ModelAttribute("instructor") Instructor instructor,RedirectAttributes ra){
        List<String> errors = new ArrayList<String>();
            if (instructorService.findInstructorById(instructor.getId()) == null) {
                String error = "Invalid instructorId,this id doesn't belong to any instructor in this department";
                errors.add(error);
                ra.addFlashAttribute("errors", errors);
                return "redirect:/updateInstructorpage";
            }
            if(instructor.getId()<=0){
                errors.add("Invalid id,instructor's id must be positive");
                ra.addFlashAttribute("errors", errors);
                return "redirect:/updateInstructorpage";
            }
            if(!instructor.getUserName().startsWith("cse")){
                errors.add("Invalid username,username should start with cse");
                ra.addFlashAttribute("errors", errors);
                return "redirect:/updateInstructorpage";
            }
            if(!(instructor.isEmailValid(instructor.getEmail(),instructor.getUserName()) == true)){
                errors.add(" Invalid email address,email should have the format username@uoi.gr");
                ra.addFlashAttribute("errors", errors);
                return "redirect:/updateInstructorpage";
            }
            if(!(instructor.getPassWord().length()>=8) || instructor.checkPassword(instructor.getPassWord())==false){
                errors.add("Password has minimum length 8 and must contain at least one Uppercase,one lowercase,one number and one special character");
                ra.addFlashAttribute("errors",errors);
                return "redirect:/updateInstructorpage";
            }
            instructorService.updateInstructor(instructor);
            instructor.setCheck("-");
            return "redirect:/updateInstructorpage?success";


    }

    @GetMapping("/deleteInstructorpage")
    public String deleteInstructorpage(@ModelAttribute("instructor") Instructor instructor) {
        return "deleteInstructor";
    }


    @GetMapping("/confirmdeleteInstructor/{userName}")
    public String confirmdeleteInstructor(@ModelAttribute("instructor") Instructor instructor,@PathVariable("userName")String userName,BindingResult result,RedirectAttributes ra){
        List<String> errors = new ArrayList<String>();

        if (instructorService.findInstructorById(instructor.getId()) == null) {
            String error = "Invalid instructorId,this id doesn't belong to any instructor in this department";
            errors.add(error);
            ra.addFlashAttribute("errors", errors);
            return "redirect:/showInstructors/"+userName;
        }
        if(instructor.getCheck().equals("YES")){
            instructorService.deleteInstructor(instructor.getId());
            ra.addFlashAttribute("instructor",instructor);
            return "redirect:/showInstructors/"+userName+"?success";
        }
        ra.addFlashAttribute("instructor",instructor);
        return   "redirect:/showInstructors/"+userName;
    }
    @GetMapping("/deleteStudentpage")
    public String deleteStudentpage(@ModelAttribute("student") Student student) {
        return "deleteStudent";
    }

    @GetMapping("/confirmdeleteStudent/{userName}")
    public String confirmdeleteStudent(@ModelAttribute("student")Student student,RedirectAttributes ra,@PathVariable("userName")String userName){
        List<String> errors = new ArrayList<String>();
        if (studentService.findStudentByAm(student.getAm()) == null) {
            String error = "Invalid am,this am doesn't belong to any student in this department";
            errors.add(error);
            ra.addFlashAttribute("errors", errors);
            return "redirect:/showStudents/"+userName;
        }
        if(student.getCheck().equals("YES")){
            studentService.deleteStudent(student.getAm());
            ra.addFlashAttribute("student",student);
            return "redirect:/showStudents/"+userName+"?success";
        }
        ra.addFlashAttribute("student",student);
        return "redirect:/showStudents/"+userName;
    }
    @GetMapping("/updateStudentpage")
    public String updateStudentpage(@ModelAttribute("student") Student student){
        return "updateStudent";
    }
    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute("student") Student student,RedirectAttributes ra){
        List<String> errors=new ArrayList<String>();
        if(student.getAm()<=0){
            errors.add("Invalid Am,Am should be a positive Integer");
            ra.addFlashAttribute("errors",errors);
            return "redirect:/updateStudentpage";
        }
        if (studentService.findStudentByAm(student.getAm()) == null) {
            String error = "Invalid am,this am doesn't belong to any student in this department";
            errors.add(error);
            ra.addFlashAttribute("errors", errors);
            return "redirect:/updateStudentpage";
        }
        if(!student.getUserName().equals("cs0"+student.getAm())){
            errors.add("Invalid username,username should be in the format cs0 and Am");
            ra.addFlashAttribute("errors",errors);
            return "redirect:/updateStudentpage";
        }
        if(!student.getEmail().startsWith("cs0")
                || !(student.isEmailValid(student.getEmail(), student.getUserName()) == true)) {
            errors.add("Invalid email address,email should have the format username@uoi.gr");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/updateStudentpage";
        }
        if(!(student.getPassWord().length()>=8) || student.checkPassword(student.getPassWord())==false){
            errors.add("Password has minimum length 8 and must contain at least one Uppercase,one lowercase,one number and one special character");
            ra.addFlashAttribute("errors",errors);
            return "redirect:/updateStudentpage";
        }
        if(student.getYear()<=0) {
            errors.add("Year of studies should be an Integer greater than zero");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/updateStudentpage";
        }
        if(student.getSemester()>(student.getYear()*2) || student.getSemester()<((student.getYear()*2)-1)) {
            errors.add("Semester is not valid.Check it according to your Year");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/updateStudentpage";
        }
        if(student.getAvgGrade()<5.0 || student.getAvgGrade()>10.0) {
            errors.add("Student's AvgGrade should be between 5.0 and 10.0");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/updateStudentpage";
        }
        student.setCheck("-");
        studentService.saveStudent(student);
        return "redirect:/updateStudentpage?success";
    }
    @GetMapping("/deleteSecretpage")
    public String deleteSecretpage(@ModelAttribute("secret") Secretariat secretariat) {
        return "deleteSecret";
    }


    @GetMapping("/confirmdeleteSecret/{userName}")
    public String confirmdeleteSecret(@ModelAttribute("secret") Secretariat secret,RedirectAttributes ra,@PathVariable("userName") String userName){
        List<String> errors = new ArrayList<String>();

        if (secretService.findSecretById(secret.getId()) == null) {
            String error = "Invalid secretId,this id doesn't belong to any secretariat in this department";
            errors.add(error);
            ra.addFlashAttribute("errors", errors);
            return "redirect:/showSecrets/"+userName;
        }
        if(secret.getCheck().equals("YES")){
            secretService.deleteSecret(secret.getId());
            ra.addFlashAttribute("secret",secret);
            return "redirect:/showSecrets/"+userName+"?success";
        }
        ra.addFlashAttribute("secret",secret);
        return "redirect:/showSecrets/"+userName;
    }

    @GetMapping("/updateSecretpage")
    public String updateSecretpage(@ModelAttribute("secret") Secretariat secret){
        return "updatesecret";
    }
    @PostMapping("/updateSecret")
    public String updateSecret(@ModelAttribute("secret") Secretariat secret,RedirectAttributes ra){
        List<String> errors=new ArrayList<String>();
        if (secretService.findSecretById(secret.getId()) == null) {
            String error = "Invalid secretId,this id doesn't belong to any secretariat in this department";
            errors.add(error);
            ra.addFlashAttribute("errors", errors);
            return "redirect:/updateSecretpage";
        }
        if(secret.getId()<=0){
            errors.add("Invalid id,secret's id must be positive");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/updateSecretpage";
        }
        if(!secret.getUserName().startsWith("css")){
            errors.add("Invalid username,username should start with css");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/updateSecretpage";
        }
        if(!(secret.isEmailValid(secret.getEmail(),secret.getUserName()) == true)){
            errors.add(" Invalid email address,email should have the format username@uoi.gr");
            ra.addFlashAttribute("errors", errors);
            return "redirect:/updateSecretpage";
        }
        if(!(secret.getPassWord().length()>=8) || secret.checkPassword(secret.getPassWord())==false){
            errors.add("Password has minimum length 8 and must contain at least one Uppercase,one lowercase,one number and one special character");
            ra.addFlashAttribute("errors",errors);
            return "redirect:/updateSecretpage";
        }
        secretService.updateSecretariat(secret);
        return "redirect:/updateSecretpage?success";
    }

    @GetMapping("/SyllabusSecret/{userName}")
    public String showCoursesFromSyllabus(Model model,@ModelAttribute("secret") Secretariat secret) {
        List<Course> allCourses=courseService.getAllCourses();
        model.addAttribute("allCourses",allCourses);
        return "syllabus_currentsemester_secretary";
    }

    @GetMapping("/showInstructors/{userName}")
    public String showUsers(Model model,@ModelAttribute("secret") Secretariat secret,@ModelAttribute("instructor") Instructor instructor) {
        List<Instructor> instructors=instructorService.getAllInstructors();
        model.addAttribute("instructors",instructors);
        List<Student> students=studentService.getAllStudents();
        model.addAttribute("students",students);
        List<Secretariat> secrets=secretService.getAllSecretariats();
        model.addAttribute("secrets",secrets);
        return "users_instructors_secretary";
    }

    @GetMapping("/showStudents/{userName}")
    public String showStudents(Model model,@ModelAttribute("secret") Secretariat secret,@ModelAttribute("student") Student student) {
        List<Instructor> instructors=instructorService.getAllInstructors();
        model.addAttribute("instructors",instructors);
        List<Student> students=studentService.getAllStudents();
        model.addAttribute("students",students);
        List<Secretariat> secrets=secretService.getAllSecretariats();
        model.addAttribute("secrets",secrets);
        return "users_students_secretary";
    }

    @GetMapping("/showSecrets/{userName}")
    public String showSecrets(Model model,@ModelAttribute("secret") Secretariat secret) {
        List<Instructor> instructors=instructorService.getAllInstructors();
        model.addAttribute("instructors",instructors);
        List<Student> students=studentService.getAllStudents();
        model.addAttribute("students",students);
        List<Secretariat> secrets=secretService.getAllSecretariats();
        model.addAttribute("secrets",secrets);
        return "users_secretaries_secretary";
    }

    @GetMapping("/EditSyllabusSecret/{userName}")
    public String editCoursesFromSyllabus(Model model,@ModelAttribute("secret") Secretariat secret,@ModelAttribute("course") Course course) {
        List<Course> allCourses=courseService.getAllCourses();
        model.addAttribute("allCourses",allCourses);
        return "syllabus_edit_secretary";
    }
}