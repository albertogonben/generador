package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.UserCategory;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.UserCategoryRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class UserCategoryBLImpl implements IUserCategoryBL {

	private UserCategoryRepository userCategoryRepository;
	private MapperFacade mapperUserCategory;

	@Autowired
	public UserCategoryBLImpl(UserCategoryRepository userCategoryRepository) {
		this.userCategoryRepository = userCategoryRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(UserCategory.class, UserCategoryBO.class).byDefault().register();
		this.mapperUserCategory = mapperFactory.getMapperFacade();

	}

	@Override
	public List<UserCategoryBO> getAll() {
		log.debug("UserCategoryBLImpl:getAll [START]");
		
		List<UserCategoryBO> userCategorys = new ArrayList<UserCategoryBO>();

		List<UserCategory> userCategoryEntities = userCategoryRepository.findAll();
		for (UserCategory userCategoryEntity : userCategoryEntities) {
			userCategorys.add(mapperUserCategory.map(userCategoryEntity, UserCategoryBO.class));
		}
		log.debug("UserCategoryBLImpl:getAll [END]");
		return userCategorys;
	}

	@Override
	public UserCategoryBO add(UserCategoryBO userCategoryBO) {
		log.debug("UserCategoryBLImpl:add [START]");
		UserCategory userCategoryEntity = mapperUserCategory.map(userCategoryBO, UserCategory.class);

		Util.getDateUser(userCategoryEntity, "INSERT");

		log.debug("UserCategoryBLImpl:add [END]");
		return mapperUserCategory.map(userCategoryRepository.save(userCategoryEntity), UserCategoryBO.class);
	}

	@Override
	public UserCategoryBO update(Long userCategoryId, UserCategoryBO userCategoryBO) {
		UserCategory userCategoryEntity = userCategoryRepository.getOne(userCategoryId);
		if (userCategoryEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(userCategoryEntity, "UPDATE");
			
			return mapperUserCategory.map(userCategoryRepository.save(userCategoryEntity), UserCategoryBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long userCategoryId) {
		UserCategory userCategoryEntity = userCategoryRepository.getOne(userCategoryId);
		if (userCategoryEntity != null) {
		
			//TODO Baja logica o fisica?
		
			userCategoryEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(userCategoryEntity, "UPDATE");
			
			mapperUserCategory.map(userCategoryRepository.save(userCategoryEntity), UserCategoryBO.class);
			
			return true;
		}

		return false;
	}

}
