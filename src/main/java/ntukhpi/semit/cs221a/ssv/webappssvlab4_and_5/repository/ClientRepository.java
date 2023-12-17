package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.repository;

import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.Client;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByEmailOrPhone(String email, String phone);

    Optional<Client> findClientByEmail(String email);
    Optional<Client> findClientByPhone(String phone);

    Optional<Client> findClientByUser(User user);
}
