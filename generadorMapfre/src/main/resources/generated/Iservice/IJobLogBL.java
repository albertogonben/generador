package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IJobLogBL {

	/**
	 * Get all job log
	 */
	List<JobLogBO> getAll();
	
	/**
	 * Add a job log
	 */
	JobLogBO add(JobLogBO jobLogBo);

	/**
	 * Update a job log
	 */
	JobLogBO update(Long closePeriodId, JobLogBO jobLogBo);

    /**
     * Delete a job log
     */
    boolean delete(Long jobLogId);

}
