package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.JobTag;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.JobTagRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class JobTagBLImpl implements IJobTagBL {

	private JobTagRepository jobTagRepository;
	private MapperFacade mapperJobTag;

	@Autowired
	public JobTagBLImpl(JobTagRepository jobTagRepository) {
		this.jobTagRepository = jobTagRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(JobTag.class, JobTagBO.class).byDefault().register();
		this.mapperJobTag = mapperFactory.getMapperFacade();

	}

	@Override
	public List<JobTagBO> getAll() {
		log.debug("JobTagBLImpl:getAll [START]");
		
		List<JobTagBO> jobTags = new ArrayList<JobTagBO>();

		List<JobTag> jobTagEntities = jobTagRepository.findAll();
		for (JobTag jobTagEntity : jobTagEntities) {
			jobTags.add(mapperJobTag.map(jobTagEntity, JobTagBO.class));
		}
		log.debug("JobTagBLImpl:getAll [END]");
		return jobTags;
	}

	@Override
	public JobTagBO add(JobTagBO jobTagBO) {
		log.debug("JobTagBLImpl:add [START]");
		JobTag jobTagEntity = mapperJobTag.map(jobTagBO, JobTag.class);

		Util.getDateUser(jobTagEntity, "INSERT");

		log.debug("JobTagBLImpl:add [END]");
		return mapperJobTag.map(jobTagRepository.save(jobTagEntity), JobTagBO.class);
	}

	@Override
	public JobTagBO update(Long jobTagId, JobTagBO jobTagBO) {
		JobTag jobTagEntity = jobTagRepository.getOne(jobTagId);
		if (jobTagEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(jobTagEntity, "UPDATE");
			
			return mapperJobTag.map(jobTagRepository.save(jobTagEntity), JobTagBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long jobTagId) {
		JobTag jobTagEntity = jobTagRepository.getOne(jobTagId);
		if (jobTagEntity != null) {
		
			//TODO Baja logica o fisica?
		
			jobTagEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(jobTagEntity, "UPDATE");
			
			mapperJobTag.map(jobTagRepository.save(jobTagEntity), JobTagBO.class);
			
			return true;
		}

		return false;
	}

}
