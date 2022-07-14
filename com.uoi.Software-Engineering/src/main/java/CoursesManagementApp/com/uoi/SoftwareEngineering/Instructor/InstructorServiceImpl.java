package CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationRepository;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class InstructorServiceImpl  implements InstructorService{
    @Autowired
    private CourseService courseService;
    @Autowired
    private InstructorRepo instructorRepo;

    public StudentRegistrationService getRegistrationService() {
        return registrationService;
    }
    public InstructorServiceImpl(InstructorRepo instructorRepo){
        this.instructorRepo=instructorRepo;
    }

    public void setRegistrationService(StudentRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Autowired
    private StudentRegistrationService registrationService;
    @Autowired
    private StudentRegistrationRepository registrationsRepo;

    public List<Course> showMyCourses(String instructorLogin){
      return  courseService.findCourseByInstructorLogin(instructorLogin);
    }
    public List<StudentRegistration> showRegistrationsInMyCourses(String instructorLogin,int courseId){
        List<StudentRegistration> registrationList=new ArrayList<StudentRegistration>();
                for(StudentRegistration registration: courseService.findRegistrationsInCourse(courseId)) {
                    registrationList.add(registration);
                }
        return  registrationList;
    }
    public void editWeightsOfMyCourse(String instructorLogin,int courseId,double projectWeight,double examWeight){
        if(checkInstructorPermission(instructorLogin,courseId)==true) {
           courseService.updatecourseWeights(instructorLogin,courseId,projectWeight,examWeight);
        }
    }
    public void deleteStudentRegistrationsInTheCourse(String instructorLogin,int courseId,int registrationId){
       if(checkInstructorPermission(instructorLogin,courseId)==true){
            for(StudentRegistration registration:registrationService.findRegistrationByCourse(courseId)){
                if(registration.getRegistrationId()==registrationId){
                    courseService.deleteStudentRegistrationsFromCourse(courseId,registration);
                }
            }
        }
    }
    public boolean checkInstructorPermission(String instructorLogin,int courseId){
        if(!(showMyCourses(instructorLogin).contains(courseService.findCourseById(courseId)))){
            throw new IllegalCallerException("Dear instructor \"+instructorLogin+\", you are not allowed to edit this course");
        }
        return true;
    }
    public Map<Integer,Double> showOverallGradesPerStudentInCourse(String instructorLogin,int courseId){
        if(checkInstructorPermission(instructorLogin,courseId)==true) {
            Map<Integer, Double> overallGradesInCourse = new HashMap<Integer, Double>();
            for (StudentRegistration registration : courseService.findRegistrationsInCourse(courseId)) {
                overallGradesInCourse.put(registration.getAm(), registrationService.findOverallOfStudentByCourse(registration.getRegistrationId(),courseId));
            }
            return overallGradesInCourse;
        }
        return null;
    }
    public Instructor findByInstructorUserName(String instructorUserName){
        return instructorRepo.findByUserName(instructorUserName);
    }
    public Instructor addInstructor(Instructor instructor){
        return instructorRepo.save(instructor);
    }

    public List<Instructor> getAllInstructors(){
        return instructorRepo.findAll();
    }
    @Transactional(readOnly=true)
    public Instructor findInstructorById(int instructorId){
        return instructorRepo.findById(instructorId);
    }
    public void updateInstructor(Instructor instructor){
        instructorRepo.save(instructor);
    }
    public void deleteInstructor(int instructorId){
        instructorRepo.deleteById(instructorId);
    }

    @Override
    public void registerGradesToStudent(int registrationId,int courseId,float examGrade,float projectGrade) {
        StudentRegistration registration=registrationsRepo.findByRegId(registrationId);
        if(courseId==registration.getCourseId()){
            registration.setExamGrade(examGrade);
            registration.setProjectGrade(projectGrade);
            registrationsRepo.save(registration);
        }
    }

    public void deleteRegistrationPerCourse(int courseId,int registrationId){
        if(registrationsRepo.findByRegId(registrationId).getCourseId()==courseId){
            registrationService.delete(registrationId);
        }
    }
}