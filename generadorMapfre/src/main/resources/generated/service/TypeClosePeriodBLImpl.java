package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeClosePeriod;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeClosePeriodRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeClosePeriodBLImpl implements ITypeClosePeriodBL {

	private TypeClosePeriodRepository typeClosePeriodRepository;
	private MapperFacade mapperTypeClosePeriod;

	@Autowired
	public TypeClosePeriodBLImpl(TypeClosePeriodRepository typeClosePeriodRepository) {
		this.typeClosePeriodRepository = typeClosePeriodRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeClosePeriod.class, TypeClosePeriodBO.class).byDefault().register();
		this.mapperTypeClosePeriod = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeClosePeriodBO> getAll() {
		log.debug("TypeClosePeriodBLImpl:getAll [START]");
		
		List<TypeClosePeriodBO> typeClosePeriods = new ArrayList<TypeClosePeriodBO>();

		List<TypeClosePeriod> typeClosePeriodEntities = typeClosePeriodRepository.findAll();
		for (TypeClosePeriod typeClosePeriodEntity : typeClosePeriodEntities) {
			typeClosePeriods.add(mapperTypeClosePeriod.map(typeClosePeriodEntity, TypeClosePeriodBO.class));
		}
		log.debug("TypeClosePeriodBLImpl:getAll [END]");
		return typeClosePeriods;
	}

	@Override
	public TypeClosePeriodBO add(TypeClosePeriodBO typeClosePeriodBO) {
		log.debug("TypeClosePeriodBLImpl:add [START]");
		TypeClosePeriod typeClosePeriodEntity = mapperTypeClosePeriod.map(typeClosePeriodBO, TypeClosePeriod.class);

		Util.getDateUser(typeClosePeriodEntity, "INSERT");

		log.debug("TypeClosePeriodBLImpl:add [END]");
		return mapperTypeClosePeriod.map(typeClosePeriodRepository.save(typeClosePeriodEntity), TypeClosePeriodBO.class);
	}

	@Override
	public TypeClosePeriodBO update(Long typeClosePeriodId, TypeClosePeriodBO typeClosePeriodBO) {
		TypeClosePeriod typeClosePeriodEntity = typeClosePeriodRepository.getOne(typeClosePeriodId);
		if (typeClosePeriodEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeClosePeriodEntity, "UPDATE");
			
			return mapperTypeClosePeriod.map(typeClosePeriodRepository.save(typeClosePeriodEntity), TypeClosePeriodBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeClosePeriodId) {
		TypeClosePeriod typeClosePeriodEntity = typeClosePeriodRepository.getOne(typeClosePeriodId);
		if (typeClosePeriodEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeClosePeriodEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeClosePeriodEntity, "UPDATE");
			
			mapperTypeClosePeriod.map(typeClosePeriodRepository.save(typeClosePeriodEntity), TypeClosePeriodBO.class);
			
			return true;
		}

		return false;
	}

}
