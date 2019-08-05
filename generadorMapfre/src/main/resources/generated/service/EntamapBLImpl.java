package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.Entamap;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.EntamapRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class EntamapBLImpl implements IEntamapBL {

	private EntamapRepository entamapRepository;
	private MapperFacade mapperEntamap;

	@Autowired
	public EntamapBLImpl(EntamapRepository entamapRepository) {
		this.entamapRepository = entamapRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Entamap.class, EntamapBO.class).byDefault().register();
		this.mapperEntamap = mapperFactory.getMapperFacade();

	}

	@Override
	public List<EntamapBO> getAll() {
		log.debug("EntamapBLImpl:getAll [START]");
		
		List<EntamapBO> entamaps = new ArrayList<EntamapBO>();

		List<Entamap> entamapEntities = entamapRepository.findAll();
		for (Entamap entamapEntity : entamapEntities) {
			entamaps.add(mapperEntamap.map(entamapEntity, EntamapBO.class));
		}
		log.debug("EntamapBLImpl:getAll [END]");
		return entamaps;
	}

	@Override
	public EntamapBO add(EntamapBO entamapBO) {
		log.debug("EntamapBLImpl:add [START]");
		Entamap entamapEntity = mapperEntamap.map(entamapBO, Entamap.class);

		Util.getDateUser(entamapEntity, "INSERT");

		log.debug("EntamapBLImpl:add [END]");
		return mapperEntamap.map(entamapRepository.save(entamapEntity), EntamapBO.class);
	}

	@Override
	public EntamapBO update(Long entamapId, EntamapBO entamapBO) {
		Entamap entamapEntity = entamapRepository.getOne(entamapId);
		if (entamapEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(entamapEntity, "UPDATE");
			
			return mapperEntamap.map(entamapRepository.save(entamapEntity), EntamapBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long entamapId) {
		Entamap entamapEntity = entamapRepository.getOne(entamapId);
		if (entamapEntity != null) {
		
			//TODO Baja logica o fisica?
		
			entamapEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(entamapEntity, "UPDATE");
			
			mapperEntamap.map(entamapRepository.save(entamapEntity), EntamapBO.class);
			
			return true;
		}

		return false;
	}

}
