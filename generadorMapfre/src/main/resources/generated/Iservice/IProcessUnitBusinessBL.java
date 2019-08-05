package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IProcessUnitBusinessBL {

	/**
	 * Get all process unit business
	 */
	List<ProcessUnitBusinessBO> getAll();
	
	/**
	 * Add a process unit business
	 */
	ProcessUnitBusinessBO add(ProcessUnitBusinessBO processUnitBusinessBo);

	/**
	 * Update a process unit business
	 */
	ProcessUnitBusinessBO update(Long closePeriodId, ProcessUnitBusinessBO processUnitBusinessBo);

    /**
     * Delete a process unit business
     */
    boolean delete(Long processUnitBusinessId);

}
