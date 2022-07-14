package CoursesManagementApp.com.uoi.SoftwareEngineering.Secretariat;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseRepository;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.Instructor;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.InstructorRepo;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.InstructorService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.StudentRepository;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;

import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SecretariatServiceImpl implements SecretariatService{
    @Autowired
    private CourseService courseService;
   @Autowired
   private InstructorRepo instructorRepo;
   @Autowired
   private InstructorService instructorService;

   @Autowired
   private CourseRepository courseRepo;

   private BCryptPasswordEncoder passwordEncoder;
   @Autowired
   private StudentRegistrationService registrationService;
   @Autowired
   private StudentRepository  studentsRepo;

   @Autowired
   private  SecretariatRepo secretRepo;

    public List<Course> getAllCourses(){
      return  courseService.getAllCourses();
    }
    public void addCourse(Course course){
        courseService.save(course);
    }

    public void updateCourse(Course course){
        courseService.update(course);
    }

    public void deleteCourse(int courseId){
        courseService.delete(courseId);
    }


    public void addInstructor(Instructor instructor){
        instructorRepo.save(instructor);
    }
    public void updateInstructor(Instructor instructor){
        instructorRepo.save(instructor);
    }

    public void deleteInstructor(String instructorUserName){
        instructorRepo.delete(instructorRepo.findByUserName(instructorUserName));
    }
    public void addStudent(Student student){
        Student std = new Student(student.getName(),
                student.getUserName(),
                passwordEncoder.encode(student.getPassWord()),
                student.getEmail(),student.getAm(),student.getYear(),
                student.getSemester(),student.getAvgGrade());

        studentsRepo.save(std);
    }
    public void deleteStudent(int studentAm){
        studentsRepo.delete(studentsRepo.findByAm(studentAm));
    }
    public void updateStudent(Student student){
        studentsRepo.save(student);
    }
    public Map<Integer,Double> showOverallGradesPerStudentInCourse(int courseId){
        Map<Integer,Double> overallGradesInCourse=new HashMap<Integer,Double>();
        for(StudentRegistration registration:registrationService.findRegistrationByCourse(courseId)){
            overallGradesInCourse.put(registration.getAm(), registrationService.findOverallOfStudentByCourse(registration.getRegAm(), registration.getCourseId()));
            System.out.println(registrationService.findOverallOfStudentByCourse(registration.getRegAm(), registration.getCourseId()));
        }
        return overallGradesInCourse;
    }
    public void deleteCourseFromInstructor(int courseId,int instructorId ){
        for(Instructor instructor: instructorService.getAllInstructors()) {
            if (instructor.getId() == instructorId) {
                instructor.removeCourseFromInstructor(courseService.findCourseById(courseId));
                courseRepo.delete(courseService.findCourseById(courseId));
            }
        }
    }

    public void addCourseToInstructor(int courseId,int instructorId){
        for(Instructor instructor: instructorService.getAllInstructors()) {
            if (instructor.getId() == instructorId) {
                instructor.addCourseToInstructor(courseService.findCourseById(courseId));
                courseService.findCourseById(courseId).setInstructorLogin(instructor.getUserName());
                courseService.save(courseService.findCourseById(courseId));
            }
        }
    }
    public Instructor findInstructorById(int instructorId){
        return instructorService.findInstructorById(instructorId);
    }

    public void ShowStatisticsInCourse(int courseId){

    }

    @Override
    public void addSecretariat(Secretariat secret) {
        secretRepo.save(secret);
    }

    @Override
    public List<Secretariat> getAllSecretariats() {
        return secretRepo.findAll();
    }

    @Override
    public void deleteSecret(String userName) {
        secretRepo.deleteByUserName(userName);
    }

    @Override
    public Secretariat findSecretByUserName(String userName) {
        return  secretRepo.findByUserName(userName);
    }
    public void updateSecretariat(Secretariat secret){
        secretRepo.save(secret);
    }

    @Override
    public Secretariat findSecretById(int id) {
        return secretRepo.findById(id).orElse(null);
    }
    public void deleteSecret(int id){
        secretRepo.deleteById(id);
    }
}
