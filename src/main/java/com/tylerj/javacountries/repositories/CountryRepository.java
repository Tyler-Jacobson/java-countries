package com.tylerj.javacountries.repositories;

import com.tylerj.javacountries.models.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
