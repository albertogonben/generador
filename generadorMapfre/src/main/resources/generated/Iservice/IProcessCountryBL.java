package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IProcessCountryBL {

	/**
	 * Get all process country
	 */
	List<ProcessCountryBO> getAll();
	
	/**
	 * Add a process country
	 */
	ProcessCountryBO add(ProcessCountryBO processCountryBo);

	/**
	 * Update a process country
	 */
	ProcessCountryBO update(Long closePeriodId, ProcessCountryBO processCountryBo);

    /**
     * Delete a process country
     */
    boolean delete(Long processCountryId);

}
