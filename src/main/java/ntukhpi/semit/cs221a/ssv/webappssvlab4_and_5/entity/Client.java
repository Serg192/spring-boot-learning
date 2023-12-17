package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.dto.ClientDTOForUser;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.enums.Gender;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"id"})
@ToString
@Entity
@Table(name="clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "second_name", nullable = false, length = 100)
    private String secondName;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Gender gender;

    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private Boolean isVIP;

    public Client(String firstName, String secondName, String phone, String email, Gender gender, boolean isVIP) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.isVIP = isVIP;
    }

    public Client(){
        this.id = 0L;
        this.firstName = "";
        this.secondName = "";
        this.phone = "";
        this.email = "";
        this.gender = Gender.undefined;
        this.isVIP = false;
    }

    public static Client fromDTO(ClientDTOForUser clientDTOForUser){
        Client client = new Client();
        client.id = clientDTOForUser.getId();
        client.email = clientDTOForUser.getEmail();
        client.firstName = clientDTOForUser.getFirstName();
        client.secondName = clientDTOForUser.getSecondName();
        client.gender = clientDTOForUser.getGender();
        client.phone = clientDTOForUser.getPhone();
        return client;
    }
}