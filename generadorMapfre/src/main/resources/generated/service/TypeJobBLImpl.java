package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeJob;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeJobRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeJobBLImpl implements ITypeJobBL {

	private TypeJobRepository typeJobRepository;
	private MapperFacade mapperTypeJob;

	@Autowired
	public TypeJobBLImpl(TypeJobRepository typeJobRepository) {
		this.typeJobRepository = typeJobRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeJob.class, TypeJobBO.class).byDefault().register();
		this.mapperTypeJob = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeJobBO> getAll() {
		log.debug("TypeJobBLImpl:getAll [START]");
		
		List<TypeJobBO> typeJobs = new ArrayList<TypeJobBO>();

		List<TypeJob> typeJobEntities = typeJobRepository.findAll();
		for (TypeJob typeJobEntity : typeJobEntities) {
			typeJobs.add(mapperTypeJob.map(typeJobEntity, TypeJobBO.class));
		}
		log.debug("TypeJobBLImpl:getAll [END]");
		return typeJobs;
	}

	@Override
	public TypeJobBO add(TypeJobBO typeJobBO) {
		log.debug("TypeJobBLImpl:add [START]");
		TypeJob typeJobEntity = mapperTypeJob.map(typeJobBO, TypeJob.class);

		Util.getDateUser(typeJobEntity, "INSERT");

		log.debug("TypeJobBLImpl:add [END]");
		return mapperTypeJob.map(typeJobRepository.save(typeJobEntity), TypeJobBO.class);
	}

	@Override
	public TypeJobBO update(Long typeJobId, TypeJobBO typeJobBO) {
		TypeJob typeJobEntity = typeJobRepository.getOne(typeJobId);
		if (typeJobEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeJobEntity, "UPDATE");
			
			return mapperTypeJob.map(typeJobRepository.save(typeJobEntity), TypeJobBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeJobId) {
		TypeJob typeJobEntity = typeJobRepository.getOne(typeJobId);
		if (typeJobEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeJobEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeJobEntity, "UPDATE");
			
			mapperTypeJob.map(typeJobRepository.save(typeJobEntity), TypeJobBO.class);
			
			return true;
		}

		return false;
	}

}
