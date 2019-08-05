package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IUserAmapBL {

	/**
	 * Get all user amap
	 */
	List<UserAmapBO> getAll();
	
	/**
	 * Add a user amap
	 */
	UserAmapBO add(UserAmapBO userAmapBo);

	/**
	 * Update a user amap
	 */
	UserAmapBO update(Long closePeriodId, UserAmapBO userAmapBo);

    /**
     * Delete a user amap
     */
    boolean delete(Long userAmapId);

}
