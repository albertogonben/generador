package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.UserAmap;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.UserAmapRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class UserAmapBLImpl implements IUserAmapBL {

	private UserAmapRepository userAmapRepository;
	private MapperFacade mapperUserAmap;

	@Autowired
	public UserAmapBLImpl(UserAmapRepository userAmapRepository) {
		this.userAmapRepository = userAmapRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(UserAmap.class, UserAmapBO.class).byDefault().register();
		this.mapperUserAmap = mapperFactory.getMapperFacade();

	}

	@Override
	public List<UserAmapBO> getAll() {
		log.debug("UserAmapBLImpl:getAll [START]");
		
		List<UserAmapBO> userAmaps = new ArrayList<UserAmapBO>();

		List<UserAmap> userAmapEntities = userAmapRepository.findAll();
		for (UserAmap userAmapEntity : userAmapEntities) {
			userAmaps.add(mapperUserAmap.map(userAmapEntity, UserAmapBO.class));
		}
		log.debug("UserAmapBLImpl:getAll [END]");
		return userAmaps;
	}

	@Override
	public UserAmapBO add(UserAmapBO userAmapBO) {
		log.debug("UserAmapBLImpl:add [START]");
		UserAmap userAmapEntity = mapperUserAmap.map(userAmapBO, UserAmap.class);

		Util.getDateUser(userAmapEntity, "INSERT");

		log.debug("UserAmapBLImpl:add [END]");
		return mapperUserAmap.map(userAmapRepository.save(userAmapEntity), UserAmapBO.class);
	}

	@Override
	public UserAmapBO update(Long userAmapId, UserAmapBO userAmapBO) {
		UserAmap userAmapEntity = userAmapRepository.getOne(userAmapId);
		if (userAmapEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(userAmapEntity, "UPDATE");
			
			return mapperUserAmap.map(userAmapRepository.save(userAmapEntity), UserAmapBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long userAmapId) {
		UserAmap userAmapEntity = userAmapRepository.getOne(userAmapId);
		if (userAmapEntity != null) {
		
			//TODO Baja logica o fisica?
		
			userAmapEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(userAmapEntity, "UPDATE");
			
			mapperUserAmap.map(userAmapRepository.save(userAmapEntity), UserAmapBO.class);
			
			return true;
		}

		return false;
	}

}
