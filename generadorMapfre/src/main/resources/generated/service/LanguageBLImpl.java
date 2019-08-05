package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.Language;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.LanguageRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class LanguageBLImpl implements ILanguageBL {

	private LanguageRepository languageRepository;
	private MapperFacade mapperLanguage;

	@Autowired
	public LanguageBLImpl(LanguageRepository languageRepository) {
		this.languageRepository = languageRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Language.class, LanguageBO.class).byDefault().register();
		this.mapperLanguage = mapperFactory.getMapperFacade();

	}

	@Override
	public List<LanguageBO> getAll() {
		log.debug("LanguageBLImpl:getAll [START]");
		
		List<LanguageBO> languages = new ArrayList<LanguageBO>();

		List<Language> languageEntities = languageRepository.findAll();
		for (Language languageEntity : languageEntities) {
			languages.add(mapperLanguage.map(languageEntity, LanguageBO.class));
		}
		log.debug("LanguageBLImpl:getAll [END]");
		return languages;
	}

	@Override
	public LanguageBO add(LanguageBO languageBO) {
		log.debug("LanguageBLImpl:add [START]");
		Language languageEntity = mapperLanguage.map(languageBO, Language.class);

		Util.getDateUser(languageEntity, "INSERT");

		log.debug("LanguageBLImpl:add [END]");
		return mapperLanguage.map(languageRepository.save(languageEntity), LanguageBO.class);
	}

	@Override
	public LanguageBO update(Long languageId, LanguageBO languageBO) {
		Language languageEntity = languageRepository.getOne(languageId);
		if (languageEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(languageEntity, "UPDATE");
			
			return mapperLanguage.map(languageRepository.save(languageEntity), LanguageBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long languageId) {
		Language languageEntity = languageRepository.getOne(languageId);
		if (languageEntity != null) {
		
			//TODO Baja logica o fisica?
		
			languageEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(languageEntity, "UPDATE");
			
			mapperLanguage.map(languageRepository.save(languageEntity), LanguageBO.class);
			
			return true;
		}

		return false;
	}

}
