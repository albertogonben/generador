package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeStateJobBL {

	/**
	 * Get all type state job
	 */
	List<TypeStateJobBO> getAll();
	
	/**
	 * Add a type state job
	 */
	TypeStateJobBO add(TypeStateJobBO typeStateJobBo);

	/**
	 * Update a type state job
	 */
	TypeStateJobBO update(Long closePeriodId, TypeStateJobBO typeStateJobBo);

    /**
     * Delete a type state job
     */
    boolean delete(Long typeStateJobId);

}
