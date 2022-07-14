package CoursesManagementApp.com.uoi.SoftwareEngineering.Secretariat;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.Instructor;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;


import java.util.List;
import java.util.Map;

public interface SecretariatService  {
    public List<Course> getAllCourses();
    public void addCourse(Course course);
    public void deleteCourse(int courseId);
    public void updateCourse(Course course);
    public void addInstructor(Instructor instructor);
    public void deleteInstructor(String instructorUserName);
    public void updateInstructor(Instructor instructor);
    public void addStudent(Student student);
    public void deleteStudent(int studentAm);
    public void updateStudent(Student student);
    public Map<Integer,Double> showOverallGradesPerStudentInCourse(int courseId);
    public void deleteCourseFromInstructor(int courseId,int instructorId);
    public Instructor findInstructorById(int instructorId);
    public void addCourseToInstructor(int courseId,int instructorId);
    public void ShowStatisticsInCourse(int courseId);
    public void addSecretariat(Secretariat secret);
    public List<Secretariat> getAllSecretariats();
    public void deleteSecret(String userName);
    public Secretariat findSecretByUserName(String userName);
    public void updateSecretariat(Secretariat secret);
    public Secretariat findSecretById(int id);
    public void deleteSecret(int id);

}
