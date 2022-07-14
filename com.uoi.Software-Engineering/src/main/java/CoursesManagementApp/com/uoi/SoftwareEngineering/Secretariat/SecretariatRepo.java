package CoursesManagementApp.com.uoi.SoftwareEngineering.Secretariat;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface SecretariatRepo extends JpaRepository<Secretariat,Integer> {
    @Transactional
    @Modifying
    @Query("delete from Secretariat s where s.userName = ?1")
    public void deleteByUserName(String userName);
    @Query("select s from Secretariat s where s.userName = ?1")
    public Secretariat findByUserName(String userName);
    @Transactional
    @Modifying
    @Query("delete from Secretariat s where s.id = ?1")
    public void deleteById(int id);
}