package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IJobOriginBL {

	/**
	 * Get all job origin
	 */
	List<JobOriginBO> getAll();
	
	/**
	 * Add a job origin
	 */
	JobOriginBO add(JobOriginBO jobOriginBo);

	/**
	 * Update a job origin
	 */
	JobOriginBO update(Long closePeriodId, JobOriginBO jobOriginBo);

    /**
     * Delete a job origin
     */
    boolean delete(Long jobOriginId);

}
