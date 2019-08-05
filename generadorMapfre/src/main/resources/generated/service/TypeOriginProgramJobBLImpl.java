package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeOriginProgramJob;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeOriginProgramJobRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeOriginProgramJobBLImpl implements ITypeOriginProgramJobBL {

	private TypeOriginProgramJobRepository typeOriginProgramJobRepository;
	private MapperFacade mapperTypeOriginProgramJob;

	@Autowired
	public TypeOriginProgramJobBLImpl(TypeOriginProgramJobRepository typeOriginProgramJobRepository) {
		this.typeOriginProgramJobRepository = typeOriginProgramJobRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeOriginProgramJob.class, TypeOriginProgramJobBO.class).byDefault().register();
		this.mapperTypeOriginProgramJob = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeOriginProgramJobBO> getAll() {
		log.debug("TypeOriginProgramJobBLImpl:getAll [START]");
		
		List<TypeOriginProgramJobBO> typeOriginProgramJobs = new ArrayList<TypeOriginProgramJobBO>();

		List<TypeOriginProgramJob> typeOriginProgramJobEntities = typeOriginProgramJobRepository.findAll();
		for (TypeOriginProgramJob typeOriginProgramJobEntity : typeOriginProgramJobEntities) {
			typeOriginProgramJobs.add(mapperTypeOriginProgramJob.map(typeOriginProgramJobEntity, TypeOriginProgramJobBO.class));
		}
		log.debug("TypeOriginProgramJobBLImpl:getAll [END]");
		return typeOriginProgramJobs;
	}

	@Override
	public TypeOriginProgramJobBO add(TypeOriginProgramJobBO typeOriginProgramJobBO) {
		log.debug("TypeOriginProgramJobBLImpl:add [START]");
		TypeOriginProgramJob typeOriginProgramJobEntity = mapperTypeOriginProgramJob.map(typeOriginProgramJobBO, TypeOriginProgramJob.class);

		Util.getDateUser(typeOriginProgramJobEntity, "INSERT");

		log.debug("TypeOriginProgramJobBLImpl:add [END]");
		return mapperTypeOriginProgramJob.map(typeOriginProgramJobRepository.save(typeOriginProgramJobEntity), TypeOriginProgramJobBO.class);
	}

	@Override
	public TypeOriginProgramJobBO update(Long typeOriginProgramJobId, TypeOriginProgramJobBO typeOriginProgramJobBO) {
		TypeOriginProgramJob typeOriginProgramJobEntity = typeOriginProgramJobRepository.getOne(typeOriginProgramJobId);
		if (typeOriginProgramJobEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeOriginProgramJobEntity, "UPDATE");
			
			return mapperTypeOriginProgramJob.map(typeOriginProgramJobRepository.save(typeOriginProgramJobEntity), TypeOriginProgramJobBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeOriginProgramJobId) {
		TypeOriginProgramJob typeOriginProgramJobEntity = typeOriginProgramJobRepository.getOne(typeOriginProgramJobId);
		if (typeOriginProgramJobEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeOriginProgramJobEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeOriginProgramJobEntity, "UPDATE");
			
			mapperTypeOriginProgramJob.map(typeOriginProgramJobRepository.save(typeOriginProgramJobEntity), TypeOriginProgramJobBO.class);
			
			return true;
		}

		return false;
	}

}
