package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.New;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.NewRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class NewBLImpl implements INewBL {

	private NewRepository newRepository;
	private MapperFacade mapperNew;

	@Autowired
	public NewBLImpl(NewRepository newRepository) {
		this.newRepository = newRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(New.class, NewBO.class).byDefault().register();
		this.mapperNew = mapperFactory.getMapperFacade();

	}

	@Override
	public List<NewBO> getAll() {
		log.debug("NewBLImpl:getAll [START]");
		
		List<NewBO> news = new ArrayList<NewBO>();

		List<New> newEntities = newRepository.findAll();
		for (New newEntity : newEntities) {
			news.add(mapperNew.map(newEntity, NewBO.class));
		}
		log.debug("NewBLImpl:getAll [END]");
		return news;
	}

	@Override
	public NewBO add(NewBO newBO) {
		log.debug("NewBLImpl:add [START]");
		New newEntity = mapperNew.map(newBO, New.class);

		Util.getDateUser(newEntity, "INSERT");

		log.debug("NewBLImpl:add [END]");
		return mapperNew.map(newRepository.save(newEntity), NewBO.class);
	}

	@Override
	public NewBO update(Long newId, NewBO newBO) {
		log.debug("NewBLImpl:update [START]");
		New newEntity = newRepository.getOne(newId);
		if (newEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(newEntity, "UPDATE");
			
			log.debug("NewBLImpl:update [START]");
			return mapperNew.map(newRepository.save(newEntity), NewBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long newId) {
		log.debug("NewBLImpl:delete [START]");
		New newEntity = newRepository.getOne(newId);
		if (newEntity != null) {
		
			//TODO Baja logica o fisica?
		
			newEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(newEntity, "UPDATE");
			
			mapperNew.map(newRepository.save(newEntity), NewBO.class);
			
			log.debug("NewBLImpl:delete [END]");
			return true;
		}

		return false;
	}

}
