package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IMatrixRiskValuationBL {

	/**
	 * Get all matrix risk valuation
	 */
	List<MatrixRiskValuationBO> getAll();
	
	/**
	 * Add a matrix risk valuation
	 */
	MatrixRiskValuationBO add(MatrixRiskValuationBO matrixRiskValuationBo);

	/**
	 * Update a matrix risk valuation
	 */
	MatrixRiskValuationBO update(Long closePeriodId, MatrixRiskValuationBO matrixRiskValuationBo);

    /**
     * Delete a matrix risk valuation
     */
    boolean delete(Long matrixRiskValuationId);

}
