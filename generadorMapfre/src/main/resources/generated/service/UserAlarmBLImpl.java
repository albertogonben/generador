package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.UserAlarm;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.UserAlarmRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class UserAlarmBLImpl implements IUserAlarmBL {

	private UserAlarmRepository userAlarmRepository;
	private MapperFacade mapperUserAlarm;

	@Autowired
	public UserAlarmBLImpl(UserAlarmRepository userAlarmRepository) {
		this.userAlarmRepository = userAlarmRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(UserAlarm.class, UserAlarmBO.class).byDefault().register();
		this.mapperUserAlarm = mapperFactory.getMapperFacade();

	}

	@Override
	public List<UserAlarmBO> getAll() {
		log.debug("UserAlarmBLImpl:getAll [START]");
		
		List<UserAlarmBO> userAlarms = new ArrayList<UserAlarmBO>();

		List<UserAlarm> userAlarmEntities = userAlarmRepository.findAll();
		for (UserAlarm userAlarmEntity : userAlarmEntities) {
			userAlarms.add(mapperUserAlarm.map(userAlarmEntity, UserAlarmBO.class));
		}
		log.debug("UserAlarmBLImpl:getAll [END]");
		return userAlarms;
	}

	@Override
	public UserAlarmBO add(UserAlarmBO userAlarmBO) {
		log.debug("UserAlarmBLImpl:add [START]");
		UserAlarm userAlarmEntity = mapperUserAlarm.map(userAlarmBO, UserAlarm.class);

		Util.getDateUser(userAlarmEntity, "INSERT");

		log.debug("UserAlarmBLImpl:add [END]");
		return mapperUserAlarm.map(userAlarmRepository.save(userAlarmEntity), UserAlarmBO.class);
	}

	@Override
	public UserAlarmBO update(Long userAlarmId, UserAlarmBO userAlarmBO) {
		UserAlarm userAlarmEntity = userAlarmRepository.getOne(userAlarmId);
		if (userAlarmEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(userAlarmEntity, "UPDATE");
			
			return mapperUserAlarm.map(userAlarmRepository.save(userAlarmEntity), UserAlarmBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long userAlarmId) {
		UserAlarm userAlarmEntity = userAlarmRepository.getOne(userAlarmId);
		if (userAlarmEntity != null) {
		
			//TODO Baja logica o fisica?
		
			userAlarmEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(userAlarmEntity, "UPDATE");
			
			mapperUserAlarm.map(userAlarmRepository.save(userAlarmEntity), UserAlarmBO.class);
			
			return true;
		}

		return false;
	}

}
