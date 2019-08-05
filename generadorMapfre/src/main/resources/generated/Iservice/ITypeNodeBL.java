package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeNodeBL {

	/**
	 * Get all type node
	 */
	List<TypeNodeBO> getAll();
	
	/**
	 * Add a type node
	 */
	TypeNodeBO add(TypeNodeBO typeNodeBo);

	/**
	 * Update a type node
	 */
	TypeNodeBO update(Long closePeriodId, TypeNodeBO typeNodeBo);

    /**
     * Delete a type node
     */
    boolean delete(Long typeNodeId);

}
