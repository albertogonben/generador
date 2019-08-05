package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeReasonChangeCategory;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeReasonChangeCategoryRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeReasonChangeCategoryBLImpl implements ITypeReasonChangeCategoryBL {

	private TypeReasonChangeCategoryRepository typeReasonChangeCategoryRepository;
	private MapperFacade mapperTypeReasonChangeCategory;

	@Autowired
	public TypeReasonChangeCategoryBLImpl(TypeReasonChangeCategoryRepository typeReasonChangeCategoryRepository) {
		this.typeReasonChangeCategoryRepository = typeReasonChangeCategoryRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeReasonChangeCategory.class, TypeReasonChangeCategoryBO.class).byDefault().register();
		this.mapperTypeReasonChangeCategory = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeReasonChangeCategoryBO> getAll() {
		log.debug("TypeReasonChangeCategoryBLImpl:getAll [START]");
		
		List<TypeReasonChangeCategoryBO> typeReasonChangeCategorys = new ArrayList<TypeReasonChangeCategoryBO>();

		List<TypeReasonChangeCategory> typeReasonChangeCategoryEntities = typeReasonChangeCategoryRepository.findAll();
		for (TypeReasonChangeCategory typeReasonChangeCategoryEntity : typeReasonChangeCategoryEntities) {
			typeReasonChangeCategorys.add(mapperTypeReasonChangeCategory.map(typeReasonChangeCategoryEntity, TypeReasonChangeCategoryBO.class));
		}
		log.debug("TypeReasonChangeCategoryBLImpl:getAll [END]");
		return typeReasonChangeCategorys;
	}

	@Override
	public TypeReasonChangeCategoryBO add(TypeReasonChangeCategoryBO typeReasonChangeCategoryBO) {
		log.debug("TypeReasonChangeCategoryBLImpl:add [START]");
		TypeReasonChangeCategory typeReasonChangeCategoryEntity = mapperTypeReasonChangeCategory.map(typeReasonChangeCategoryBO, TypeReasonChangeCategory.class);

		Util.getDateUser(typeReasonChangeCategoryEntity, "INSERT");

		log.debug("TypeReasonChangeCategoryBLImpl:add [END]");
		return mapperTypeReasonChangeCategory.map(typeReasonChangeCategoryRepository.save(typeReasonChangeCategoryEntity), TypeReasonChangeCategoryBO.class);
	}

	@Override
	public TypeReasonChangeCategoryBO update(Long typeReasonChangeCategoryId, TypeReasonChangeCategoryBO typeReasonChangeCategoryBO) {
		TypeReasonChangeCategory typeReasonChangeCategoryEntity = typeReasonChangeCategoryRepository.getOne(typeReasonChangeCategoryId);
		if (typeReasonChangeCategoryEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeReasonChangeCategoryEntity, "UPDATE");
			
			return mapperTypeReasonChangeCategory.map(typeReasonChangeCategoryRepository.save(typeReasonChangeCategoryEntity), TypeReasonChangeCategoryBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeReasonChangeCategoryId) {
		TypeReasonChangeCategory typeReasonChangeCategoryEntity = typeReasonChangeCategoryRepository.getOne(typeReasonChangeCategoryId);
		if (typeReasonChangeCategoryEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeReasonChangeCategoryEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeReasonChangeCategoryEntity, "UPDATE");
			
			mapperTypeReasonChangeCategory.map(typeReasonChangeCategoryRepository.save(typeReasonChangeCategoryEntity), TypeReasonChangeCategoryBO.class);
			
			return true;
		}

		return false;
	}

}
