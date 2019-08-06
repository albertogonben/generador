package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.ClosePeriod;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.ClosePeriodRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class ClosePeriodBLImpl implements IClosePeriodBL {

	private ClosePeriodRepository closePeriodRepository;
	private MapperFacade mapperClosePeriod;

	@Autowired
	public ClosePeriodBLImpl(ClosePeriodRepository closePeriodRepository) {
		this.closePeriodRepository = closePeriodRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(ClosePeriod.class, ClosePeriodBO.class).byDefault().register();
		this.mapperClosePeriod = mapperFactory.getMapperFacade();

	}

	@Override
	public List<ClosePeriodBO> getAll() {
		log.debug("ClosePeriodBLImpl:getAll [START]");
		
		List<ClosePeriodBO> closePeriods = new ArrayList<ClosePeriodBO>();

		List<ClosePeriod> closePeriodEntities = closePeriodRepository.findAll();
		for (ClosePeriod closePeriodEntity : closePeriodEntities) {
			closePeriods.add(mapperClosePeriod.map(closePeriodEntity, ClosePeriodBO.class));
		}
		log.debug("ClosePeriodBLImpl:getAll [END]");
		return closePeriods;
	}

	@Override
	public ClosePeriodBO add(ClosePeriodBO closePeriodBO) {
		log.debug("ClosePeriodBLImpl:add [START]");
		ClosePeriod closePeriodEntity = mapperClosePeriod.map(closePeriodBO, ClosePeriod.class);

		Util.getDateUser(closePeriodEntity, "INSERT");

		log.debug("ClosePeriodBLImpl:add [END]");
		return mapperClosePeriod.map(closePeriodRepository.save(closePeriodEntity), ClosePeriodBO.class);
	}

	@Override
	public ClosePeriodBO update(Long closePeriodId, ClosePeriodBO closePeriodBO) {
		log.debug("ClosePeriodBLImpl:update [START]");
		ClosePeriod closePeriodEntity = closePeriodRepository.getOne(closePeriodId);
		if (closePeriodEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(closePeriodEntity, "UPDATE");
			
			log.debug("ClosePeriodBLImpl:update [START]");
			return mapperClosePeriod.map(closePeriodRepository.save(closePeriodEntity), ClosePeriodBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long closePeriodId) {
		log.debug("ClosePeriodBLImpl:delete [START]");
		ClosePeriod closePeriodEntity = closePeriodRepository.getOne(closePeriodId);
		if (closePeriodEntity != null) {
		
			//TODO Baja logica o fisica?
		
			closePeriodEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(closePeriodEntity, "UPDATE");
			
			mapperClosePeriod.map(closePeriodRepository.save(closePeriodEntity), ClosePeriodBO.class);
			
			log.debug("ClosePeriodBLImpl:delete [END]");
			return true;
		}

		return false;
	}

}
