package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.UserLanguage;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.UserLanguageRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class UserLanguageBLImpl implements IUserLanguageBL {

	private UserLanguageRepository userLanguageRepository;
	private MapperFacade mapperUserLanguage;

	@Autowired
	public UserLanguageBLImpl(UserLanguageRepository userLanguageRepository) {
		this.userLanguageRepository = userLanguageRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(UserLanguage.class, UserLanguageBO.class).byDefault().register();
		this.mapperUserLanguage = mapperFactory.getMapperFacade();

	}

	@Override
	public List<UserLanguageBO> getAll() {
		log.debug("UserLanguageBLImpl:getAll [START]");
		
		List<UserLanguageBO> userLanguages = new ArrayList<UserLanguageBO>();

		List<UserLanguage> userLanguageEntities = userLanguageRepository.findAll();
		for (UserLanguage userLanguageEntity : userLanguageEntities) {
			userLanguages.add(mapperUserLanguage.map(userLanguageEntity, UserLanguageBO.class));
		}
		log.debug("UserLanguageBLImpl:getAll [END]");
		return userLanguages;
	}

	@Override
	public UserLanguageBO add(UserLanguageBO userLanguageBO) {
		log.debug("UserLanguageBLImpl:add [START]");
		UserLanguage userLanguageEntity = mapperUserLanguage.map(userLanguageBO, UserLanguage.class);

		Util.getDateUser(userLanguageEntity, "INSERT");

		log.debug("UserLanguageBLImpl:add [END]");
		return mapperUserLanguage.map(userLanguageRepository.save(userLanguageEntity), UserLanguageBO.class);
	}

	@Override
	public UserLanguageBO update(Long userLanguageId, UserLanguageBO userLanguageBO) {
		UserLanguage userLanguageEntity = userLanguageRepository.getOne(userLanguageId);
		if (userLanguageEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(userLanguageEntity, "UPDATE");
			
			return mapperUserLanguage.map(userLanguageRepository.save(userLanguageEntity), UserLanguageBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long userLanguageId) {
		UserLanguage userLanguageEntity = userLanguageRepository.getOne(userLanguageId);
		if (userLanguageEntity != null) {
		
			//TODO Baja logica o fisica?
		
			userLanguageEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(userLanguageEntity, "UPDATE");
			
			mapperUserLanguage.map(userLanguageRepository.save(userLanguageEntity), UserLanguageBO.class);
			
			return true;
		}

		return false;
	}

}
