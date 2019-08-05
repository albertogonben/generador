package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeOriginProgramJobBL {

	/**
	 * Get all type origin program job
	 */
	List<TypeOriginProgramJobBO> getAll();
	
	/**
	 * Add a type origin program job
	 */
	TypeOriginProgramJobBO add(TypeOriginProgramJobBO typeOriginProgramJobBo);

	/**
	 * Update a type origin program job
	 */
	TypeOriginProgramJobBO update(Long closePeriodId, TypeOriginProgramJobBO typeOriginProgramJobBo);

    /**
     * Delete a type origin program job
     */
    boolean delete(Long typeOriginProgramJobId);

}
