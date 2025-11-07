package dev.cheloti.populationdatams.queries;

public class CountyQueries {
    public static final String GET_COUNTIES_POPULATION = """
            SELECT c.county_code,
                   c.county_name,
                   c.population,
                   st_asgeojson(st_simplify(geometry::geometry,0.001)) as geometry
            FROM counties c
            ORDER BY c.county_code
            """;
    public static final String GET_COUNTY_POPULATION_BY_CODE = """
            SELECT c.county_code,
            c.county_name,
            c.population,
            st_asgeojson(st_simplify(geometry::geometry, 0.001)) as geometry
            FROM counties c
            WHERE c.county_code = ?
            ORDER BY c.county_code
            """;
    public static final String GET_COUNTY_POPULATION_BY_NAME = """
            SELECT c.county_code,
            c.county_name,
            c.population,
            st_asgeojson(st_simplify(geometry::geometry, 0.001)) as geometry
            FROM counties c
            WHERE c.county_name = ?
            """;
}
