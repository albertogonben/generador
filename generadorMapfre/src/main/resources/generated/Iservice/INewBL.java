package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface INewBL {

	/**
	 * Get all new
	 */
	List<NewBO> getAll();
	
	/**
	 * Add a new
	 */
	NewBO add(NewBO newBo);

	/**
	 * Update a new
	 */
	NewBO update(Long newId, NewBO newBo);

    /**
     * Delete a new
     */
    boolean delete(Long newId);

}
