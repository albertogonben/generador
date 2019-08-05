package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ITagBL {

	/**
	 * Get all tag
	 */
	List<TagBO> getAll();
	
	/**
	 * Add a tag
	 */
	TagBO add(TagBO tagBo);

	/**
	 * Update a tag
	 */
	TagBO update(Long closePeriodId, TagBO tagBo);

    /**
     * Delete a tag
     */
    boolean delete(Long tagId);

}
