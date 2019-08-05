package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.JobIdea;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.JobIdeaRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class JobIdeaBLImpl implements IJobIdeaBL {

	private JobIdeaRepository jobIdeaRepository;
	private MapperFacade mapperJobIdea;

	@Autowired
	public JobIdeaBLImpl(JobIdeaRepository jobIdeaRepository) {
		this.jobIdeaRepository = jobIdeaRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(JobIdea.class, JobIdeaBO.class).byDefault().register();
		this.mapperJobIdea = mapperFactory.getMapperFacade();

	}

	@Override
	public List<JobIdeaBO> getAll() {
		log.debug("JobIdeaBLImpl:getAll [START]");
		
		List<JobIdeaBO> jobIdeas = new ArrayList<JobIdeaBO>();

		List<JobIdea> jobIdeaEntities = jobIdeaRepository.findAll();
		for (JobIdea jobIdeaEntity : jobIdeaEntities) {
			jobIdeas.add(mapperJobIdea.map(jobIdeaEntity, JobIdeaBO.class));
		}
		log.debug("JobIdeaBLImpl:getAll [END]");
		return jobIdeas;
	}

	@Override
	public JobIdeaBO add(JobIdeaBO jobIdeaBO) {
		log.debug("JobIdeaBLImpl:add [START]");
		JobIdea jobIdeaEntity = mapperJobIdea.map(jobIdeaBO, JobIdea.class);

		Util.getDateUser(jobIdeaEntity, "INSERT");

		log.debug("JobIdeaBLImpl:add [END]");
		return mapperJobIdea.map(jobIdeaRepository.save(jobIdeaEntity), JobIdeaBO.class);
	}

	@Override
	public JobIdeaBO update(Long jobIdeaId, JobIdeaBO jobIdeaBO) {
		JobIdea jobIdeaEntity = jobIdeaRepository.getOne(jobIdeaId);
		if (jobIdeaEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(jobIdeaEntity, "UPDATE");
			
			return mapperJobIdea.map(jobIdeaRepository.save(jobIdeaEntity), JobIdeaBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long jobIdeaId) {
		JobIdea jobIdeaEntity = jobIdeaRepository.getOne(jobIdeaId);
		if (jobIdeaEntity != null) {
		
			//TODO Baja logica o fisica?
		
			jobIdeaEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(jobIdeaEntity, "UPDATE");
			
			mapperJobIdea.map(jobIdeaRepository.save(jobIdeaEntity), JobIdeaBO.class);
			
			return true;
		}

		return false;
	}

}
