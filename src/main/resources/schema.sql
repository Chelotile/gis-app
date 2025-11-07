CREATE SCHEMA IF NOT EXISTS population_data;
CREATE EXTENSION IF NOT EXISTS postgis;

DROP TABLE IF EXISTS counties;

CREATE TABLE counties(
    id SERIAL PRIMARY KEY,
    county_code INTEGER NOT NULL UNIQUE,
    county_name VARCHAR(100) NOT NULL UNIQUE,
    population BIGINT NOT NULL CHECK ( population>0 ),
    geometry GEOMETRY(MULTIPOLYGON, 4326) NOT NULL
);

CREATE INDEX idx_counties_geometry ON counties USING GIST (geometry);
CREATE INDEX idx_counties_name ON counties(county_name);
CREATE INDEX idx_counties_population ON counties(population);
CREATE INDEX idx_counties_code ON counties(county_code);

