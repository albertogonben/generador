package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.Origin;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.OriginRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class OriginBLImpl implements IOriginBL {

	private OriginRepository originRepository;
	private MapperFacade mapperOrigin;

	@Autowired
	public OriginBLImpl(OriginRepository originRepository) {
		this.originRepository = originRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Origin.class, OriginBO.class).byDefault().register();
		this.mapperOrigin = mapperFactory.getMapperFacade();

	}

	@Override
	public List<OriginBO> getAll() {
		log.debug("OriginBLImpl:getAll [START]");
		
		List<OriginBO> origins = new ArrayList<OriginBO>();

		List<Origin> originEntities = originRepository.findAll();
		for (Origin originEntity : originEntities) {
			origins.add(mapperOrigin.map(originEntity, OriginBO.class));
		}
		log.debug("OriginBLImpl:getAll [END]");
		return origins;
	}

	@Override
	public OriginBO add(OriginBO originBO) {
		log.debug("OriginBLImpl:add [START]");
		Origin originEntity = mapperOrigin.map(originBO, Origin.class);

		Util.getDateUser(originEntity, "INSERT");

		log.debug("OriginBLImpl:add [END]");
		return mapperOrigin.map(originRepository.save(originEntity), OriginBO.class);
	}

	@Override
	public OriginBO update(Long originId, OriginBO originBO) {
		Origin originEntity = originRepository.getOne(originId);
		if (originEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(originEntity, "UPDATE");
			
			return mapperOrigin.map(originRepository.save(originEntity), OriginBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long originId) {
		Origin originEntity = originRepository.getOne(originId);
		if (originEntity != null) {
		
			//TODO Baja logica o fisica?
		
			originEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(originEntity, "UPDATE");
			
			mapperOrigin.map(originRepository.save(originEntity), OriginBO.class);
			
			return true;
		}

		return false;
	}

}
