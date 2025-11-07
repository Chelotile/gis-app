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
    public List<PopDensityDTO> getCountiesPopDensity() {

        var data = popDensityRepository.findCountiesPopDensity();
        return List.of(mapper.toDTOs(data));
    }

    @Override
    public Optional<PopDensityDTO> getCountyPopDensityByCode(int code) {
        validate.validateCode(code);
        var data = popDensityRepository.findCountyPopDensityByCode(code)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("County no %d not found", code)));
        return Optional.of(mapper.toDTO(data));
    }

    @Override
    public Optional<PopDensityDTO> getCountyPopDensityByName(String name) {
        var data = popDensityRepository.findCountyPopDensityByName(name).map(mapper::toDTO);
        return Optional.of(data)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("%s county not found", name)));
    }
}
