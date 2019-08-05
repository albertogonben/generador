package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeCriquiteAlarmBL {

	/**
	 * Get all type criquite alarm
	 */
	List<TypeCriquiteAlarmBO> getAll();
	
	/**
	 * Add a type criquite alarm
	 */
	TypeCriquiteAlarmBO add(TypeCriquiteAlarmBO typeCriquiteAlarmBo);

	/**
	 * Update a type criquite alarm
	 */
	TypeCriquiteAlarmBO update(Long closePeriodId, TypeCriquiteAlarmBO typeCriquiteAlarmBo);

    /**
     * Delete a type criquite alarm
     */
    boolean delete(Long typeCriquiteAlarmId);

}
