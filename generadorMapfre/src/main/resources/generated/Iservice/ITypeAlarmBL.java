package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeAlarmBL {

	/**
	 * Get all type alarm
	 */
	List<TypeAlarmBO> getAll();
	
	/**
	 * Add a type alarm
	 */
	TypeAlarmBO add(TypeAlarmBO typeAlarmBo);

	/**
	 * Update a type alarm
	 */
	TypeAlarmBO update(Long closePeriodId, TypeAlarmBO typeAlarmBo);

    /**
     * Delete a type alarm
     */
    boolean delete(Long typeAlarmId);

}
