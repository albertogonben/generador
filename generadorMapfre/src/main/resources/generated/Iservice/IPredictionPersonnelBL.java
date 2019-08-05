package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IPredictionPersonnelBL {

	/**
	 * Get all prediction personnel
	 */
	List<PredictionPersonnelBO> getAll();
	
	/**
	 * Add a prediction personnel
	 */
	PredictionPersonnelBO add(PredictionPersonnelBO predictionPersonnelBo);

	/**
	 * Update a prediction personnel
	 */
	PredictionPersonnelBO update(Long closePeriodId, PredictionPersonnelBO predictionPersonnelBo);

    /**
     * Delete a prediction personnel
     */
    boolean delete(Long predictionPersonnelId);

}
