package dev.cheloti.populationdatams.validation;

import dev.cheloti.populationdatams.exceptions.OutOfRangeException;
import dev.cheloti.populationdatams.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PropertyValidator {

    public void validateName(String name){

        if(name == null || name.trim().isEmpty()){
            throw new ResourceNotFoundException("Name cannot be null or empty");
        }
    }
    public void validateCode(int code){
        if(code <1 || code >47){
            throw new OutOfRangeException("Code must be between 1 and 47");
        }
    }
}
