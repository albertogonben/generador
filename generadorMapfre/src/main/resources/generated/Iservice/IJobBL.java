package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IJobBL {

	/**
	 * Get all job
	 */
	List<JobBO> getAll();
	
	/**
	 * Add a job
	 */
	JobBO add(JobBO jobBo);

	/**
	 * Update a job
	 */
	JobBO update(Long closePeriodId, JobBO jobBo);

    /**
     * Delete a job
     */
    boolean delete(Long jobId);

}
