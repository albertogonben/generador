package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeTraceBL {

	/**
	 * Get all type trace
	 */
	List<TypeTraceBO> getAll();
	
	/**
	 * Add a type trace
	 */
	TypeTraceBO add(TypeTraceBO typeTraceBo);

	/**
	 * Update a type trace
	 */
	TypeTraceBO update(Long closePeriodId, TypeTraceBO typeTraceBo);

    /**
     * Delete a type trace
     */
    boolean delete(Long typeTraceId);

}
