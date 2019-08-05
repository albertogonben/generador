package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IJobIdeaBL {

	/**
	 * Get all job idea
	 */
	List<JobIdeaBO> getAll();
	
	/**
	 * Add a job idea
	 */
	JobIdeaBO add(JobIdeaBO jobIdeaBo);

	/**
	 * Update a job idea
	 */
	JobIdeaBO update(Long closePeriodId, JobIdeaBO jobIdeaBo);

    /**
     * Delete a job idea
     */
    boolean delete(Long jobIdeaId);

}
