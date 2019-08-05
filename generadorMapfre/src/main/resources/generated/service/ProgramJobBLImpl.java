package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.ProgramJob;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.ProgramJobRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class ProgramJobBLImpl implements IProgramJobBL {

	private ProgramJobRepository programJobRepository;
	private MapperFacade mapperProgramJob;

	@Autowired
	public ProgramJobBLImpl(ProgramJobRepository programJobRepository) {
		this.programJobRepository = programJobRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(ProgramJob.class, ProgramJobBO.class).byDefault().register();
		this.mapperProgramJob = mapperFactory.getMapperFacade();

	}

	@Override
	public List<ProgramJobBO> getAll() {
		log.debug("ProgramJobBLImpl:getAll [START]");
		
		List<ProgramJobBO> programJobs = new ArrayList<ProgramJobBO>();

		List<ProgramJob> programJobEntities = programJobRepository.findAll();
		for (ProgramJob programJobEntity : programJobEntities) {
			programJobs.add(mapperProgramJob.map(programJobEntity, ProgramJobBO.class));
		}
		log.debug("ProgramJobBLImpl:getAll [END]");
		return programJobs;
	}

	@Override
	public ProgramJobBO add(ProgramJobBO programJobBO) {
		log.debug("ProgramJobBLImpl:add [START]");
		ProgramJob programJobEntity = mapperProgramJob.map(programJobBO, ProgramJob.class);

		Util.getDateUser(programJobEntity, "INSERT");

		log.debug("ProgramJobBLImpl:add [END]");
		return mapperProgramJob.map(programJobRepository.save(programJobEntity), ProgramJobBO.class);
	}

	@Override
	public ProgramJobBO update(Long programJobId, ProgramJobBO programJobBO) {
		ProgramJob programJobEntity = programJobRepository.getOne(programJobId);
		if (programJobEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(programJobEntity, "UPDATE");
			
			return mapperProgramJob.map(programJobRepository.save(programJobEntity), ProgramJobBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long programJobId) {
		ProgramJob programJobEntity = programJobRepository.getOne(programJobId);
		if (programJobEntity != null) {
		
			//TODO Baja logica o fisica?
		
			programJobEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(programJobEntity, "UPDATE");
			
			mapperProgramJob.map(programJobRepository.save(programJobEntity), ProgramJobBO.class);
			
			return true;
		}

		return false;
	}

}
