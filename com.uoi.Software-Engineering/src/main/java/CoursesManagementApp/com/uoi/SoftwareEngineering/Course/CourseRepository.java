package CoursesManagementApp.com.uoi.SoftwareEngineering.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRepository  extends JpaRepository<Course,Integer>{
    @Query("select c from Course c where c.instructorLogin = ?1")
    public List<Course> findByInstructorLogin(String LoginName);
    public Course findByCourseId(int courseId);
    @Transactional
    @Modifying
    @Query("delete from Course c where c.courseId = ?1")
    public void deleteByCourseId(int courseId);

}
