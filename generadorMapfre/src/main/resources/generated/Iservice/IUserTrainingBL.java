package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IUserTrainingBL {

	/**
	 * Get all user training
	 */
	List<UserTrainingBO> getAll();
	
	/**
	 * Add a user training
	 */
	UserTrainingBO add(UserTrainingBO userTrainingBo);

	/**
	 * Update a user training
	 */
	UserTrainingBO update(Long closePeriodId, UserTrainingBO userTrainingBo);

    /**
     * Delete a user training
     */
    boolean delete(Long userTrainingId);

}
