package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.JobLog;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.JobLogRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class JobLogBLImpl implements IJobLogBL {

	private JobLogRepository jobLogRepository;
	private MapperFacade mapperJobLog;

	@Autowired
	public JobLogBLImpl(JobLogRepository jobLogRepository) {
		this.jobLogRepository = jobLogRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(JobLog.class, JobLogBO.class).byDefault().register();
		this.mapperJobLog = mapperFactory.getMapperFacade();

	}

	@Override
	public List<JobLogBO> getAll() {
		log.debug("JobLogBLImpl:getAll [START]");
		
		List<JobLogBO> jobLogs = new ArrayList<JobLogBO>();

		List<JobLog> jobLogEntities = jobLogRepository.findAll();
		for (JobLog jobLogEntity : jobLogEntities) {
			jobLogs.add(mapperJobLog.map(jobLogEntity, JobLogBO.class));
		}
		log.debug("JobLogBLImpl:getAll [END]");
		return jobLogs;
	}

	@Override
	public JobLogBO add(JobLogBO jobLogBO) {
		log.debug("JobLogBLImpl:add [START]");
		JobLog jobLogEntity = mapperJobLog.map(jobLogBO, JobLog.class);

		Util.getDateUser(jobLogEntity, "INSERT");

		log.debug("JobLogBLImpl:add [END]");
		return mapperJobLog.map(jobLogRepository.save(jobLogEntity), JobLogBO.class);
	}

	@Override
	public JobLogBO update(Long jobLogId, JobLogBO jobLogBO) {
		JobLog jobLogEntity = jobLogRepository.getOne(jobLogId);
		if (jobLogEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(jobLogEntity, "UPDATE");
			
			return mapperJobLog.map(jobLogRepository.save(jobLogEntity), JobLogBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long jobLogId) {
		JobLog jobLogEntity = jobLogRepository.getOne(jobLogId);
		if (jobLogEntity != null) {
		
			//TODO Baja logica o fisica?
		
			jobLogEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(jobLogEntity, "UPDATE");
			
			mapperJobLog.map(jobLogRepository.save(jobLogEntity), JobLogBO.class);
			
			return true;
		}

		return false;
	}

}
