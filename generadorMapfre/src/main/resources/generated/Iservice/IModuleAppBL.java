package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IModuleAppBL {

	/**
	 * Get all module app
	 */
	List<ModuleAppBO> getAll();
	
	/**
	 * Add a module app
	 */
	ModuleAppBO add(ModuleAppBO moduleAppBo);

	/**
	 * Update a module app
	 */
	ModuleAppBO update(Long closePeriodId, ModuleAppBO moduleAppBo);

    /**
     * Delete a module app
     */
    boolean delete(Long moduleAppId);

}
