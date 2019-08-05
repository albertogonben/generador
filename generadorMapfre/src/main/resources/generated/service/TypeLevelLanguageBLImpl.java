package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeLevelLanguage;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeLevelLanguageRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeLevelLanguageBLImpl implements ITypeLevelLanguageBL {

	private TypeLevelLanguageRepository typeLevelLanguageRepository;
	private MapperFacade mapperTypeLevelLanguage;

	@Autowired
	public TypeLevelLanguageBLImpl(TypeLevelLanguageRepository typeLevelLanguageRepository) {
		this.typeLevelLanguageRepository = typeLevelLanguageRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeLevelLanguage.class, TypeLevelLanguageBO.class).byDefault().register();
		this.mapperTypeLevelLanguage = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeLevelLanguageBO> getAll() {
		log.debug("TypeLevelLanguageBLImpl:getAll [START]");
		
		List<TypeLevelLanguageBO> typeLevelLanguages = new ArrayList<TypeLevelLanguageBO>();

		List<TypeLevelLanguage> typeLevelLanguageEntities = typeLevelLanguageRepository.findAll();
		for (TypeLevelLanguage typeLevelLanguageEntity : typeLevelLanguageEntities) {
			typeLevelLanguages.add(mapperTypeLevelLanguage.map(typeLevelLanguageEntity, TypeLevelLanguageBO.class));
		}
		log.debug("TypeLevelLanguageBLImpl:getAll [END]");
		return typeLevelLanguages;
	}

	@Override
	public TypeLevelLanguageBO add(TypeLevelLanguageBO typeLevelLanguageBO) {
		log.debug("TypeLevelLanguageBLImpl:add [START]");
		TypeLevelLanguage typeLevelLanguageEntity = mapperTypeLevelLanguage.map(typeLevelLanguageBO, TypeLevelLanguage.class);

		Util.getDateUser(typeLevelLanguageEntity, "INSERT");

		log.debug("TypeLevelLanguageBLImpl:add [END]");
		return mapperTypeLevelLanguage.map(typeLevelLanguageRepository.save(typeLevelLanguageEntity), TypeLevelLanguageBO.class);
	}

	@Override
	public TypeLevelLanguageBO update(Long typeLevelLanguageId, TypeLevelLanguageBO typeLevelLanguageBO) {
		TypeLevelLanguage typeLevelLanguageEntity = typeLevelLanguageRepository.getOne(typeLevelLanguageId);
		if (typeLevelLanguageEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeLevelLanguageEntity, "UPDATE");
			
			return mapperTypeLevelLanguage.map(typeLevelLanguageRepository.save(typeLevelLanguageEntity), TypeLevelLanguageBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeLevelLanguageId) {
		TypeLevelLanguage typeLevelLanguageEntity = typeLevelLanguageRepository.getOne(typeLevelLanguageId);
		if (typeLevelLanguageEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeLevelLanguageEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeLevelLanguageEntity, "UPDATE");
			
			mapperTypeLevelLanguage.map(typeLevelLanguageRepository.save(typeLevelLanguageEntity), TypeLevelLanguageBO.class);
			
			return true;
		}

		return false;
	}

}
