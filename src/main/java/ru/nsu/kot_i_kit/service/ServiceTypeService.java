package ru.nsu.kot_i_kit.service;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.kot_i_kit.entity.ServiceType;
import ru.nsu.kot_i_kit.repository.ServiceTypeRepo;

@AllArgsConstructor
@Service
public class ServiceTypeService {
    private ServiceTypeRepo serviceTypeRepo;

    public ServiceType getById(@NotNull Long id) {
        if (!serviceTypeRepo.existsById(id)) {
            throw new IllegalArgumentException("Service type with this ID not found");
        }
        return serviceTypeRepo.getById(id);
    }

    public java.util.List<ServiceType> getAll() {
        return serviceTypeRepo.findAll();
    }

    public void add(ServiceType serviceType) {
        if (serviceTypeRepo.existsById(serviceType.getId())) {
            throw new IllegalArgumentException("ServiceType already exists");
        }
        serviceTypeRepo.save(serviceType);
    }

    public boolean existsById(@NotNull Long id) {
        return serviceTypeRepo.existsById(id);
    }

    public void deleteById(@NotNull Long id) {
        serviceTypeRepo.deleteById(id);
    }

    public void update(@NotNull ServiceType serviceType) {
        if (!serviceTypeRepo.existsById(serviceType.getId())) {
            throw new IllegalArgumentException("Service with this ID not found");
        }
        serviceTypeRepo.save(serviceType);
    }
}
