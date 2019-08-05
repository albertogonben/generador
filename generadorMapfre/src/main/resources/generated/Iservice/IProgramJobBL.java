package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IProgramJobBL {

	/**
	 * Get all program job
	 */
	List<ProgramJobBO> getAll();
	
	/**
	 * Add a program job
	 */
	ProgramJobBO add(ProgramJobBO programJobBo);

	/**
	 * Update a program job
	 */
	ProgramJobBO update(Long closePeriodId, ProgramJobBO programJobBo);

    /**
     * Delete a program job
     */
    boolean delete(Long programJobId);

}
