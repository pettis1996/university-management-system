package CoursesManagementApp.com.uoi.SoftwareEngineering.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query("select s from Student s where s.am = ?1")
    public Student findByAm(int am);
    public Student findByEmail(String email);
    @Transactional
    @Modifying
    @Query("delete from Student s where s.am = ?1")
    public void deleteByAm(int am);

}
