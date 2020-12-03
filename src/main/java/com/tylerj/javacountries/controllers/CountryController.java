package com.tylerj.javacountries.controllers;

import com.tylerj.javacountries.models.Country;
import com.tylerj.javacountries.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {

    @Autowired
    CountryRepository countryrepos;

    private List<Country> findCountry(List<Country> myList, char letter) {
        List<Country> tempList = new ArrayList<>();

        for (Country c : myList) {
            if (c.getName().charAt(0) == letter) {
                tempList.add(c);
            }
        }
        return tempList;
    }

    // http://localhost:2019/names/all
    @GetMapping(value = "/names/all", produces = "application/json")
    public ResponseEntity<?> listAllCountries() {
        List<Country> myList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(myList::add);
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    // http://localhost:2019/names/start/u
    @GetMapping(value = "names/start/{letter}")
    public ResponseEntity<?> listAllCountriesByFirstLetter(@PathVariable char letter) {
        List<Country> myList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(myList::add);
        List<Country> rtnList = findCountry(myList, letter);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    // http://localhost:2019/population/total
    @GetMapping(value = "/population/total", produces = "application/json")
    public ResponseEntity<?> totalEmployeeSalary() {
        List<Country> myList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(myList::add);

        long total = 0;
        for (Country c : myList) {
            total += c.getPopulation();
        }

        System.out.println("The total population is " + total);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    // myList.sort((e1, e2) -> (int)(e1.getPopulation() - e2.getPopulation()));

    // http://localhost:2019/population/min
    @GetMapping(value = "/population/min", produces = "application/json")
    public ResponseEntity<?> minPopulation() {
        List<Country> myList = new ArrayList<>();

        countryrepos.findAll().iterator().forEachRemaining(myList::add);
        myList.sort((e1, e2) -> (int)(e1.getPopulation() - e2.getPopulation()));

        return new ResponseEntity<>(myList.get(0), HttpStatus.OK);
    }


    // http://localhost:2019/population/max
    @GetMapping(value = "/population/max", produces = "application/json")
    public ResponseEntity<?> maxPopulation() {
        List<Country> myList = new ArrayList<>();

        countryrepos.findAll().iterator().forEachRemaining(myList::add);
        myList.sort((e1, e2) -> (int)(e2.getPopulation() - e1.getPopulation()));

        return new ResponseEntity<>(myList.get(0), HttpStatus.OK);
    }

    // http://localhost:2019/population/median
    @GetMapping(value = "/population/median", produces = "application/json")
    public ResponseEntity<?> medianPopulation() {
        List<Country> myList = new ArrayList<>();

        countryrepos.findAll().iterator().forEachRemaining(myList::add);
        myList.sort((e1, e2) -> (int)(e2.getPopulation() - e1.getPopulation()));
        

        return new ResponseEntity<>(myList.get((myList.size() / 2) - 1), HttpStatus.OK);
    }



}
