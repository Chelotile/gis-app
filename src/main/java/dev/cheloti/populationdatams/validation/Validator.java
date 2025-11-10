package dev.cheloti.populationdatams.validation;

import dev.cheloti.populationdatams.exceptions.InvalidInputException;
import dev.cheloti.populationdatams.exceptions.OutOfRangeException;
import org.springframework.stereotype.Component;


@Component
public class Validator {

    public void validateName(String name){

        if(name == null || name.trim()
                .replaceAll("\\s+", " ")  // Replace multiple spaces with single space
                .replaceAll("(?i)\\s*county\\s*$", "")  // Remove 'county' suffix
                .toLowerCase().isEmpty()){
            throw new InvalidInputException("Name cannot be null or empty");
        }


    }
    public void validateCode(int code){
        if(code <1 || code >47){
            throw new OutOfRangeException("Code must be between 1 and 47");
        }
    }
}
