package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.FactorRisk;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.FactorRiskRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class FactorRiskBLImpl implements IFactorRiskBL {

	private FactorRiskRepository factorRiskRepository;
	private MapperFacade mapperFactorRisk;

	@Autowired
	public FactorRiskBLImpl(FactorRiskRepository factorRiskRepository) {
		this.factorRiskRepository = factorRiskRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(FactorRisk.class, FactorRiskBO.class).byDefault().register();
		this.mapperFactorRisk = mapperFactory.getMapperFacade();

	}

	@Override
	public List<FactorRiskBO> getAll() {
		log.debug("FactorRiskBLImpl:getAll [START]");
		
		List<FactorRiskBO> factorRisks = new ArrayList<FactorRiskBO>();

		List<FactorRisk> factorRiskEntities = factorRiskRepository.findAll();
		for (FactorRisk factorRiskEntity : factorRiskEntities) {
			factorRisks.add(mapperFactorRisk.map(factorRiskEntity, FactorRiskBO.class));
		}
		log.debug("FactorRiskBLImpl:getAll [END]");
		return factorRisks;
	}

	@Override
	public FactorRiskBO add(FactorRiskBO factorRiskBO) {
		log.debug("FactorRiskBLImpl:add [START]");
		FactorRisk factorRiskEntity = mapperFactorRisk.map(factorRiskBO, FactorRisk.class);

		Util.getDateUser(factorRiskEntity, "INSERT");

		log.debug("FactorRiskBLImpl:add [END]");
		return mapperFactorRisk.map(factorRiskRepository.save(factorRiskEntity), FactorRiskBO.class);
	}

	@Override
	public FactorRiskBO update(Long factorRiskId, FactorRiskBO factorRiskBO) {
		FactorRisk factorRiskEntity = factorRiskRepository.getOne(factorRiskId);
		if (factorRiskEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(factorRiskEntity, "UPDATE");
			
			return mapperFactorRisk.map(factorRiskRepository.save(factorRiskEntity), FactorRiskBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long factorRiskId) {
		FactorRisk factorRiskEntity = factorRiskRepository.getOne(factorRiskId);
		if (factorRiskEntity != null) {
		
			//TODO Baja logica o fisica?
		
			factorRiskEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(factorRiskEntity, "UPDATE");
			
			mapperFactorRisk.map(factorRiskRepository.save(factorRiskEntity), FactorRiskBO.class);
			
			return true;
		}

		return false;
	}

}
