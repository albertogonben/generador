package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeJobBL {

	/**
	 * Get all type job
	 */
	List<TypeJobBO> getAll();
	
	/**
	 * Add a type job
	 */
	TypeJobBO add(TypeJobBO typeJobBo);

	/**
	 * Update a type job
	 */
	TypeJobBO update(Long closePeriodId, TypeJobBO typeJobBo);

    /**
     * Delete a type job
     */
    boolean delete(Long typeJobId);

}
