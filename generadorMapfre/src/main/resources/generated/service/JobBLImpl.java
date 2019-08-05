package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.Job;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.JobRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class JobBLImpl implements IJobBL {

	private JobRepository jobRepository;
	private MapperFacade mapperJob;

	@Autowired
	public JobBLImpl(JobRepository jobRepository) {
		this.jobRepository = jobRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Job.class, JobBO.class).byDefault().register();
		this.mapperJob = mapperFactory.getMapperFacade();

	}

	@Override
	public List<JobBO> getAll() {
		log.debug("JobBLImpl:getAll [START]");
		
		List<JobBO> jobs = new ArrayList<JobBO>();

		List<Job> jobEntities = jobRepository.findAll();
		for (Job jobEntity : jobEntities) {
			jobs.add(mapperJob.map(jobEntity, JobBO.class));
		}
		log.debug("JobBLImpl:getAll [END]");
		return jobs;
	}

	@Override
	public JobBO add(JobBO jobBO) {
		log.debug("JobBLImpl:add [START]");
		Job jobEntity = mapperJob.map(jobBO, Job.class);

		Util.getDateUser(jobEntity, "INSERT");

		log.debug("JobBLImpl:add [END]");
		return mapperJob.map(jobRepository.save(jobEntity), JobBO.class);
	}

	@Override
	public JobBO update(Long jobId, JobBO jobBO) {
		Job jobEntity = jobRepository.getOne(jobId);
		if (jobEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(jobEntity, "UPDATE");
			
			return mapperJob.map(jobRepository.save(jobEntity), JobBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long jobId) {
		Job jobEntity = jobRepository.getOne(jobId);
		if (jobEntity != null) {
		
			//TODO Baja logica o fisica?
		
			jobEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(jobEntity, "UPDATE");
			
			mapperJob.map(jobRepository.save(jobEntity), JobBO.class);
			
			return true;
		}

		return false;
	}

}
