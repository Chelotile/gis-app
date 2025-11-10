package dev.cheloti.populationdatams.controller;

import dev.cheloti.populationdatams.domain.Response;
import dev.cheloti.populationdatams.service.PopDensityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/popDensity")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class PopDensityController {

    private final PopDensityService popDensityService;

    @GetMapping("/allCounties")
    public ResponseEntity<Response> getCountiesPopDensity() {
            var data = popDensityService.getCountiesPopDensity();
            return ResponseEntity.ok().body(
                    new Response(
                            "counties pop density",
                            HttpStatus.OK,
                            HttpStatus.OK.value(),
                            Map.of("data", data))
            );
    }
    @GetMapping("/counties")
    public ResponseEntity<Response> getCountiesWithPopDensityAbove(@RequestParam long popDensityAbove) {
        var data = popDensityService.getCountiesWithPopDensityAbove(popDensityAbove);
        return ResponseEntity.ok().body(
                new Response(
                        String.format("counties with pop density above %d",popDensityAbove),
                        HttpStatus.OK,
                        HttpStatus.OK.value(),
                        Map.of("data", data)
                )
        );
    }
    @GetMapping("/by-code/{code}")
    public ResponseEntity<Response> getCountyPopDensityByCode(@PathVariable int code){

            var data = popDensityService.getCountyPopDensityByCode(code);

            return ResponseEntity.ok().body(
                    new Response(
                            String.format("pop density for county number %d", code),
                            HttpStatus.OK,
                            HttpStatus.OK.value(),
                            Map.of("data", data)
                    )
            );

    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<Response> getCountyPopDensityByName(@PathVariable String name) {

            var data = popDensityService.getCountyPopDensityByName(name);
            return ResponseEntity.ok().body(
                    new Response(
                            String.format("pop density for %s county",name),
                            HttpStatus.OK,
                            HttpStatus.OK.value(),
                            Map.of("data", data)
                    )
            );
    }
}
