package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeStateNodeBL {

	/**
	 * Get all type state node
	 */
	List<TypeStateNodeBO> getAll();
	
	/**
	 * Add a type state node
	 */
	TypeStateNodeBO add(TypeStateNodeBO typeStateNodeBo);

	/**
	 * Update a type state node
	 */
	TypeStateNodeBO update(Long closePeriodId, TypeStateNodeBO typeStateNodeBo);

    /**
     * Delete a type state node
     */
    boolean delete(Long typeStateNodeId);

}
