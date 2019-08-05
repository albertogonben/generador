package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeCourseBL {

	/**
	 * Get all type course
	 */
	List<TypeCourseBO> getAll();
	
	/**
	 * Add a type course
	 */
	TypeCourseBO add(TypeCourseBO typeCourseBo);

	/**
	 * Update a type course
	 */
	TypeCourseBO update(Long closePeriodId, TypeCourseBO typeCourseBo);

    /**
     * Delete a type course
     */
    boolean delete(Long typeCourseId);

}
