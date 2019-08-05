package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IGuideJobBL {

	/**
	 * Get all guide job
	 */
	List<GuideJobBO> getAll();
	
	/**
	 * Add a guide job
	 */
	GuideJobBO add(GuideJobBO guideJobBo);

	/**
	 * Update a guide job
	 */
	GuideJobBO update(Long closePeriodId, GuideJobBO guideJobBo);

    /**
     * Delete a guide job
     */
    boolean delete(Long guideJobId);

}
