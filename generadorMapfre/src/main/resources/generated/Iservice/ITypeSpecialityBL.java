package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeSpecialityBL {

	/**
	 * Get all type speciality
	 */
	List<TypeSpecialityBO> getAll();
	
	/**
	 * Add a type speciality
	 */
	TypeSpecialityBO add(TypeSpecialityBO typeSpecialityBo);

	/**
	 * Update a type speciality
	 */
	TypeSpecialityBO update(Long closePeriodId, TypeSpecialityBO typeSpecialityBo);

    /**
     * Delete a type speciality
     */
    boolean delete(Long typeSpecialityId);

}
