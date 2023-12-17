package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.service;



import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<Address> getAllAddresses();

    List<Address> getAllAddressesByIdClient(Long idClient);

    Optional<Address> getAddressById(Long id);

    Address saveAddress(Address address);
    void deleteAddressById(Long id);

    void deleteAddressesByIdClient(Long id);
}
