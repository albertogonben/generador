package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IEntamapBL {

	/**
	 * Get all entamap
	 */
	List<EntamapBO> getAll();
	
	/**
	 * Add a entamap
	 */
	EntamapBO add(EntamapBO entamapBo);

	/**
	 * Update a entamap
	 */
	EntamapBO update(Long closePeriodId, EntamapBO entamapBo);

    /**
     * Delete a entamap
     */
    boolean delete(Long entamapId);

}
