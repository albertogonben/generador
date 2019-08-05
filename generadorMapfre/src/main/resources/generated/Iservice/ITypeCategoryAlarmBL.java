package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeCategoryAlarmBL {

	/**
	 * Get all type category alarm
	 */
	List<TypeCategoryAlarmBO> getAll();
	
	/**
	 * Add a type category alarm
	 */
	TypeCategoryAlarmBO add(TypeCategoryAlarmBO typeCategoryAlarmBo);

	/**
	 * Update a type category alarm
	 */
	TypeCategoryAlarmBO update(Long closePeriodId, TypeCategoryAlarmBO typeCategoryAlarmBo);

    /**
     * Delete a type category alarm
     */
    boolean delete(Long typeCategoryAlarmId);

}
