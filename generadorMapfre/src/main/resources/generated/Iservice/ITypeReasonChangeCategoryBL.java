package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeReasonChangeCategoryBL {

	/**
	 * Get all type reason change category
	 */
	List<TypeReasonChangeCategoryBO> getAll();
	
	/**
	 * Add a type reason change category
	 */
	TypeReasonChangeCategoryBO add(TypeReasonChangeCategoryBO typeReasonChangeCategoryBo);

	/**
	 * Update a type reason change category
	 */
	TypeReasonChangeCategoryBO update(Long closePeriodId, TypeReasonChangeCategoryBO typeReasonChangeCategoryBo);

    /**
     * Delete a type reason change category
     */
    boolean delete(Long typeReasonChangeCategoryId);

}
