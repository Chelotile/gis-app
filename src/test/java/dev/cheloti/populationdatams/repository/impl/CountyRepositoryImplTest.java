package dev.cheloti.populationdatams.repository.impl;

import dev.cheloti.populationdatams.ApplicationTests;
import dev.cheloti.populationdatams.entities.County;
import dev.cheloti.populationdatams.exceptions.SQLQueryException;
import dev.cheloti.populationdatams.repository.CountyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CountyRepositoryImplTest extends ApplicationTests {

    @Autowired
    private CountyRepository repository;

    @BeforeEach
    void setUp() {
        // data already in test_db
    }

    @AfterEach
    void tearDown() {
        //don't delete data keep it for next test
        System.out.println("Test completed");
    }

    @Test
    void findCountiesPopulation() {

        var counties = repository.findCountiesPopulation();

        assertNotNull(counties,"counties should not be null");
        assertFalse(counties.isEmpty(), "counties should not be empty");
        assertEquals(47, counties.size(), "counties size should be 47");


        for(County county : counties) {
            assertNotNull(county.getName(), "county name should not be null");
            assertTrue(county.getPopulation()>0, "county population should be greater than 0");
            assertNotNull(county.getGeometry(), "county geometry should not be null");
        }

        var hasNairobi = counties.stream().anyMatch(county -> county.getName().equals("Nairobi"));
        assertTrue(hasNairobi, "Nairobi should not be null");

        var hasMombasa = counties.stream().anyMatch(county -> county.getName().equals("Mombasa"));
        assertTrue(hasMombasa, "Mombasa should not be null");

    }

    @Test
    void testFindCountiesWithPopAbove() {

        var popAbove = 1000000;
        var counties = repository.findCountiesWithPopAbove(popAbove);
        assertNotNull(counties,"counties should not be null");
        assertFalse(counties.isEmpty(), "counties should not be empty");

        for(County county : counties) {
            assertTrue(county.getPopulation()>popAbove, "county population should be greater than 500000");
        }

        var hasNairobi = counties.stream().anyMatch(county -> county.getName().equals("Nairobi"));
        assertTrue(hasNairobi, "Nairobi should not be null");

        var hasKwale = counties.stream().anyMatch(county -> county.getName().equals("Kwale"));
        assertFalse(hasKwale, "Kwale should be null");


    }

    @Test
    void testFindCountiesWithAboveNotFound(){
        var popAbove = 7000000;
        var counties = repository.findCountiesWithPopAbove(popAbove);
        assertThat(counties).isEmpty();
        assertEquals(0, counties.size(), "counties should be zero");
        for(County county : counties) {
            assertFalse(county.getPopulation()>popAbove);
        }


    }

    @Test
    @Disabled
    void findCountyPopulationByCode() {
    }

    @Test
    @Disabled
    void findCountyPopulationByName() {
    }
}