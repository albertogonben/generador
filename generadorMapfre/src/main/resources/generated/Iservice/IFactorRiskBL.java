package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IFactorRiskBL {

	/**
	 * Get all factor risk
	 */
	List<FactorRiskBO> getAll();
	
	/**
	 * Add a factor risk
	 */
	FactorRiskBO add(FactorRiskBO factorRiskBo);

	/**
	 * Update a factor risk
	 */
	FactorRiskBO update(Long closePeriodId, FactorRiskBO factorRiskBo);

    /**
     * Delete a factor risk
     */
    boolean delete(Long factorRiskId);

}
