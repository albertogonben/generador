package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeReasonCloseBL {

	/**
	 * Get all type reason close
	 */
	List<TypeReasonCloseBO> getAll();
	
	/**
	 * Add a type reason close
	 */
	TypeReasonCloseBO add(TypeReasonCloseBO typeReasonCloseBo);

	/**
	 * Update a type reason close
	 */
	TypeReasonCloseBO update(Long closePeriodId, TypeReasonCloseBO typeReasonCloseBo);

    /**
     * Delete a type reason close
     */
    boolean delete(Long typeReasonCloseId);

}
