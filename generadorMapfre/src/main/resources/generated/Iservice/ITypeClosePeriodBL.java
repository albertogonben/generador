package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeClosePeriodBL {

	/**
	 * Get all type close period
	 */
	List<TypeClosePeriodBO> getAll();
	
	/**
	 * Add a type close period
	 */
	TypeClosePeriodBO add(TypeClosePeriodBO typeClosePeriodBo);

	/**
	 * Update a type close period
	 */
	TypeClosePeriodBO update(Long closePeriodId, TypeClosePeriodBO typeClosePeriodBo);

    /**
     * Delete a type close period
     */
    boolean delete(Long typeClosePeriodId);

}
