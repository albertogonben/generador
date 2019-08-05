package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeTrace;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeTraceRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeTraceBLImpl implements ITypeTraceBL {

	private TypeTraceRepository typeTraceRepository;
	private MapperFacade mapperTypeTrace;

	@Autowired
	public TypeTraceBLImpl(TypeTraceRepository typeTraceRepository) {
		this.typeTraceRepository = typeTraceRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeTrace.class, TypeTraceBO.class).byDefault().register();
		this.mapperTypeTrace = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeTraceBO> getAll() {
		log.debug("TypeTraceBLImpl:getAll [START]");
		
		List<TypeTraceBO> typeTraces = new ArrayList<TypeTraceBO>();

		List<TypeTrace> typeTraceEntities = typeTraceRepository.findAll();
		for (TypeTrace typeTraceEntity : typeTraceEntities) {
			typeTraces.add(mapperTypeTrace.map(typeTraceEntity, TypeTraceBO.class));
		}
		log.debug("TypeTraceBLImpl:getAll [END]");
		return typeTraces;
	}

	@Override
	public TypeTraceBO add(TypeTraceBO typeTraceBO) {
		log.debug("TypeTraceBLImpl:add [START]");
		TypeTrace typeTraceEntity = mapperTypeTrace.map(typeTraceBO, TypeTrace.class);

		Util.getDateUser(typeTraceEntity, "INSERT");

		log.debug("TypeTraceBLImpl:add [END]");
		return mapperTypeTrace.map(typeTraceRepository.save(typeTraceEntity), TypeTraceBO.class);
	}

	@Override
	public TypeTraceBO update(Long typeTraceId, TypeTraceBO typeTraceBO) {
		TypeTrace typeTraceEntity = typeTraceRepository.getOne(typeTraceId);
		if (typeTraceEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeTraceEntity, "UPDATE");
			
			return mapperTypeTrace.map(typeTraceRepository.save(typeTraceEntity), TypeTraceBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeTraceId) {
		TypeTrace typeTraceEntity = typeTraceRepository.getOne(typeTraceId);
		if (typeTraceEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeTraceEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeTraceEntity, "UPDATE");
			
			mapperTypeTrace.map(typeTraceRepository.save(typeTraceEntity), TypeTraceBO.class);
			
			return true;
		}

		return false;
	}

}
