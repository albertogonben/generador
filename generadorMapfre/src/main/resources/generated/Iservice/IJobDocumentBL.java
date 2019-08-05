package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IJobDocumentBL {

	/**
	 * Get all job document
	 */
	List<JobDocumentBO> getAll();
	
	/**
	 * Add a job document
	 */
	JobDocumentBO add(JobDocumentBO jobDocumentBo);

	/**
	 * Update a job document
	 */
	JobDocumentBO update(Long closePeriodId, JobDocumentBO jobDocumentBo);

    /**
     * Delete a job document
     */
    boolean delete(Long jobDocumentId);

}
