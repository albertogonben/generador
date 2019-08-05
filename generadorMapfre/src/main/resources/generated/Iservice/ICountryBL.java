package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ICountryBL {

	/**
	 * Get all country
	 */
	List<CountryBO> getAll();
	
	/**
	 * Add a country
	 */
	CountryBO add(CountryBO countryBo);

	/**
	 * Update a country
	 */
	CountryBO update(Long closePeriodId, CountryBO countryBo);

    /**
     * Delete a country
     */
    boolean delete(Long countryId);

}
