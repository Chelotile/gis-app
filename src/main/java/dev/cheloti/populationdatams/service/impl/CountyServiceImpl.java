package dev.cheloti.populationdatams.service.impl;

import dev.cheloti.populationdatams.dto.CountyDTO;
import dev.cheloti.populationdatams.dtoMapper.CountyMapper;
import dev.cheloti.populationdatams.exceptions.ResourceNotFoundException;
import dev.cheloti.populationdatams.repository.CountyRepository;
import dev.cheloti.populationdatams.service.CountyService;
import dev.cheloti.populationdatams.validation.PropertyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CountyServiceImpl implements CountyService {
    private final CountyRepository countyRepository;
    private final CountyMapper countyMapper;
    private final PropertyValidator propertyValidator;
    @Override
    public List<CountyDTO> getCountiesPopulation() {

        var data =  countyRepository.findCountiesPopulation();

        return List.of(countyMapper.toCountiesDTO(data));
    }

    @Override
    public Optional<CountyDTO> getCountyPopulationByCode(int code) {
        propertyValidator.validateCode(code);
        var data = countyRepository.findCountyPopulationByCode(code)
                .orElseThrow(()-> new ResourceNotFoundException("County not found"));
        return Optional.of(countyMapper.toCountyDTO(data));
    }

    @Override
    public Optional<CountyDTO> getCountyPopulationByName(String name) {
        propertyValidator.validateName(name);
        var data = countyRepository.findCountyPopulationByName(name)
                .orElseThrow(()-> new ResourceNotFoundException("County not found"));
        return Optional.of(countyMapper.toCountyDTO(data));
    }

}
