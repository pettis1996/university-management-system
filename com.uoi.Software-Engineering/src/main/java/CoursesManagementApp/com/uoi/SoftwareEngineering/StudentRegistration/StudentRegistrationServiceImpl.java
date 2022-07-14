package CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration;


import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentRegistrationServiceImpl implements StudentRegistrationService  {
    @Autowired
    private StudentRegistrationRepository registrationsRepo;

    @Autowired
    private CourseRepository courseRepo;

    public List<StudentRegistration> findRegistrationByCourse(int courseId){
        return registrationsRepo.findByCourseId(courseId);
    }

    public void delete(int registrationId){
        registrationsRepo.deleteByRegId(registrationId);
    }
    public void save(StudentRegistration student){
        registrationsRepo.save(student);
    }

    public void update(StudentRegistration registration){
        registrationsRepo.save(registration);
        System.out.println("Updated :"+ registration.getCourseId() + " "+ registration.getRegistrationId());;
    }
    public List<StudentRegistration> getAllRegistrations(){return registrationsRepo.findAll();}

    @Override
    public List<StudentRegistration> getRegistrationsByAM(int am) {
        return registrationsRepo.findByRegAm(am);
    }


    public double findOverallOfStudentByCourse(int studentAm,int courseId){
        List<StudentRegistration> registrations=registrationsRepo.findByRegAm(studentAm);
        System.out.println(registrations);
        Course c=courseRepo.findByCourseId(courseId);
        float overall;
        if(c!=null) {
            for (StudentRegistration reg : registrations) {
                if (reg.getCourseId() == courseId) {
                    overall = (float) ((reg.getExamGrade() * c.getExamPercentage()) + (reg.getProjectGrade() * c.getProjectPercentage()));
                    reg.setOverall(overall);
                    System.out.println(c.getExamPercentage());
                    System.out.println(overall);
                    update(reg);
                    return overall;
                }
            }
        }
        return 0;
    }

    @Override
    public void deleteByRegAm(int regAm) {
        registrationsRepo.deleteByRegAm(regAm);
    }

    @Override
    public StudentRegistration findByRegId(int regId) {
       return registrationsRepo.findByRegId(regId);
    }


}