package CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import CoursesManagementApp.com.uoi.SoftwareEngineering.User.LoginInterface;



import java.util.List;
import java.util.Map;

public interface InstructorService  {
    public Instructor findByInstructorUserName(String instructorUserName);
    public List<Course> showMyCourses(String instructorLogin);
    /*Map with course name as key and list of StudentRegistrations per Course as value*/
    public List<StudentRegistration> showRegistrationsInMyCourses(String instructorLogin,int courseId);
    public void editWeightsOfMyCourse(String instructorLogin,int courseId,double projectWeight,double examWeight);
    public void deleteRegistrationPerCourse(int courseId,int registrationId);
    public Map<Integer,Double> showOverallGradesPerStudentInCourse(String instructorLogin,int courseId);
    public Instructor addInstructor(Instructor instructor);
    public List<Instructor> getAllInstructors();
    public Instructor findInstructorById(int instructorId);
    public void updateInstructor(Instructor instructor);
    public void deleteInstructor(int instructorId);
    public void registerGradesToStudent(int registrationId,int courseId,float examGrade,float projectGrade);
}