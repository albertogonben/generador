package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeCertificationBL {

	/**
	 * Get all type certification
	 */
	List<TypeCertificationBO> getAll();
	
	/**
	 * Add a type certification
	 */
	TypeCertificationBO add(TypeCertificationBO typeCertificationBo);

	/**
	 * Update a type certification
	 */
	TypeCertificationBO update(Long closePeriodId, TypeCertificationBO typeCertificationBo);

    /**
     * Delete a type certification
     */
    boolean delete(Long typeCertificationId);

}
