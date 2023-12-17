package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.repository;

import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByLogin(String login);
}
