package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IUserAlarmBL {

	/**
	 * Get all user alarm
	 */
	List<UserAlarmBO> getAll();
	
	/**
	 * Add a user alarm
	 */
	UserAlarmBO add(UserAlarmBO userAlarmBo);

	/**
	 * Update a user alarm
	 */
	UserAlarmBO update(Long closePeriodId, UserAlarmBO userAlarmBo);

    /**
     * Delete a user alarm
     */
    boolean delete(Long userAlarmId);

}
