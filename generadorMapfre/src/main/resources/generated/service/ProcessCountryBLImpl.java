package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.ProcessCountry;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.ProcessCountryRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class ProcessCountryBLImpl implements IProcessCountryBL {

	private ProcessCountryRepository processCountryRepository;
	private MapperFacade mapperProcessCountry;

	@Autowired
	public ProcessCountryBLImpl(ProcessCountryRepository processCountryRepository) {
		this.processCountryRepository = processCountryRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(ProcessCountry.class, ProcessCountryBO.class).byDefault().register();
		this.mapperProcessCountry = mapperFactory.getMapperFacade();

	}

	@Override
	public List<ProcessCountryBO> getAll() {
		log.debug("ProcessCountryBLImpl:getAll [START]");
		
		List<ProcessCountryBO> processCountrys = new ArrayList<ProcessCountryBO>();

		List<ProcessCountry> processCountryEntities = processCountryRepository.findAll();
		for (ProcessCountry processCountryEntity : processCountryEntities) {
			processCountrys.add(mapperProcessCountry.map(processCountryEntity, ProcessCountryBO.class));
		}
		log.debug("ProcessCountryBLImpl:getAll [END]");
		return processCountrys;
	}

	@Override
	public ProcessCountryBO add(ProcessCountryBO processCountryBO) {
		log.debug("ProcessCountryBLImpl:add [START]");
		ProcessCountry processCountryEntity = mapperProcessCountry.map(processCountryBO, ProcessCountry.class);

		Util.getDateUser(processCountryEntity, "INSERT");

		log.debug("ProcessCountryBLImpl:add [END]");
		return mapperProcessCountry.map(processCountryRepository.save(processCountryEntity), ProcessCountryBO.class);
	}

	@Override
	public ProcessCountryBO update(Long processCountryId, ProcessCountryBO processCountryBO) {
		ProcessCountry processCountryEntity = processCountryRepository.getOne(processCountryId);
		if (processCountryEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(processCountryEntity, "UPDATE");
			
			return mapperProcessCountry.map(processCountryRepository.save(processCountryEntity), ProcessCountryBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long processCountryId) {
		ProcessCountry processCountryEntity = processCountryRepository.getOne(processCountryId);
		if (processCountryEntity != null) {
		
			//TODO Baja logica o fisica?
		
			processCountryEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(processCountryEntity, "UPDATE");
			
			mapperProcessCountry.map(processCountryRepository.save(processCountryEntity), ProcessCountryBO.class);
			
			return true;
		}

		return false;
	}

}
