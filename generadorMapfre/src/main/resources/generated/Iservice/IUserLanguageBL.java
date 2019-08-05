package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IUserLanguageBL {

	/**
	 * Get all user language
	 */
	List<UserLanguageBO> getAll();
	
	/**
	 * Add a user language
	 */
	UserLanguageBO add(UserLanguageBO userLanguageBo);

	/**
	 * Update a user language
	 */
	UserLanguageBO update(Long closePeriodId, UserLanguageBO userLanguageBo);

    /**
     * Delete a user language
     */
    boolean delete(Long userLanguageId);

}
