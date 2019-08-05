package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ISectorBL {

	/**
	 * Get all sector
	 */
	List<SectorBO> getAll();
	
	/**
	 * Add a sector
	 */
	SectorBO add(SectorBO sectorBo);

	/**
	 * Update a sector
	 */
	SectorBO update(Long closePeriodId, SectorBO sectorBo);

    /**
     * Delete a sector
     */
    boolean delete(Long sectorId);

}
