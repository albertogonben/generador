package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.Region;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.RegionRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class RegionBLImpl implements IRegionBL {

	private RegionRepository regionRepository;
	private MapperFacade mapperRegion;

	@Autowired
	public RegionBLImpl(RegionRepository regionRepository) {
		this.regionRepository = regionRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Region.class, RegionBO.class).byDefault().register();
		this.mapperRegion = mapperFactory.getMapperFacade();

	}

	@Override
	public List<RegionBO> getAll() {
		log.debug("RegionBLImpl:getAll [START]");
		
		List<RegionBO> regions = new ArrayList<RegionBO>();

		List<Region> regionEntities = regionRepository.findAll();
		for (Region regionEntity : regionEntities) {
			regions.add(mapperRegion.map(regionEntity, RegionBO.class));
		}
		log.debug("RegionBLImpl:getAll [END]");
		return regions;
	}

	@Override
	public RegionBO add(RegionBO regionBO) {
		log.debug("RegionBLImpl:add [START]");
		Region regionEntity = mapperRegion.map(regionBO, Region.class);

		Util.getDateUser(regionEntity, "INSERT");

		log.debug("RegionBLImpl:add [END]");
		return mapperRegion.map(regionRepository.save(regionEntity), RegionBO.class);
	}

	@Override
	public RegionBO update(Long regionId, RegionBO regionBO) {
		Region regionEntity = regionRepository.getOne(regionId);
		if (regionEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(regionEntity, "UPDATE");
			
			return mapperRegion.map(regionRepository.save(regionEntity), RegionBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long regionId) {
		Region regionEntity = regionRepository.getOne(regionId);
		if (regionEntity != null) {
		
			//TODO Baja logica o fisica?
		
			regionEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(regionEntity, "UPDATE");
			
			mapperRegion.map(regionRepository.save(regionEntity), RegionBO.class);
			
			return true;
		}

		return false;
	}

}
