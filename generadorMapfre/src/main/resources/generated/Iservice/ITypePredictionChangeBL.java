package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypePredictionChangeBL {

	/**
	 * Get all type prediction change
	 */
	List<TypePredictionChangeBO> getAll();
	
	/**
	 * Add a type prediction change
	 */
	TypePredictionChangeBO add(TypePredictionChangeBO typePredictionChangeBo);

	/**
	 * Update a type prediction change
	 */
	TypePredictionChangeBO update(Long closePeriodId, TypePredictionChangeBO typePredictionChangeBo);

    /**
     * Delete a type prediction change
     */
    boolean delete(Long typePredictionChangeId);

}
