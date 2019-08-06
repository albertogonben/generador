package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ICelebratedSentenceBL {

	/**
	 * Get all celebrated sentence
	 */
	List<CelebratedSentenceBO> getAll();
	
	/**
	 * Add a celebrated sentence
	 */
	CelebratedSentenceBO add(CelebratedSentenceBO celebratedSentenceBo);

	/**
	 * Update a celebrated sentence
	 */
	CelebratedSentenceBO update(Long celebratedSentenceId, CelebratedSentenceBO celebratedSentenceBo);

    /**
     * Delete a celebrated sentence
     */
    boolean delete(Long celebratedSentenceId);

}
