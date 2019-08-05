package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ILanguageBL {

	/**
	 * Get all language
	 */
	List<LanguageBO> getAll();
	
	/**
	 * Add a language
	 */
	LanguageBO add(LanguageBO languageBo);

	/**
	 * Update a language
	 */
	LanguageBO update(Long closePeriodId, LanguageBO languageBo);

    /**
     * Delete a language
     */
    boolean delete(Long languageId);

}
