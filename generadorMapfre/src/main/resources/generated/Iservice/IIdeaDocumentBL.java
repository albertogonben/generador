package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IIdeaDocumentBL {

	/**
	 * Get all idea document
	 */
	List<IdeaDocumentBO> getAll();
	
	/**
	 * Add a idea document
	 */
	IdeaDocumentBO add(IdeaDocumentBO ideaDocumentBo);

	/**
	 * Update a idea document
	 */
	IdeaDocumentBO update(Long closePeriodId, IdeaDocumentBO ideaDocumentBo);

    /**
     * Delete a idea document
     */
    boolean delete(Long ideaDocumentId);

}
