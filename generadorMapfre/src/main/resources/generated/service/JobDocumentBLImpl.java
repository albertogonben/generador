package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.JobDocument;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.JobDocumentRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class JobDocumentBLImpl implements IJobDocumentBL {

	private JobDocumentRepository jobDocumentRepository;
	private MapperFacade mapperJobDocument;

	@Autowired
	public JobDocumentBLImpl(JobDocumentRepository jobDocumentRepository) {
		this.jobDocumentRepository = jobDocumentRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(JobDocument.class, JobDocumentBO.class).byDefault().register();
		this.mapperJobDocument = mapperFactory.getMapperFacade();

	}

	@Override
	public List<JobDocumentBO> getAll() {
		log.debug("JobDocumentBLImpl:getAll [START]");
		
		List<JobDocumentBO> jobDocuments = new ArrayList<JobDocumentBO>();

		List<JobDocument> jobDocumentEntities = jobDocumentRepository.findAll();
		for (JobDocument jobDocumentEntity : jobDocumentEntities) {
			jobDocuments.add(mapperJobDocument.map(jobDocumentEntity, JobDocumentBO.class));
		}
		log.debug("JobDocumentBLImpl:getAll [END]");
		return jobDocuments;
	}

	@Override
	public JobDocumentBO add(JobDocumentBO jobDocumentBO) {
		log.debug("JobDocumentBLImpl:add [START]");
		JobDocument jobDocumentEntity = mapperJobDocument.map(jobDocumentBO, JobDocument.class);

		Util.getDateUser(jobDocumentEntity, "INSERT");

		log.debug("JobDocumentBLImpl:add [END]");
		return mapperJobDocument.map(jobDocumentRepository.save(jobDocumentEntity), JobDocumentBO.class);
	}

	@Override
	public JobDocumentBO update(Long jobDocumentId, JobDocumentBO jobDocumentBO) {
		JobDocument jobDocumentEntity = jobDocumentRepository.getOne(jobDocumentId);
		if (jobDocumentEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(jobDocumentEntity, "UPDATE");
			
			return mapperJobDocument.map(jobDocumentRepository.save(jobDocumentEntity), JobDocumentBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long jobDocumentId) {
		JobDocument jobDocumentEntity = jobDocumentRepository.getOne(jobDocumentId);
		if (jobDocumentEntity != null) {
		
			//TODO Baja logica o fisica?
		
			jobDocumentEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(jobDocumentEntity, "UPDATE");
			
			mapperJobDocument.map(jobDocumentRepository.save(jobDocumentEntity), JobDocumentBO.class);
			
			return true;
		}

		return false;
	}

}
