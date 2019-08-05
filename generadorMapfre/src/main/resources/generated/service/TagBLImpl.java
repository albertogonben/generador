package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.Tag;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TagRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TagBLImpl implements ITagBL {

	private TagRepository tagRepository;
	private MapperFacade mapperTag;

	@Autowired
	public TagBLImpl(TagRepository tagRepository) {
		this.tagRepository = tagRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Tag.class, TagBO.class).byDefault().register();
		this.mapperTag = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TagBO> getAll() {
		log.debug("TagBLImpl:getAll [START]");
		
		List<TagBO> tags = new ArrayList<TagBO>();

		List<Tag> tagEntities = tagRepository.findAll();
		for (Tag tagEntity : tagEntities) {
			tags.add(mapperTag.map(tagEntity, TagBO.class));
		}
		log.debug("TagBLImpl:getAll [END]");
		return tags;
	}

	@Override
	public TagBO add(TagBO tagBO) {
		log.debug("TagBLImpl:add [START]");
		Tag tagEntity = mapperTag.map(tagBO, Tag.class);

		Util.getDateUser(tagEntity, "INSERT");

		log.debug("TagBLImpl:add [END]");
		return mapperTag.map(tagRepository.save(tagEntity), TagBO.class);
	}

	@Override
	public TagBO update(Long tagId, TagBO tagBO) {
		Tag tagEntity = tagRepository.getOne(tagId);
		if (tagEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(tagEntity, "UPDATE");
			
			return mapperTag.map(tagRepository.save(tagEntity), TagBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long tagId) {
		Tag tagEntity = tagRepository.getOne(tagId);
		if (tagEntity != null) {
		
			//TODO Baja logica o fisica?
		
			tagEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(tagEntity, "UPDATE");
			
			mapperTag.map(tagRepository.save(tagEntity), TagBO.class);
			
			return true;
		}

		return false;
	}

}
