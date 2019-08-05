package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IPaiBL {

	/**
	 * Get all pai
	 */
	List<PaiBO> getAll();
	
	/**
	 * Add a pai
	 */
	PaiBO add(PaiBO paiBo);

	/**
	 * Update a pai
	 */
	PaiBO update(Long closePeriodId, PaiBO paiBo);

    /**
     * Delete a pai
     */
    boolean delete(Long paiId);

}
