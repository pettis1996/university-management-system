package CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface StudentRegistrationRepository  extends JpaRepository<StudentRegistration,Integer>{
    @Query("select s from StudentRegistration s where s.courseId = ?1")
    public List<StudentRegistration> findByCourseId(int courseId);
    @Query("select s from StudentRegistration s where s.regId = ?1")
    public StudentRegistration findByRegId(int regId);
    @Query("select s from StudentRegistration s where s.regAm = ?1")
    public List<StudentRegistration> findByRegAm(int am);


    @Transactional
    @Modifying
    @Query("delete from StudentRegistration s where s.regId = ?1")
    public void deleteByRegId(int registrationId);

    @Transactional
    @Modifying
    @Query("delete from StudentRegistration s where s.regAm = ?1")
    public void deleteByRegAm(int regAm);
}
