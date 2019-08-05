package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IUserCategoryBL {

	/**
	 * Get all user category
	 */
	List<UserCategoryBO> getAll();
	
	/**
	 * Add a user category
	 */
	UserCategoryBO add(UserCategoryBO userCategoryBo);

	/**
	 * Update a user category
	 */
	UserCategoryBO update(Long closePeriodId, UserCategoryBO userCategoryBo);

    /**
     * Delete a user category
     */
    boolean delete(Long userCategoryId);

}
