package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IDepartmentBL {

	/**
	 * Get all department
	 */
	List<DepartmentBO> getAll();
	
	/**
	 * Add a department
	 */
	DepartmentBO add(DepartmentBO departmentBo);

	/**
	 * Update a department
	 */
	DepartmentBO update(Long closePeriodId, DepartmentBO departmentBo);

    /**
     * Delete a department
     */
    boolean delete(Long departmentId);

}
