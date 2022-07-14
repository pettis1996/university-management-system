package CoursesManagementApp.com.uoi.SoftwareEngineering.Student;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class PersonalStudentServiceImpl implements PersonalStudentService {
    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private StudentRegistrationService registrationsService;

    @Autowired
    private CourseService courseService;

    public List<Course> findPassedCourses(int am){
        List<Course> passedCourses=new ArrayList<Course>();
        for(StudentRegistration registration : registrationsService.getRegistrationsByAM(am)){
            if(registrationsService.findOverallOfStudentByCourse(registration.getAm(), registration.getCourseId())>=5){
                passedCourses.add(courseService.findCourseById(registration.getCourseId()));
                studentRepo.findByAm(am).setCoursePassed(passedCourses.size());
                studentRepo.save(studentRepo.findByAm(am));
            }
        }
        return passedCourses;
    }

    @Override
    public List<Course> currentSemester(int am,int semester) {
        List<Course> currentCourses=new ArrayList<Course>();
        for(StudentRegistration registration : registrationsService.getRegistrationsByAM(am)){
            if(registration.getSemester()==semester){
                currentCourses.add(courseService.findCourseById(registration.getCourseId()));
            }
        }
        return currentCourses;
    }
    @Override
    public double calculateAvgGradeOfAllCoursesPassed(int am) {
         double avgGrade=0;
         int coursesPassed=0;
        for(StudentRegistration registration : registrationsService.getRegistrationsByAM(am)) {
            if (registrationsService.findOverallOfStudentByCourse(registration.getAm(), registration.getCourseId()) >= 5) {
                coursesPassed++;
                avgGrade += registrationsService.findOverallOfStudentByCourse(registration.getAm(), registration.getCourseId());
            }
        }
        if(findPassedCourses(am).size()!=0){
            avgGrade = (avgGrade/findPassedCourses(am).size());

        }
        Student student=findStudentByAm(am);

        updateStudentsGrade(am, avgGrade,coursesPassed);
        updateStudent(student);
        return avgGrade;
    }
    @Override
    public Student findStudentByAm(int am){
        return studentRepo.findByAm(am);
    }

    public boolean updateStudentsGrade(int am,double avgGrade,int coursesPassed){
        Student student=findStudentByAm(am);
        if(student!=null){
            student.setAvgGrade(avgGrade);
            student.setCoursePassed(coursesPassed);
            return true;
        }
        return false;
    }
    public void deleteStudent(int am){
        studentRepo.deleteByAm(am);
    }
    public Student saveStudent(Student student){
         return studentRepo.save(student);
    }
    public void updateStudent(Student student){
        studentRepo.save(student);
    }
    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student=studentRepo.findByEmail(username);
        if(student==null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(student.getEmail(), student.getPassWord(),null);
    }
}
