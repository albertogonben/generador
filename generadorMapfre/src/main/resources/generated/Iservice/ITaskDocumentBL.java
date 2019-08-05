package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITaskDocumentBL {

	/**
	 * Get all task document
	 */
	List<TaskDocumentBO> getAll();
	
	/**
	 * Add a task document
	 */
	TaskDocumentBO add(TaskDocumentBO taskDocumentBo);

	/**
	 * Update a task document
	 */
	TaskDocumentBO update(Long closePeriodId, TaskDocumentBO taskDocumentBo);

    /**
     * Delete a task document
     */
    boolean delete(Long taskDocumentId);

}
