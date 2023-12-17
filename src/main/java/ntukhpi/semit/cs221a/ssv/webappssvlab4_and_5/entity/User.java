package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.dto.UserDTO;

import java.util.Set;

@Entity
@Table(name = "app_users_tl")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String login;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "app_users_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")
            }
    )
    private Set<Role> roles;

    public static User fromDTO(UserDTO dto) {
        User user = new User();
        user.login = dto.getLogin();
        user.password = dto.getPassword();
        return user;
    }
}
