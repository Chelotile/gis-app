package dev.cheloti.populationdatams.utilis;

import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.MultiPolygon;
import org.springframework.stereotype.Component;
import org.wololo.jts2geojson.GeoJSONReader;

@Component
@RequiredArgsConstructor
public class Reader {
    private final GeoJSONReader reader;

    public MultiPolygon parseGeoJSON(String geoJson){
        if (geoJson == null){
            throw new IllegalArgumentException("geoJson is null or empty");
        }
        return (MultiPolygon) reader.read(geoJson);
    }
}
