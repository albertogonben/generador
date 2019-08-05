package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IMatrixRiskBL {

	/**
	 * Get all matrix risk
	 */
	List<MatrixRiskBO> getAll();
	
	/**
	 * Add a matrix risk
	 */
	MatrixRiskBO add(MatrixRiskBO matrixRiskBo);

	/**
	 * Update a matrix risk
	 */
	MatrixRiskBO update(Long closePeriodId, MatrixRiskBO matrixRiskBo);

    /**
     * Delete a matrix risk
     */
    boolean delete(Long matrixRiskId);

}
