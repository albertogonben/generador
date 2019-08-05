package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IUnitBusinessBL {

	/**
	 * Get all unit business
	 */
	List<UnitBusinessBO> getAll();
	
	/**
	 * Add a unit business
	 */
	UnitBusinessBO add(UnitBusinessBO unitBusinessBo);

	/**
	 * Update a unit business
	 */
	UnitBusinessBO update(Long closePeriodId, UnitBusinessBO unitBusinessBo);

    /**
     * Delete a unit business
     */
    boolean delete(Long unitBusinessId);

}
