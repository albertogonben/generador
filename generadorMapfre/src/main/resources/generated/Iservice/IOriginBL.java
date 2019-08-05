package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IOriginBL {

	/**
	 * Get all origin
	 */
	List<OriginBO> getAll();
	
	/**
	 * Add a origin
	 */
	OriginBO add(OriginBO originBo);

	/**
	 * Update a origin
	 */
	OriginBO update(Long closePeriodId, OriginBO originBo);

    /**
     * Delete a origin
     */
    boolean delete(Long originId);

}
