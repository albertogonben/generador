package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.UnitBusiness;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.UnitBusinessRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class UnitBusinessBLImpl implements IUnitBusinessBL {

	private UnitBusinessRepository unitBusinessRepository;
	private MapperFacade mapperUnitBusiness;

	@Autowired
	public UnitBusinessBLImpl(UnitBusinessRepository unitBusinessRepository) {
		this.unitBusinessRepository = unitBusinessRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(UnitBusiness.class, UnitBusinessBO.class).byDefault().register();
		this.mapperUnitBusiness = mapperFactory.getMapperFacade();

	}

	@Override
	public List<UnitBusinessBO> getAll() {
		log.debug("UnitBusinessBLImpl:getAll [START]");
		
		List<UnitBusinessBO> unitBusinesss = new ArrayList<UnitBusinessBO>();

		List<UnitBusiness> unitBusinessEntities = unitBusinessRepository.findAll();
		for (UnitBusiness unitBusinessEntity : unitBusinessEntities) {
			unitBusinesss.add(mapperUnitBusiness.map(unitBusinessEntity, UnitBusinessBO.class));
		}
		log.debug("UnitBusinessBLImpl:getAll [END]");
		return unitBusinesss;
	}

	@Override
	public UnitBusinessBO add(UnitBusinessBO unitBusinessBO) {
		log.debug("UnitBusinessBLImpl:add [START]");
		UnitBusiness unitBusinessEntity = mapperUnitBusiness.map(unitBusinessBO, UnitBusiness.class);

		Util.getDateUser(unitBusinessEntity, "INSERT");

		log.debug("UnitBusinessBLImpl:add [END]");
		return mapperUnitBusiness.map(unitBusinessRepository.save(unitBusinessEntity), UnitBusinessBO.class);
	}

	@Override
	public UnitBusinessBO update(Long unitBusinessId, UnitBusinessBO unitBusinessBO) {
		UnitBusiness unitBusinessEntity = unitBusinessRepository.getOne(unitBusinessId);
		if (unitBusinessEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(unitBusinessEntity, "UPDATE");
			
			return mapperUnitBusiness.map(unitBusinessRepository.save(unitBusinessEntity), UnitBusinessBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long unitBusinessId) {
		UnitBusiness unitBusinessEntity = unitBusinessRepository.getOne(unitBusinessId);
		if (unitBusinessEntity != null) {
		
			//TODO Baja logica o fisica?
		
			unitBusinessEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(unitBusinessEntity, "UPDATE");
			
			mapperUnitBusiness.map(unitBusinessRepository.save(unitBusinessEntity), UnitBusinessBO.class);
			
			return true;
		}

		return false;
	}

}
