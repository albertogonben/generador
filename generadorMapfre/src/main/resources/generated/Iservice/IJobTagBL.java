package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IJobTagBL {

	/**
	 * Get all job tag
	 */
	List<JobTagBO> getAll();
	
	/**
	 * Add a job tag
	 */
	JobTagBO add(JobTagBO jobTagBo);

	/**
	 * Update a job tag
	 */
	JobTagBO update(Long closePeriodId, JobTagBO jobTagBo);

    /**
     * Delete a job tag
     */
    boolean delete(Long jobTagId);

}
