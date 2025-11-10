package dev.cheloti.populationdatams.service.impl;

import dev.cheloti.populationdatams.dto.CountyDTO;
import dev.cheloti.populationdatams.dtoMapper.CountyMapper;
import dev.cheloti.populationdatams.exceptions.ResourceNotFoundException;
import dev.cheloti.populationdatams.repository.CountyRepository;
import dev.cheloti.populationdatams.service.CountyService;
import dev.cheloti.populationdatams.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CountyServiceImpl implements CountyService {
    private final CountyRepository countyRepository;
    private final CountyMapper countyMapper;
    private final Validator validator;
    @Override
    public CountyDTO getCountiesPopulation() {

        var data =  countyRepository.findCountiesPopulation();

        return countyMapper.toCountiesDTO(data);
    }

    @Override
    public CountyDTO getCountiesWithPopulationAbove(long minPopulation) {

        var data = countyRepository.findCountiesWithPopAbove(minPopulation);
        return countyMapper.toCountiesDTO(data);
    }

    @Override
    public CountyDTO getCountyPopulationByCode(int code) {
        var data = countyRepository.findCountyPopulationByCode(code)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("County no %d not found", code)));

        return countyMapper.toCountyDTO(data);
    }

    @Override
    public CountyDTO getCountyPopulationByName(String name) {
        var data = countyRepository.findCountyPopulationByName(name)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("%s county not found",name)));
        return countyMapper.toCountyDTO(data);
    }

}
