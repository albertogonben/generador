package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeRoleBL {

	/**
	 * Get all type role
	 */
	List<TypeRoleBO> getAll();
	
	/**
	 * Add a type role
	 */
	TypeRoleBO add(TypeRoleBO typeRoleBo);

	/**
	 * Update a type role
	 */
	TypeRoleBO update(Long closePeriodId, TypeRoleBO typeRoleBo);

    /**
     * Delete a type role
     */
    boolean delete(Long typeRoleId);

}
