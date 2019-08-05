package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeLevelLanguageBL {

	/**
	 * Get all type level language
	 */
	List<TypeLevelLanguageBO> getAll();
	
	/**
	 * Add a type level language
	 */
	TypeLevelLanguageBO add(TypeLevelLanguageBO typeLevelLanguageBo);

	/**
	 * Update a type level language
	 */
	TypeLevelLanguageBO update(Long closePeriodId, TypeLevelLanguageBO typeLevelLanguageBo);

    /**
     * Delete a type level language
     */
    boolean delete(Long typeLevelLanguageId);

}
