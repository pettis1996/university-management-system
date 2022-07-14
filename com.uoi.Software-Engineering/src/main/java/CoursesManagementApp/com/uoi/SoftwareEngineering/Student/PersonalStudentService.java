package CoursesManagementApp.com.uoi.SoftwareEngineering.Student;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface PersonalStudentService extends UserDetailsService {
    //courseName as key,overall as value
    public List<Course> findPassedCourses(int am);
    public List<Course> currentSemester(int am, int semester);
    public double calculateAvgGradeOfAllCoursesPassed(int am);
    public Student findStudentByAm(int am);
    public void deleteStudent(int am);
    public Student saveStudent(Student student);
    public void updateStudent(Student student);
    public List<Student> getAllStudents();
}
