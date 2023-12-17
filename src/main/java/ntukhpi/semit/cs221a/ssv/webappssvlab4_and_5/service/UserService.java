package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.service;

import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.dto.UserDTO;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User save(User user);

    User saveClient(UserDTO userDTO);
    Optional<User> findUserByLogin(String login);
}
