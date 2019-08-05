package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeStateIdea;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeStateIdeaRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeStateIdeaBLImpl implements ITypeStateIdeaBL {

	private TypeStateIdeaRepository typeStateIdeaRepository;
	private MapperFacade mapperTypeStateIdea;

	@Autowired
	public TypeStateIdeaBLImpl(TypeStateIdeaRepository typeStateIdeaRepository) {
		this.typeStateIdeaRepository = typeStateIdeaRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeStateIdea.class, TypeStateIdeaBO.class).byDefault().register();
		this.mapperTypeStateIdea = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeStateIdeaBO> getAll() {
		log.debug("TypeStateIdeaBLImpl:getAll [START]");
		
		List<TypeStateIdeaBO> typeStateIdeas = new ArrayList<TypeStateIdeaBO>();

		List<TypeStateIdea> typeStateIdeaEntities = typeStateIdeaRepository.findAll();
		for (TypeStateIdea typeStateIdeaEntity : typeStateIdeaEntities) {
			typeStateIdeas.add(mapperTypeStateIdea.map(typeStateIdeaEntity, TypeStateIdeaBO.class));
		}
		log.debug("TypeStateIdeaBLImpl:getAll [END]");
		return typeStateIdeas;
	}

	@Override
	public TypeStateIdeaBO add(TypeStateIdeaBO typeStateIdeaBO) {
		log.debug("TypeStateIdeaBLImpl:add [START]");
		TypeStateIdea typeStateIdeaEntity = mapperTypeStateIdea.map(typeStateIdeaBO, TypeStateIdea.class);

		Util.getDateUser(typeStateIdeaEntity, "INSERT");

		log.debug("TypeStateIdeaBLImpl:add [END]");
		return mapperTypeStateIdea.map(typeStateIdeaRepository.save(typeStateIdeaEntity), TypeStateIdeaBO.class);
	}

	@Override
	public TypeStateIdeaBO update(Long typeStateIdeaId, TypeStateIdeaBO typeStateIdeaBO) {
		TypeStateIdea typeStateIdeaEntity = typeStateIdeaRepository.getOne(typeStateIdeaId);
		if (typeStateIdeaEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeStateIdeaEntity, "UPDATE");
			
			return mapperTypeStateIdea.map(typeStateIdeaRepository.save(typeStateIdeaEntity), TypeStateIdeaBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeStateIdeaId) {
		TypeStateIdea typeStateIdeaEntity = typeStateIdeaRepository.getOne(typeStateIdeaId);
		if (typeStateIdeaEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeStateIdeaEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeStateIdeaEntity, "UPDATE");
			
			mapperTypeStateIdea.map(typeStateIdeaRepository.save(typeStateIdeaEntity), TypeStateIdeaBO.class);
			
			return true;
		}

		return false;
	}

}
