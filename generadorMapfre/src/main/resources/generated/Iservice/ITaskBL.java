package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITaskBL {

	/**
	 * Get all task
	 */
	List<TaskBO> getAll();
	
	/**
	 * Add a task
	 */
	TaskBO add(TaskBO taskBo);

	/**
	 * Update a task
	 */
	TaskBO update(Long closePeriodId, TaskBO taskBo);

    /**
     * Delete a task
     */
    boolean delete(Long taskId);

}
