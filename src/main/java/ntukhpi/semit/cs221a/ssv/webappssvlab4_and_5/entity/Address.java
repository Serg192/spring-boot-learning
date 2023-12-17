package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.dto.AddressDTO;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"id"})
@ToString
@Entity
@Table(name="addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String codAddress;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false, updatable = false)
    private Client client;

    @Column(nullable = false, length = 30)
    private String region;

    @Column(nullable = false, length = 30)
    private String city;

    @Column(length = 50)
    private String addressStr;

    private Integer pointNP;
    private Integer pointUkrPost;

    public Address(){
        this.id = 0L;
        this.codAddress = "";
        this.client = null;
        this.region = "";
        this.city = "";
        this.addressStr = "";
    }

    public static Address fromDTO(AddressDTO dto){
        Address address = new Address();
        address.id = dto.getId();
        address.codAddress = dto.getCodAddress();
        address.region = dto.getRegion();
        address.city = dto.getCity();
        address.addressStr = dto.getAddressStr();
        address.pointNP = dto.getPointNP();
        address.pointUkrPost = dto.getPointUkrPost();
        return address;
    }
}
