package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IIdeaBL {

	/**
	 * Get all idea
	 */
	List<IdeaBO> getAll();
	
	/**
	 * Add a idea
	 */
	IdeaBO add(IdeaBO ideaBo);

	/**
	 * Update a idea
	 */
	IdeaBO update(Long closePeriodId, IdeaBO ideaBo);

    /**
     * Delete a idea
     */
    boolean delete(Long ideaId);

}
