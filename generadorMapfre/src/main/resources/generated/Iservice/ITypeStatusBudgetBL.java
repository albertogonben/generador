package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeStatusBudgetBL {

	/**
	 * Get all type status budget
	 */
	List<TypeStatusBudgetBO> getAll();
	
	/**
	 * Add a type status budget
	 */
	TypeStatusBudgetBO add(TypeStatusBudgetBO typeStatusBudgetBo);

	/**
	 * Update a type status budget
	 */
	TypeStatusBudgetBO update(Long closePeriodId, TypeStatusBudgetBO typeStatusBudgetBo);

    /**
     * Delete a type status budget
     */
    boolean delete(Long typeStatusBudgetId);

}
