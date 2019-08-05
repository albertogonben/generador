package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.UserTraining;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.UserTrainingRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class UserTrainingBLImpl implements IUserTrainingBL {

	private UserTrainingRepository userTrainingRepository;
	private MapperFacade mapperUserTraining;

	@Autowired
	public UserTrainingBLImpl(UserTrainingRepository userTrainingRepository) {
		this.userTrainingRepository = userTrainingRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(UserTraining.class, UserTrainingBO.class).byDefault().register();
		this.mapperUserTraining = mapperFactory.getMapperFacade();

	}

	@Override
	public List<UserTrainingBO> getAll() {
		log.debug("UserTrainingBLImpl:getAll [START]");
		
		List<UserTrainingBO> userTrainings = new ArrayList<UserTrainingBO>();

		List<UserTraining> userTrainingEntities = userTrainingRepository.findAll();
		for (UserTraining userTrainingEntity : userTrainingEntities) {
			userTrainings.add(mapperUserTraining.map(userTrainingEntity, UserTrainingBO.class));
		}
		log.debug("UserTrainingBLImpl:getAll [END]");
		return userTrainings;
	}

	@Override
	public UserTrainingBO add(UserTrainingBO userTrainingBO) {
		log.debug("UserTrainingBLImpl:add [START]");
		UserTraining userTrainingEntity = mapperUserTraining.map(userTrainingBO, UserTraining.class);

		Util.getDateUser(userTrainingEntity, "INSERT");

		log.debug("UserTrainingBLImpl:add [END]");
		return mapperUserTraining.map(userTrainingRepository.save(userTrainingEntity), UserTrainingBO.class);
	}

	@Override
	public UserTrainingBO update(Long userTrainingId, UserTrainingBO userTrainingBO) {
		UserTraining userTrainingEntity = userTrainingRepository.getOne(userTrainingId);
		if (userTrainingEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(userTrainingEntity, "UPDATE");
			
			return mapperUserTraining.map(userTrainingRepository.save(userTrainingEntity), UserTrainingBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long userTrainingId) {
		UserTraining userTrainingEntity = userTrainingRepository.getOne(userTrainingId);
		if (userTrainingEntity != null) {
		
			//TODO Baja logica o fisica?
		
			userTrainingEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(userTrainingEntity, "UPDATE");
			
			mapperUserTraining.map(userTrainingRepository.save(userTrainingEntity), UserTrainingBO.class);
			
			return true;
		}

		return false;
	}

}
