package CoursesManagementApp.com.uoi.SoftwareEngineering.Course;

import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface CourseService {

    public List<Course> getAllCourses();
    public List<Course> findCourseByInstructorLogin(String LoginName);

    public void delete(int courseId);

    public void save(Course course);

    public void update(Course course);

    public Course findCourseById(int courseId);

    public  void deleteStudentRegistrationsFromCourse(int courseId,StudentRegistration studentRegistration);

    public void updatecourseWeights(String InstructorLogin,int courseId,double projectWeight,double examWeight);

    public Map<String,Double> calculateCourseStatistics(int courseId);
    public boolean addRegistrationToCourse(int regId,int courseId);

    public List<StudentRegistration> findRegistrationsInCourse( int courseId);

}
