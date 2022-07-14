package CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepo  extends JpaRepository<Instructor,Integer>{
    @Query("select i from Instructor i where i.userName = ?1")
    public Instructor findByUserName(String userName);
    @Query("select i from Instructor i where i.id = ?1")
    public Instructor findById(int id);
}
