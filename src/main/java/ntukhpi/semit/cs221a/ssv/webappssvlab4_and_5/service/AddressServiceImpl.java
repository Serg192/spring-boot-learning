package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.service;

import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.Address;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public List<Address> getAllAddressesByIdClient(Long idClient) {
        return addressRepository.findAddressesByClient_Id(idClient);
    }

    @Override
    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddressById(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public void deleteAddressesByIdClient(Long id) {
        addressRepository.deleteAddressesByClient_Id(id);
    }
}
