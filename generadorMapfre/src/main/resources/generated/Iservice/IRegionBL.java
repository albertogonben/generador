package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IRegionBL {

	/**
	 * Get all region
	 */
	List<RegionBO> getAll();
	
	/**
	 * Add a region
	 */
	RegionBO add(RegionBO regionBo);

	/**
	 * Update a region
	 */
	RegionBO update(Long closePeriodId, RegionBO regionBo);

    /**
     * Delete a region
     */
    boolean delete(Long regionId);

}
