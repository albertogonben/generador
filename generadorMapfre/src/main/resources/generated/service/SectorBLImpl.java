package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.Sector;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.SectorRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class SectorBLImpl implements ISectorBL {

	private SectorRepository sectorRepository;
	private MapperFacade mapperSector;

	@Autowired
	public SectorBLImpl(SectorRepository sectorRepository) {
		this.sectorRepository = sectorRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Sector.class, SectorBO.class).byDefault().register();
		this.mapperSector = mapperFactory.getMapperFacade();

	}

	@Override
	public List<SectorBO> getAll() {
		log.debug("SectorBLImpl:getAll [START]");
		
		List<SectorBO> sectors = new ArrayList<SectorBO>();

		List<Sector> sectorEntities = sectorRepository.findAll();
		for (Sector sectorEntity : sectorEntities) {
			sectors.add(mapperSector.map(sectorEntity, SectorBO.class));
		}
		log.debug("SectorBLImpl:getAll [END]");
		return sectors;
	}

	@Override
	public SectorBO add(SectorBO sectorBO) {
		log.debug("SectorBLImpl:add [START]");
		Sector sectorEntity = mapperSector.map(sectorBO, Sector.class);

		Util.getDateUser(sectorEntity, "INSERT");

		log.debug("SectorBLImpl:add [END]");
		return mapperSector.map(sectorRepository.save(sectorEntity), SectorBO.class);
	}

	@Override
	public SectorBO update(Long sectorId, SectorBO sectorBO) {
		Sector sectorEntity = sectorRepository.getOne(sectorId);
		if (sectorEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(sectorEntity, "UPDATE");
			
			return mapperSector.map(sectorRepository.save(sectorEntity), SectorBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long sectorId) {
		Sector sectorEntity = sectorRepository.getOne(sectorId);
		if (sectorEntity != null) {
		
			//TODO Baja logica o fisica?
		
			sectorEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(sectorEntity, "UPDATE");
			
			mapperSector.map(sectorRepository.save(sectorEntity), SectorBO.class);
			
			return true;
		}

		return false;
	}

}
