package dev.cheloti.populationdatams.service.impl;

import dev.cheloti.populationdatams.dto.PopDensityDTO;
import dev.cheloti.populationdatams.dtoMapper.PopDensityMapper;
import dev.cheloti.populationdatams.exceptions.ResourceNotFoundException;
import dev.cheloti.populationdatams.repository.PopDensityRepository;
import dev.cheloti.populationdatams.service.PopDensityService;
import dev.cheloti.populationdatams.validation.PropertyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class PopDensityServiceImpl implements PopDensityService {

    private final PopDensityRepository popDensityRepository;
    private final PopDensityMapper mapper;
    private final PropertyValidator validate;
    @Override
    public PopDensityDTO getCountiesPopDensity() {

        var data = popDensityRepository.findCountiesPopDensity();
        return mapper.toDTOs(data);
    }

    @Override
    public PopDensityDTO getCountyPopDensityByCode(int code) {
        validate.validateCode(code);
        var data = popDensityRepository.findCountyPopDensityByCode(code)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("County no %d not found", code)));
        return mapper.toDTO(data);
    }

    @Override
    public PopDensityDTO getCountyPopDensityByName(String name) {
        validate.validateName(name);
        var data = popDensityRepository.findCountyPopDensityByName(name).map(mapper::toDTO);
        return data.orElseThrow(()-> new ResourceNotFoundException(String.format("%s county not found", name)));
    }
}
