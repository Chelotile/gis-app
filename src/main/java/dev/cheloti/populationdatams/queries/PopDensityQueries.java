package dev.cheloti.populationdatams.queries;

public class PopDensityQueries {
    public static final String GET_ALL_COUNTIES_POP_DENSITY = """
            SELECT c.county_code,
            c.county_name,
            round(c.population/(st_area(st_transform(c.geometry, 21097))/1000000)) as population_density,
            st_asgeojson(st_simplify(c.geometry::geometry, 0.001)) as geometry
            FROM counties c
            ORDER BY c.county_code
            """;
    public static final String GET_COUNTY_POP_DENSITY_BY_CODE = """
            SELECT c.county_code,
            c.county_name,
            ceil(c.population/(st_area(st_transform(c.geometry, 21097))/1000000)) as population_density,
            st_asgeojson(st_simplify(c.geometry::geometry, 0.001)) as geometry
            FROM counties c
            WHERE c.county_code = ?
            """;
    public static final String GET_COUNTY_POP_DENSITY_NAME = """
            SELECT c.county_code,
            c.county_name,
            ceil(c.population/(st_area(st_transform(c.geometry, 21097))/1000000)) as population_density,
            st_asgeojson(st_simplify(c.geometry::geometry, 0.001)) as geometry
            FROM counties c
            WHERE c.county_name ILIKE $1
            """;
}
