package dev.cheloti.populationdatams.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.n52.jackson.datatype.jts.JtsModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wololo.jts2geojson.GeoJSONReader;

@Configuration
public class JTSConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
         var objectMapper = new ObjectMapper();
         objectMapper.registerModule(new Jdk8Module());
         objectMapper.registerModule(new JtsModule());
         return objectMapper;
    }

    @Bean
    public GeoJSONReader geoJSONReader() {
        return new GeoJSONReader();
    }
}
