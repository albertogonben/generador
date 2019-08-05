package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeProcessBL {

	/**
	 * Get all type process
	 */
	List<TypeProcessBO> getAll();
	
	/**
	 * Add a type process
	 */
	TypeProcessBO add(TypeProcessBO typeProcessBo);

	/**
	 * Update a type process
	 */
	TypeProcessBO update(Long closePeriodId, TypeProcessBO typeProcessBo);

    /**
     * Delete a type process
     */
    boolean delete(Long typeProcessId);

}
