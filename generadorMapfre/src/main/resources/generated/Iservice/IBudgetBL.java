package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IBudgetBL {

	/**
	 * Get all budget
	 */
	List<BudgetBO> getAll();
	
	/**
	 * Add a budget
	 */
	BudgetBO add(BudgetBO budgetBo);

	/**
	 * Update a budget
	 */
	BudgetBO update(Long closePeriodId, BudgetBO budgetBo);

    /**
     * Delete a budget
     */
    boolean delete(Long budgetId);

}
