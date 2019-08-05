package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITypeStateIdeaBL {

	/**
	 * Get all type state idea
	 */
	List<TypeStateIdeaBO> getAll();
	
	/**
	 * Add a type state idea
	 */
	TypeStateIdeaBO add(TypeStateIdeaBO typeStateIdeaBo);

	/**
	 * Update a type state idea
	 */
	TypeStateIdeaBO update(Long closePeriodId, TypeStateIdeaBO typeStateIdeaBo);

    /**
     * Delete a type state idea
     */
    boolean delete(Long typeStateIdeaId);

}
