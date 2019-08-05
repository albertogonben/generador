package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.ProcessUnitBusiness;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.ProcessUnitBusinessRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class ProcessUnitBusinessBLImpl implements IProcessUnitBusinessBL {

	private ProcessUnitBusinessRepository processUnitBusinessRepository;
	private MapperFacade mapperProcessUnitBusiness;

	@Autowired
	public ProcessUnitBusinessBLImpl(ProcessUnitBusinessRepository processUnitBusinessRepository) {
		this.processUnitBusinessRepository = processUnitBusinessRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(ProcessUnitBusiness.class, ProcessUnitBusinessBO.class).byDefault().register();
		this.mapperProcessUnitBusiness = mapperFactory.getMapperFacade();

	}

	@Override
	public List<ProcessUnitBusinessBO> getAll() {
		log.debug("ProcessUnitBusinessBLImpl:getAll [START]");
		
		List<ProcessUnitBusinessBO> processUnitBusinesss = new ArrayList<ProcessUnitBusinessBO>();

		List<ProcessUnitBusiness> processUnitBusinessEntities = processUnitBusinessRepository.findAll();
		for (ProcessUnitBusiness processUnitBusinessEntity : processUnitBusinessEntities) {
			processUnitBusinesss.add(mapperProcessUnitBusiness.map(processUnitBusinessEntity, ProcessUnitBusinessBO.class));
		}
		log.debug("ProcessUnitBusinessBLImpl:getAll [END]");
		return processUnitBusinesss;
	}

	@Override
	public ProcessUnitBusinessBO add(ProcessUnitBusinessBO processUnitBusinessBO) {
		log.debug("ProcessUnitBusinessBLImpl:add [START]");
		ProcessUnitBusiness processUnitBusinessEntity = mapperProcessUnitBusiness.map(processUnitBusinessBO, ProcessUnitBusiness.class);

		Util.getDateUser(processUnitBusinessEntity, "INSERT");

		log.debug("ProcessUnitBusinessBLImpl:add [END]");
		return mapperProcessUnitBusiness.map(processUnitBusinessRepository.save(processUnitBusinessEntity), ProcessUnitBusinessBO.class);
	}

	@Override
	public ProcessUnitBusinessBO update(Long processUnitBusinessId, ProcessUnitBusinessBO processUnitBusinessBO) {
		ProcessUnitBusiness processUnitBusinessEntity = processUnitBusinessRepository.getOne(processUnitBusinessId);
		if (processUnitBusinessEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(processUnitBusinessEntity, "UPDATE");
			
			return mapperProcessUnitBusiness.map(processUnitBusinessRepository.save(processUnitBusinessEntity), ProcessUnitBusinessBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long processUnitBusinessId) {
		ProcessUnitBusiness processUnitBusinessEntity = processUnitBusinessRepository.getOne(processUnitBusinessId);
		if (processUnitBusinessEntity != null) {
		
			//TODO Baja logica o fisica?
		
			processUnitBusinessEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(processUnitBusinessEntity, "UPDATE");
			
			mapperProcessUnitBusiness.map(processUnitBusinessRepository.save(processUnitBusinessEntity), ProcessUnitBusinessBO.class);
			
			return true;
		}

		return false;
	}

}
