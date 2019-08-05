package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.GuideJob;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.GuideJobRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class GuideJobBLImpl implements IGuideJobBL {

	private GuideJobRepository guideJobRepository;
	private MapperFacade mapperGuideJob;

	@Autowired
	public GuideJobBLImpl(GuideJobRepository guideJobRepository) {
		this.guideJobRepository = guideJobRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(GuideJob.class, GuideJobBO.class).byDefault().register();
		this.mapperGuideJob = mapperFactory.getMapperFacade();

	}

	@Override
	public List<GuideJobBO> getAll() {
		log.debug("GuideJobBLImpl:getAll [START]");
		
		List<GuideJobBO> guideJobs = new ArrayList<GuideJobBO>();

		List<GuideJob> guideJobEntities = guideJobRepository.findAll();
		for (GuideJob guideJobEntity : guideJobEntities) {
			guideJobs.add(mapperGuideJob.map(guideJobEntity, GuideJobBO.class));
		}
		log.debug("GuideJobBLImpl:getAll [END]");
		return guideJobs;
	}

	@Override
	public GuideJobBO add(GuideJobBO guideJobBO) {
		log.debug("GuideJobBLImpl:add [START]");
		GuideJob guideJobEntity = mapperGuideJob.map(guideJobBO, GuideJob.class);

		Util.getDateUser(guideJobEntity, "INSERT");

		log.debug("GuideJobBLImpl:add [END]");
		return mapperGuideJob.map(guideJobRepository.save(guideJobEntity), GuideJobBO.class);
	}

	@Override
	public GuideJobBO update(Long guideJobId, GuideJobBO guideJobBO) {
		GuideJob guideJobEntity = guideJobRepository.getOne(guideJobId);
		if (guideJobEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(guideJobEntity, "UPDATE");
			
			return mapperGuideJob.map(guideJobRepository.save(guideJobEntity), GuideJobBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long guideJobId) {
		GuideJob guideJobEntity = guideJobRepository.getOne(guideJobId);
		if (guideJobEntity != null) {
		
			//TODO Baja logica o fisica?
		
			guideJobEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(guideJobEntity, "UPDATE");
			
			mapperGuideJob.map(guideJobRepository.save(guideJobEntity), GuideJobBO.class);
			
			return true;
		}

		return false;
	}

}
