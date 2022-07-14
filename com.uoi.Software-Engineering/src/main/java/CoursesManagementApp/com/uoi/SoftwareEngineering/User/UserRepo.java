/*package CoursesManagementApp.com.uoi.SoftwareEngineering.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface UserRepo extends JpaRepository<User,Integer> {
    @Query("select u from User u where u.username = ?1")
    public User findByUsername(String userName);

}*/
