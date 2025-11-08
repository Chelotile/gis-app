package dev.cheloti.populationdatams.controller;

import dev.cheloti.populationdatams.domain.Response;
import dev.cheloti.populationdatams.exceptions.ResourceNotFoundException;
import dev.cheloti.populationdatams.service.CountyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class CountyController {
    private final CountyService countyService;

    @GetMapping("/counties")
    public ResponseEntity<Response> getCounties() {

        try {
            var data = countyService.getCountiesPopulation();

            return ResponseEntity.ok().body(
                    new Response(
                            "Successfully retrieved  counties",
                            HttpStatus.OK,
                            HttpStatus.OK.value(),
                            Map.of("counties", data)
                    )
            );
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();

        }
    }
    @GetMapping("/counties/by-code/{code}")
    public ResponseEntity<Response> getCountyByCode(@PathVariable int code){
        try {
            var data = countyService.getCountyPopulationByCode(code);

            return ResponseEntity.ok().body(
                    new Response(
                            String.format("Retrieve data for county no %d ", code),
                            HttpStatus.OK,
                            HttpStatus.OK.value(),
                            Map.of("county", data)
                    )
            );
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/counties/by-name/{name}")
    public ResponseEntity<Response> getCountyByName(@PathVariable String name){

        try {
            var data = countyService.getCountyPopulationByName(name);
            return ResponseEntity.ok().body(
                    new Response(
                            String.format("Retrieve data for %s county", name),
                            HttpStatus.OK,
                            HttpStatus.OK.value(),
                            Map.of("county", data)
                    )
            );
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
