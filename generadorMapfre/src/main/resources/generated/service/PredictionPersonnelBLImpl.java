package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.PredictionPersonnel;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.PredictionPersonnelRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class PredictionPersonnelBLImpl implements IPredictionPersonnelBL {

	private PredictionPersonnelRepository predictionPersonnelRepository;
	private MapperFacade mapperPredictionPersonnel;

	@Autowired
	public PredictionPersonnelBLImpl(PredictionPersonnelRepository predictionPersonnelRepository) {
		this.predictionPersonnelRepository = predictionPersonnelRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(PredictionPersonnel.class, PredictionPersonnelBO.class).byDefault().register();
		this.mapperPredictionPersonnel = mapperFactory.getMapperFacade();

	}

	@Override
	public List<PredictionPersonnelBO> getAll() {
		log.debug("PredictionPersonnelBLImpl:getAll [START]");
		
		List<PredictionPersonnelBO> predictionPersonnels = new ArrayList<PredictionPersonnelBO>();

		List<PredictionPersonnel> predictionPersonnelEntities = predictionPersonnelRepository.findAll();
		for (PredictionPersonnel predictionPersonnelEntity : predictionPersonnelEntities) {
			predictionPersonnels.add(mapperPredictionPersonnel.map(predictionPersonnelEntity, PredictionPersonnelBO.class));
		}
		log.debug("PredictionPersonnelBLImpl:getAll [END]");
		return predictionPersonnels;
	}

	@Override
	public PredictionPersonnelBO add(PredictionPersonnelBO predictionPersonnelBO) {
		log.debug("PredictionPersonnelBLImpl:add [START]");
		PredictionPersonnel predictionPersonnelEntity = mapperPredictionPersonnel.map(predictionPersonnelBO, PredictionPersonnel.class);

		Util.getDateUser(predictionPersonnelEntity, "INSERT");

		log.debug("PredictionPersonnelBLImpl:add [END]");
		return mapperPredictionPersonnel.map(predictionPersonnelRepository.save(predictionPersonnelEntity), PredictionPersonnelBO.class);
	}

	@Override
	public PredictionPersonnelBO update(Long predictionPersonnelId, PredictionPersonnelBO predictionPersonnelBO) {
		PredictionPersonnel predictionPersonnelEntity = predictionPersonnelRepository.getOne(predictionPersonnelId);
		if (predictionPersonnelEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(predictionPersonnelEntity, "UPDATE");
			
			return mapperPredictionPersonnel.map(predictionPersonnelRepository.save(predictionPersonnelEntity), PredictionPersonnelBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long predictionPersonnelId) {
		PredictionPersonnel predictionPersonnelEntity = predictionPersonnelRepository.getOne(predictionPersonnelId);
		if (predictionPersonnelEntity != null) {
		
			//TODO Baja logica o fisica?
		
			predictionPersonnelEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(predictionPersonnelEntity, "UPDATE");
			
			mapperPredictionPersonnel.map(predictionPersonnelRepository.save(predictionPersonnelEntity), PredictionPersonnelBO.class);
			
			return true;
		}

		return false;
	}

}
