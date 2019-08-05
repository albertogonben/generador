package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeOrganizationalStructureBL {

	/**
	 * Get all type organizational structure
	 */
	List<TypeOrganizationalStructureBO> getAll();
	
	/**
	 * Add a type organizational structure
	 */
	TypeOrganizationalStructureBO add(TypeOrganizationalStructureBO typeOrganizationalStructureBo);

	/**
	 * Update a type organizational structure
	 */
	TypeOrganizationalStructureBO update(Long closePeriodId, TypeOrganizationalStructureBO typeOrganizationalStructureBo);

    /**
     * Delete a type organizational structure
     */
    boolean delete(Long typeOrganizationalStructureId);

}
