package com.ossrep.address;

import com.ossrep.account.AccountRepository;
import com.ossrep.address_type.AddressTypeRepository;
import com.ossrep.exception.ServiceException;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@AllArgsConstructor
public class AddressService {

    private AddressRepository addressRepository;
    private AddressTypeRepository addressTypeRepository;
    private AccountRepository accountRepository;
    private AddressMapper addressMapper;

    public List<Address> findAll(){
        return addressRepository.findAll().stream()
                .map(addressMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Optional<Address> findById(Integer addressId) {
        return addressRepository.findByIdOptional(addressId).map(addressMapper::toDomain);
    }

    @Transactional
    public Address save(Address address) {
        AddressEntity entity = addressMapper.toEntity(address);
        addressRepository.persist(entity);
        return addressMapper.toDomain(entity);
    }

    @Transactional
    public Address update(Address address) {
        if (address.getAddressId() == null) {
            throw new ServiceException("Address does not have a addressId");
        }
        Optional<AddressEntity> optional = addressRepository.findByIdOptional(address.getAddressId());
        if (optional.isEmpty()) {
            throw new ServiceException(String.format("No Address found for addressId[%s]", address.getAddressId()));
        }
        AddressEntity entity = optional.get();
        entity.setAddressType(addressTypeRepository.findById(address.getAddressType().getAddressTypeId()));
        entity.setAccount(accountRepository.findById(address.getAccount().getAccountId()));
        entity.setAddressLine1(address.getAddressLine1());
        entity.setAddressLine2(address.getAddressLine2());
        entity.setCity(address.getCity());
        entity.setState(address.getState());
        entity.setZipCode(address.getZipCode());
        addressRepository.persist(entity);
        return addressMapper.toDomain(entity);
    }

}