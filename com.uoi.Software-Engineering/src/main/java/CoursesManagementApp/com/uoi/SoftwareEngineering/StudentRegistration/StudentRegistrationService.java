package CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface StudentRegistrationService {

    public List<StudentRegistration> findRegistrationByCourse(int courseId);

    public void delete(int registrationId);

    public void save(StudentRegistration student);

    public void update(StudentRegistration student);

    public List<StudentRegistration> getAllRegistrations();

    public List<StudentRegistration> getRegistrationsByAM(int am);

    public double findOverallOfStudentByCourse(int studentId,int courseId);

    public void deleteByRegAm(int regAm);
    public StudentRegistration findByRegId(int regId);
}
