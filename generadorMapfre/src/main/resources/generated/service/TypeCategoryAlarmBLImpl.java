package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeCategoryAlarm;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeCategoryAlarmRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeCategoryAlarmBLImpl implements ITypeCategoryAlarmBL {

	private TypeCategoryAlarmRepository typeCategoryAlarmRepository;
	private MapperFacade mapperTypeCategoryAlarm;

	@Autowired
	public TypeCategoryAlarmBLImpl(TypeCategoryAlarmRepository typeCategoryAlarmRepository) {
		this.typeCategoryAlarmRepository = typeCategoryAlarmRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeCategoryAlarm.class, TypeCategoryAlarmBO.class).byDefault().register();
		this.mapperTypeCategoryAlarm = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeCategoryAlarmBO> getAll() {
		log.debug("TypeCategoryAlarmBLImpl:getAll [START]");
		
		List<TypeCategoryAlarmBO> typeCategoryAlarms = new ArrayList<TypeCategoryAlarmBO>();

		List<TypeCategoryAlarm> typeCategoryAlarmEntities = typeCategoryAlarmRepository.findAll();
		for (TypeCategoryAlarm typeCategoryAlarmEntity : typeCategoryAlarmEntities) {
			typeCategoryAlarms.add(mapperTypeCategoryAlarm.map(typeCategoryAlarmEntity, TypeCategoryAlarmBO.class));
		}
		log.debug("TypeCategoryAlarmBLImpl:getAll [END]");
		return typeCategoryAlarms;
	}

	@Override
	public TypeCategoryAlarmBO add(TypeCategoryAlarmBO typeCategoryAlarmBO) {
		log.debug("TypeCategoryAlarmBLImpl:add [START]");
		TypeCategoryAlarm typeCategoryAlarmEntity = mapperTypeCategoryAlarm.map(typeCategoryAlarmBO, TypeCategoryAlarm.class);

		Util.getDateUser(typeCategoryAlarmEntity, "INSERT");

		log.debug("TypeCategoryAlarmBLImpl:add [END]");
		return mapperTypeCategoryAlarm.map(typeCategoryAlarmRepository.save(typeCategoryAlarmEntity), TypeCategoryAlarmBO.class);
	}

	@Override
	public TypeCategoryAlarmBO update(Long typeCategoryAlarmId, TypeCategoryAlarmBO typeCategoryAlarmBO) {
		TypeCategoryAlarm typeCategoryAlarmEntity = typeCategoryAlarmRepository.getOne(typeCategoryAlarmId);
		if (typeCategoryAlarmEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeCategoryAlarmEntity, "UPDATE");
			
			return mapperTypeCategoryAlarm.map(typeCategoryAlarmRepository.save(typeCategoryAlarmEntity), TypeCategoryAlarmBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeCategoryAlarmId) {
		TypeCategoryAlarm typeCategoryAlarmEntity = typeCategoryAlarmRepository.getOne(typeCategoryAlarmId);
		if (typeCategoryAlarmEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeCategoryAlarmEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeCategoryAlarmEntity, "UPDATE");
			
			mapperTypeCategoryAlarm.map(typeCategoryAlarmRepository.save(typeCategoryAlarmEntity), TypeCategoryAlarmBO.class);
			
			return true;
		}

		return false;
	}

}
