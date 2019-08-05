package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeAlarm;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeAlarmRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeAlarmBLImpl implements ITypeAlarmBL {

	private TypeAlarmRepository typeAlarmRepository;
	private MapperFacade mapperTypeAlarm;

	@Autowired
	public TypeAlarmBLImpl(TypeAlarmRepository typeAlarmRepository) {
		this.typeAlarmRepository = typeAlarmRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeAlarm.class, TypeAlarmBO.class).byDefault().register();
		this.mapperTypeAlarm = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeAlarmBO> getAll() {
		log.debug("TypeAlarmBLImpl:getAll [START]");
		
		List<TypeAlarmBO> typeAlarms = new ArrayList<TypeAlarmBO>();

		List<TypeAlarm> typeAlarmEntities = typeAlarmRepository.findAll();
		for (TypeAlarm typeAlarmEntity : typeAlarmEntities) {
			typeAlarms.add(mapperTypeAlarm.map(typeAlarmEntity, TypeAlarmBO.class));
		}
		log.debug("TypeAlarmBLImpl:getAll [END]");
		return typeAlarms;
	}

	@Override
	public TypeAlarmBO add(TypeAlarmBO typeAlarmBO) {
		log.debug("TypeAlarmBLImpl:add [START]");
		TypeAlarm typeAlarmEntity = mapperTypeAlarm.map(typeAlarmBO, TypeAlarm.class);

		Util.getDateUser(typeAlarmEntity, "INSERT");

		log.debug("TypeAlarmBLImpl:add [END]");
		return mapperTypeAlarm.map(typeAlarmRepository.save(typeAlarmEntity), TypeAlarmBO.class);
	}

	@Override
	public TypeAlarmBO update(Long typeAlarmId, TypeAlarmBO typeAlarmBO) {
		TypeAlarm typeAlarmEntity = typeAlarmRepository.getOne(typeAlarmId);
		if (typeAlarmEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeAlarmEntity, "UPDATE");
			
			return mapperTypeAlarm.map(typeAlarmRepository.save(typeAlarmEntity), TypeAlarmBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeAlarmId) {
		TypeAlarm typeAlarmEntity = typeAlarmRepository.getOne(typeAlarmId);
		if (typeAlarmEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeAlarmEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeAlarmEntity, "UPDATE");
			
			mapperTypeAlarm.map(typeAlarmRepository.save(typeAlarmEntity), TypeAlarmBO.class);
			
			return true;
		}

		return false;
	}

}
