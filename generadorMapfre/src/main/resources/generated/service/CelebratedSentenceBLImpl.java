package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.CelebratedSentence;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.CelebratedSentenceRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class CelebratedSentenceBLImpl implements ICelebratedSentenceBL {

	private CelebratedSentenceRepository celebratedSentenceRepository;
	private MapperFacade mapperCelebratedSentence;

	@Autowired
	public CelebratedSentenceBLImpl(CelebratedSentenceRepository celebratedSentenceRepository) {
		this.celebratedSentenceRepository = celebratedSentenceRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(CelebratedSentence.class, CelebratedSentenceBO.class).byDefault().register();
		this.mapperCelebratedSentence = mapperFactory.getMapperFacade();

	}

	@Override
	public List<CelebratedSentenceBO> getAll() {
		log.debug("CelebratedSentenceBLImpl:getAll [START]");
		
		List<CelebratedSentenceBO> celebratedSentences = new ArrayList<CelebratedSentenceBO>();

		List<CelebratedSentence> celebratedSentenceEntities = celebratedSentenceRepository.findAll();
		for (CelebratedSentence celebratedSentenceEntity : celebratedSentenceEntities) {
			celebratedSentences.add(mapperCelebratedSentence.map(celebratedSentenceEntity, CelebratedSentenceBO.class));
		}
		log.debug("CelebratedSentenceBLImpl:getAll [END]");
		return celebratedSentences;
	}

	@Override
	public CelebratedSentenceBO add(CelebratedSentenceBO celebratedSentenceBO) {
		log.debug("CelebratedSentenceBLImpl:add [START]");
		CelebratedSentence celebratedSentenceEntity = mapperCelebratedSentence.map(celebratedSentenceBO, CelebratedSentence.class);

		Util.getDateUser(celebratedSentenceEntity, "INSERT");

		log.debug("CelebratedSentenceBLImpl:add [END]");
		return mapperCelebratedSentence.map(celebratedSentenceRepository.save(celebratedSentenceEntity), CelebratedSentenceBO.class);
	}

	@Override
	public CelebratedSentenceBO update(Long celebratedSentenceId, CelebratedSentenceBO celebratedSentenceBO) {
		log.debug("CelebratedSentenceBLImpl:update [START]");
		CelebratedSentence celebratedSentenceEntity = celebratedSentenceRepository.getOne(celebratedSentenceId);
		if (celebratedSentenceEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(celebratedSentenceEntity, "UPDATE");
			
			log.debug("CelebratedSentenceBLImpl:update [START]");
			return mapperCelebratedSentence.map(celebratedSentenceRepository.save(celebratedSentenceEntity), CelebratedSentenceBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long celebratedSentenceId) {
		log.debug("CelebratedSentenceBLImpl:delete [START]");
		CelebratedSentence celebratedSentenceEntity = celebratedSentenceRepository.getOne(celebratedSentenceId);
		if (celebratedSentenceEntity != null) {
		
			//TODO Baja logica o fisica?
		
			celebratedSentenceEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(celebratedSentenceEntity, "UPDATE");
			
			mapperCelebratedSentence.map(celebratedSentenceRepository.save(celebratedSentenceEntity), CelebratedSentenceBO.class);
			
			log.debug("CelebratedSentenceBLImpl:delete [END]");
			return true;
		}

		return false;
	}

}
