package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.AreaTerritorial;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.AreaTerritorialRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class AreaTerritorialBLImpl implements IAreaTerritorialBL {

	private AreaTerritorialRepository areaTerritorialRepository;
	private MapperFacade mapperAreaTerritorial;

	@Autowired
	public AreaTerritorialBLImpl(AreaTerritorialRepository areaTerritorialRepository) {
		this.areaTerritorialRepository = areaTerritorialRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(AreaTerritorial.class, AreaTerritorialBO.class).byDefault().register();
		this.mapperAreaTerritorial = mapperFactory.getMapperFacade();

	}

	@Override
	public List<AreaTerritorialBO> getAll() {
		log.debug("AreaTerritorialBLImpl:getAll [START]");
		
		List<AreaTerritorialBO> areaTerritorials = new ArrayList<AreaTerritorialBO>();

		List<AreaTerritorial> areaTerritorialEntities = areaTerritorialRepository.findAll();
		for (AreaTerritorial areaTerritorialEntity : areaTerritorialEntities) {
			areaTerritorials.add(mapperAreaTerritorial.map(areaTerritorialEntity, AreaTerritorialBO.class));
		}
		log.debug("AreaTerritorialBLImpl:getAll [END]");
		return areaTerritorials;
	}

	@Override
	public AreaTerritorialBO add(AreaTerritorialBO areaTerritorialBO) {
		log.debug("AreaTerritorialBLImpl:add [START]");
		AreaTerritorial areaTerritorialEntity = mapperAreaTerritorial.map(areaTerritorialBO, AreaTerritorial.class);

		Util.getDateUser(areaTerritorialEntity, "INSERT");

		log.debug("AreaTerritorialBLImpl:add [END]");
		return mapperAreaTerritorial.map(areaTerritorialRepository.save(areaTerritorialEntity), AreaTerritorialBO.class);
	}

	@Override
	public AreaTerritorialBO update(Long areaTerritorialId, AreaTerritorialBO areaTerritorialBO) {
		AreaTerritorial areaTerritorialEntity = areaTerritorialRepository.getOne(areaTerritorialId);
		if (areaTerritorialEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(areaTerritorialEntity, "UPDATE");
			
			return mapperAreaTerritorial.map(areaTerritorialRepository.save(areaTerritorialEntity), AreaTerritorialBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long areaTerritorialId) {
		AreaTerritorial areaTerritorialEntity = areaTerritorialRepository.getOne(areaTerritorialId);
		if (areaTerritorialEntity != null) {
		
			//TODO Baja logica o fisica?
		
			areaTerritorialEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(areaTerritorialEntity, "UPDATE");
			
			mapperAreaTerritorial.map(areaTerritorialRepository.save(areaTerritorialEntity), AreaTerritorialBO.class);
			
			return true;
		}

		return false;
	}

}
