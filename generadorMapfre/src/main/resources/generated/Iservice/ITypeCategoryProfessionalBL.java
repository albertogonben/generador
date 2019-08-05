package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeCategoryProfessionalBL {

	/**
	 * Get all type category professional
	 */
	List<TypeCategoryProfessionalBO> getAll();
	
	/**
	 * Add a type category professional
	 */
	TypeCategoryProfessionalBO add(TypeCategoryProfessionalBO typeCategoryProfessionalBo);

	/**
	 * Update a type category professional
	 */
	TypeCategoryProfessionalBO update(Long closePeriodId, TypeCategoryProfessionalBO typeCategoryProfessionalBo);

    /**
     * Delete a type category professional
     */
    boolean delete(Long typeCategoryProfessionalId);

}
