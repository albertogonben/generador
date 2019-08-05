package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeCriquiteAlarm;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeCriquiteAlarmRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeCriquiteAlarmBLImpl implements ITypeCriquiteAlarmBL {

	private TypeCriquiteAlarmRepository typeCriquiteAlarmRepository;
	private MapperFacade mapperTypeCriquiteAlarm;

	@Autowired
	public TypeCriquiteAlarmBLImpl(TypeCriquiteAlarmRepository typeCriquiteAlarmRepository) {
		this.typeCriquiteAlarmRepository = typeCriquiteAlarmRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeCriquiteAlarm.class, TypeCriquiteAlarmBO.class).byDefault().register();
		this.mapperTypeCriquiteAlarm = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeCriquiteAlarmBO> getAll() {
		log.debug("TypeCriquiteAlarmBLImpl:getAll [START]");
		
		List<TypeCriquiteAlarmBO> typeCriquiteAlarms = new ArrayList<TypeCriquiteAlarmBO>();

		List<TypeCriquiteAlarm> typeCriquiteAlarmEntities = typeCriquiteAlarmRepository.findAll();
		for (TypeCriquiteAlarm typeCriquiteAlarmEntity : typeCriquiteAlarmEntities) {
			typeCriquiteAlarms.add(mapperTypeCriquiteAlarm.map(typeCriquiteAlarmEntity, TypeCriquiteAlarmBO.class));
		}
		log.debug("TypeCriquiteAlarmBLImpl:getAll [END]");
		return typeCriquiteAlarms;
	}

	@Override
	public TypeCriquiteAlarmBO add(TypeCriquiteAlarmBO typeCriquiteAlarmBO) {
		log.debug("TypeCriquiteAlarmBLImpl:add [START]");
		TypeCriquiteAlarm typeCriquiteAlarmEntity = mapperTypeCriquiteAlarm.map(typeCriquiteAlarmBO, TypeCriquiteAlarm.class);

		Util.getDateUser(typeCriquiteAlarmEntity, "INSERT");

		log.debug("TypeCriquiteAlarmBLImpl:add [END]");
		return mapperTypeCriquiteAlarm.map(typeCriquiteAlarmRepository.save(typeCriquiteAlarmEntity), TypeCriquiteAlarmBO.class);
	}

	@Override
	public TypeCriquiteAlarmBO update(Long typeCriquiteAlarmId, TypeCriquiteAlarmBO typeCriquiteAlarmBO) {
		TypeCriquiteAlarm typeCriquiteAlarmEntity = typeCriquiteAlarmRepository.getOne(typeCriquiteAlarmId);
		if (typeCriquiteAlarmEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeCriquiteAlarmEntity, "UPDATE");
			
			return mapperTypeCriquiteAlarm.map(typeCriquiteAlarmRepository.save(typeCriquiteAlarmEntity), TypeCriquiteAlarmBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeCriquiteAlarmId) {
		TypeCriquiteAlarm typeCriquiteAlarmEntity = typeCriquiteAlarmRepository.getOne(typeCriquiteAlarmId);
		if (typeCriquiteAlarmEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeCriquiteAlarmEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeCriquiteAlarmEntity, "UPDATE");
			
			mapperTypeCriquiteAlarm.map(typeCriquiteAlarmRepository.save(typeCriquiteAlarmEntity), TypeCriquiteAlarmBO.class);
			
			return true;
		}

		return false;
	}

}
