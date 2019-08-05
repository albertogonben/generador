package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IOrganizationalStructureBL {

	/**
	 * Get all organizational structure
	 */
	List<OrganizationalStructureBO> getAll();
	
	/**
	 * Add a organizational structure
	 */
	OrganizationalStructureBO add(OrganizationalStructureBO organizationalStructureBo);

	/**
	 * Update a organizational structure
	 */
	OrganizationalStructureBO update(Long closePeriodId, OrganizationalStructureBO organizationalStructureBo);

    /**
     * Delete a organizational structure
     */
    boolean delete(Long organizationalStructureId);

}
