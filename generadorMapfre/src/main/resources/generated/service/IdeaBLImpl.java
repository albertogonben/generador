package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.Idea;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.IdeaRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class IdeaBLImpl implements IIdeaBL {

	private IdeaRepository ideaRepository;
	private MapperFacade mapperIdea;

	@Autowired
	public IdeaBLImpl(IdeaRepository ideaRepository) {
		this.ideaRepository = ideaRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Idea.class, IdeaBO.class).byDefault().register();
		this.mapperIdea = mapperFactory.getMapperFacade();

	}

	@Override
	public List<IdeaBO> getAll() {
		log.debug("IdeaBLImpl:getAll [START]");
		
		List<IdeaBO> ideas = new ArrayList<IdeaBO>();

		List<Idea> ideaEntities = ideaRepository.findAll();
		for (Idea ideaEntity : ideaEntities) {
			ideas.add(mapperIdea.map(ideaEntity, IdeaBO.class));
		}
		log.debug("IdeaBLImpl:getAll [END]");
		return ideas;
	}

	@Override
	public IdeaBO add(IdeaBO ideaBO) {
		log.debug("IdeaBLImpl:add [START]");
		Idea ideaEntity = mapperIdea.map(ideaBO, Idea.class);

		Util.getDateUser(ideaEntity, "INSERT");

		log.debug("IdeaBLImpl:add [END]");
		return mapperIdea.map(ideaRepository.save(ideaEntity), IdeaBO.class);
	}

	@Override
	public IdeaBO update(Long ideaId, IdeaBO ideaBO) {
		Idea ideaEntity = ideaRepository.getOne(ideaId);
		if (ideaEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(ideaEntity, "UPDATE");
			
			return mapperIdea.map(ideaRepository.save(ideaEntity), IdeaBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long ideaId) {
		Idea ideaEntity = ideaRepository.getOne(ideaId);
		if (ideaEntity != null) {
		
			//TODO Baja logica o fisica?
		
			ideaEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(ideaEntity, "UPDATE");
			
			mapperIdea.map(ideaRepository.save(ideaEntity), IdeaBO.class);
			
			return true;
		}

		return false;
	}

}
