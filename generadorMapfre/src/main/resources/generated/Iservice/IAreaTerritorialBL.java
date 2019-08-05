package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IAreaTerritorialBL {

	/**
	 * Get all area territorial
	 */
	List<AreaTerritorialBO> getAll();
	
	/**
	 * Add a area territorial
	 */
	AreaTerritorialBO add(AreaTerritorialBO areaTerritorialBo);

	/**
	 * Update a area territorial
	 */
	AreaTerritorialBO update(Long closePeriodId, AreaTerritorialBO areaTerritorialBo);

    /**
     * Delete a area territorial
     */
    boolean delete(Long areaTerritorialId);

}
