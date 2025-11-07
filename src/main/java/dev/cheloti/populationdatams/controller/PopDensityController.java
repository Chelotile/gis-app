package dev.cheloti.populationdatams.controller;

import dev.cheloti.populationdatams.domain.Response;
import dev.cheloti.populationdatams.exceptions.ResourceNotFoundException;
import dev.cheloti.populationdatams.service.PopDensityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pop")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class PopDensityController {

    private final PopDensityService popDensityService;

    @GetMapping("/all")
    public ResponseEntity<Response> getCountiesPopDensity() {

        try {
            var data = popDensityService.getCountiesPopDensity();

            return ResponseEntity.ok().body(
                    new Response(
                            "counties pop density",
                            HttpStatus.OK,
                            HttpStatus.OK.value(),
                            Map.of("data", data))
            );
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/by-code/{code}")
    public ResponseEntity<Response> getCountyPopDensityByCode(@PathVariable int code){

        try {
            var data = popDensityService.getCountyPopDensityByCode(code);

            return ResponseEntity.ok().body(
                    new Response(
                            String.format("pop density for county number %d", code),
                            HttpStatus.OK,
                            HttpStatus.OK.value(),
                            Map.of("data", data)
                    )
            );
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new ResourceNotFoundException("county number " + code + " not found");
        }
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<Response> getCountyPopDensityByName(@PathVariable String name) {

        try {
            var data = popDensityService.getCountyPopDensityByName(name);
            return ResponseEntity.ok().body(
                    new Response(
                            String.format("pop density for %s county",name),
                            HttpStatus.OK,
                            HttpStatus.OK.value(),
                            Map.of("data", data)
                    )
            );
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Response(
                            String.format(name, "%s county not found"),
                            HttpStatus.NOT_FOUND,
                            HttpStatus.NOT_FOUND.value(),
                            Map.of("data", e)
                    )
            );
        }
    }
}
