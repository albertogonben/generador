package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.JobOrigin;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.JobOriginRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class JobOriginBLImpl implements IJobOriginBL {

	private JobOriginRepository jobOriginRepository;
	private MapperFacade mapperJobOrigin;

	@Autowired
	public JobOriginBLImpl(JobOriginRepository jobOriginRepository) {
		this.jobOriginRepository = jobOriginRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(JobOrigin.class, JobOriginBO.class).byDefault().register();
		this.mapperJobOrigin = mapperFactory.getMapperFacade();

	}

	@Override
	public List<JobOriginBO> getAll() {
		log.debug("JobOriginBLImpl:getAll [START]");
		
		List<JobOriginBO> jobOrigins = new ArrayList<JobOriginBO>();

		List<JobOrigin> jobOriginEntities = jobOriginRepository.findAll();
		for (JobOrigin jobOriginEntity : jobOriginEntities) {
			jobOrigins.add(mapperJobOrigin.map(jobOriginEntity, JobOriginBO.class));
		}
		log.debug("JobOriginBLImpl:getAll [END]");
		return jobOrigins;
	}

	@Override
	public JobOriginBO add(JobOriginBO jobOriginBO) {
		log.debug("JobOriginBLImpl:add [START]");
		JobOrigin jobOriginEntity = mapperJobOrigin.map(jobOriginBO, JobOrigin.class);

		Util.getDateUser(jobOriginEntity, "INSERT");

		log.debug("JobOriginBLImpl:add [END]");
		return mapperJobOrigin.map(jobOriginRepository.save(jobOriginEntity), JobOriginBO.class);
	}

	@Override
	public JobOriginBO update(Long jobOriginId, JobOriginBO jobOriginBO) {
		JobOrigin jobOriginEntity = jobOriginRepository.getOne(jobOriginId);
		if (jobOriginEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(jobOriginEntity, "UPDATE");
			
			return mapperJobOrigin.map(jobOriginRepository.save(jobOriginEntity), JobOriginBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long jobOriginId) {
		JobOrigin jobOriginEntity = jobOriginRepository.getOne(jobOriginId);
		if (jobOriginEntity != null) {
		
			//TODO Baja logica o fisica?
		
			jobOriginEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(jobOriginEntity, "UPDATE");
			
			mapperJobOrigin.map(jobOriginRepository.save(jobOriginEntity), JobOriginBO.class);
			
			return true;
		}

		return false;
	}

}
