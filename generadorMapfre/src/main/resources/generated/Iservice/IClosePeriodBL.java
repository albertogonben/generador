package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IClosePeriodBL {

	/**
	 * Get all close period
	 */
	List<ClosePeriodBO> getAll();
	
	/**
	 * Add a close period
	 */
	ClosePeriodBO add(ClosePeriodBO closePeriodBo);

	/**
	 * Update a close period
	 */
	ClosePeriodBO update(Long closePeriodId, ClosePeriodBO closePeriodBo);

    /**
     * Delete a close period
     */
    boolean delete(Long closePeriodId);

}
