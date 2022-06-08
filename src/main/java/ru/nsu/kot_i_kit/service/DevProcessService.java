package ru.nsu.kot_i_kit.service;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.kot_i_kit.entity.DevProcess;
import ru.nsu.kot_i_kit.repository.DevProcessRepo;

import java.util.List;

@AllArgsConstructor
@Service
public class DevProcessService {
    private DevProcessRepo devProcessRepo;

    public DevProcess getById(@NotNull Long id){
        if(!devProcessRepo.existsById(id)){
            throw new IllegalArgumentException("DevProcess with this ID not found");
        }
        return devProcessRepo.getById(id);
    }

    public List<DevProcess> getAll(){
        return devProcessRepo.findAll();
    }

    public void add(@NotNull DevProcess devProcess){
        if(devProcessRepo.existsById(devProcess.getId())){
            throw new IllegalArgumentException("DevProcess already exists");
        }
        devProcessRepo.save(devProcess);
    }

    public boolean existsById(@NotNull Long id){
        return devProcessRepo.existsById(id);
    }

    public void deleteById(@NotNull Long id){
        devProcessRepo.getById(id);
    }

    public void update(@NotNull DevProcess devProcess){
        if(!devProcessRepo.existsById(devProcess.getId())){
            throw new IllegalArgumentException("DevProcess with this ID not found");
        }
        devProcessRepo.save(devProcess);
    }
}
