package lv.team3.botcovidlab.manager.repository;
import lv.team3.botcovidlab.manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByEmail(String email);

}
