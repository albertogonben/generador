package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeStateJob;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeStateJobRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeStateJobBLImpl implements ITypeStateJobBL {

	private TypeStateJobRepository typeStateJobRepository;
	private MapperFacade mapperTypeStateJob;

	@Autowired
	public TypeStateJobBLImpl(TypeStateJobRepository typeStateJobRepository) {
		this.typeStateJobRepository = typeStateJobRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeStateJob.class, TypeStateJobBO.class).byDefault().register();
		this.mapperTypeStateJob = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeStateJobBO> getAll() {
		log.debug("TypeStateJobBLImpl:getAll [START]");
		
		List<TypeStateJobBO> typeStateJobs = new ArrayList<TypeStateJobBO>();

		List<TypeStateJob> typeStateJobEntities = typeStateJobRepository.findAll();
		for (TypeStateJob typeStateJobEntity : typeStateJobEntities) {
			typeStateJobs.add(mapperTypeStateJob.map(typeStateJobEntity, TypeStateJobBO.class));
		}
		log.debug("TypeStateJobBLImpl:getAll [END]");
		return typeStateJobs;
	}

	@Override
	public TypeStateJobBO add(TypeStateJobBO typeStateJobBO) {
		log.debug("TypeStateJobBLImpl:add [START]");
		TypeStateJob typeStateJobEntity = mapperTypeStateJob.map(typeStateJobBO, TypeStateJob.class);

		Util.getDateUser(typeStateJobEntity, "INSERT");

		log.debug("TypeStateJobBLImpl:add [END]");
		return mapperTypeStateJob.map(typeStateJobRepository.save(typeStateJobEntity), TypeStateJobBO.class);
	}

	@Override
	public TypeStateJobBO update(Long typeStateJobId, TypeStateJobBO typeStateJobBO) {
		TypeStateJob typeStateJobEntity = typeStateJobRepository.getOne(typeStateJobId);
		if (typeStateJobEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeStateJobEntity, "UPDATE");
			
			return mapperTypeStateJob.map(typeStateJobRepository.save(typeStateJobEntity), TypeStateJobBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeStateJobId) {
		TypeStateJob typeStateJobEntity = typeStateJobRepository.getOne(typeStateJobId);
		if (typeStateJobEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeStateJobEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeStateJobEntity, "UPDATE");
			
			mapperTypeStateJob.map(typeStateJobRepository.save(typeStateJobEntity), TypeStateJobBO.class);
			
			return true;
		}

		return false;
	}

}
