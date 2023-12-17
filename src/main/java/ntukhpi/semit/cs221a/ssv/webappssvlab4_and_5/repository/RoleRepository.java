package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.repository;

import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByRoleName(String roleName);
}
