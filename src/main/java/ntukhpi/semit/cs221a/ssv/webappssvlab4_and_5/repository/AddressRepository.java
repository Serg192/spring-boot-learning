package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.repository;

import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAddressesByClient_Id(Long id);

    void deleteAddressesByClient_Id(Long idEmpl);
}
