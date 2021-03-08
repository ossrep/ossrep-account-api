package com.ossrep.address_type;

import com.ossrep.exception.ServiceException;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@AllArgsConstructor
public class AddressTypeService {

    private AddressTypeRepository addressTypeRepository;
    private AddressTypeMapper addressTypeMapper;

    public List<AddressType> findAll(){
        return addressTypeRepository.findAll().stream()
                .map(addressTypeMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Optional<AddressType> findById(Integer addressTypeId) {
        return addressTypeRepository.findByIdOptional(addressTypeId).map(addressTypeMapper::toDomain);
    }

    @Transactional
    public AddressType save(AddressType addressType) {
        AddressTypeEntity entity = addressTypeMapper.toEntity(addressType);
        addressTypeRepository.persist(entity);
        return addressTypeMapper.toDomain(entity);
    }

    @Transactional
    public AddressType update(AddressType addressType) {
        if (addressType.getAddressTypeId() == null) {
            throw new ServiceException("AddressType does not have a addressTypeId");
        }
        Optional<AddressTypeEntity> optional = addressTypeRepository.findByIdOptional(addressType.getAddressTypeId());
        if (optional.isEmpty()) {
            throw new ServiceException(String.format("No AddressType found for addressTypeId[%s]", addressType.getAddressTypeId()));
        }
        AddressTypeEntity entity = optional.get();
        entity.setName(addressType.getName());
        entity.setDescription(addressType.getDescription());
        addressTypeRepository.persist(entity);
        return addressTypeMapper.toDomain(entity);
    }

}