package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeReasonClose;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeReasonCloseRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeReasonCloseBLImpl implements ITypeReasonCloseBL {

	private TypeReasonCloseRepository typeReasonCloseRepository;
	private MapperFacade mapperTypeReasonClose;

	@Autowired
	public TypeReasonCloseBLImpl(TypeReasonCloseRepository typeReasonCloseRepository) {
		this.typeReasonCloseRepository = typeReasonCloseRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeReasonClose.class, TypeReasonCloseBO.class).byDefault().register();
		this.mapperTypeReasonClose = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeReasonCloseBO> getAll() {
		log.debug("TypeReasonCloseBLImpl:getAll [START]");
		
		List<TypeReasonCloseBO> typeReasonCloses = new ArrayList<TypeReasonCloseBO>();

		List<TypeReasonClose> typeReasonCloseEntities = typeReasonCloseRepository.findAll();
		for (TypeReasonClose typeReasonCloseEntity : typeReasonCloseEntities) {
			typeReasonCloses.add(mapperTypeReasonClose.map(typeReasonCloseEntity, TypeReasonCloseBO.class));
		}
		log.debug("TypeReasonCloseBLImpl:getAll [END]");
		return typeReasonCloses;
	}

	@Override
	public TypeReasonCloseBO add(TypeReasonCloseBO typeReasonCloseBO) {
		log.debug("TypeReasonCloseBLImpl:add [START]");
		TypeReasonClose typeReasonCloseEntity = mapperTypeReasonClose.map(typeReasonCloseBO, TypeReasonClose.class);

		Util.getDateUser(typeReasonCloseEntity, "INSERT");

		log.debug("TypeReasonCloseBLImpl:add [END]");
		return mapperTypeReasonClose.map(typeReasonCloseRepository.save(typeReasonCloseEntity), TypeReasonCloseBO.class);
	}

	@Override
	public TypeReasonCloseBO update(Long typeReasonCloseId, TypeReasonCloseBO typeReasonCloseBO) {
		TypeReasonClose typeReasonCloseEntity = typeReasonCloseRepository.getOne(typeReasonCloseId);
		if (typeReasonCloseEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeReasonCloseEntity, "UPDATE");
			
			return mapperTypeReasonClose.map(typeReasonCloseRepository.save(typeReasonCloseEntity), TypeReasonCloseBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeReasonCloseId) {
		TypeReasonClose typeReasonCloseEntity = typeReasonCloseRepository.getOne(typeReasonCloseId);
		if (typeReasonCloseEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeReasonCloseEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeReasonCloseEntity, "UPDATE");
			
			mapperTypeReasonClose.map(typeReasonCloseRepository.save(typeReasonCloseEntity), TypeReasonCloseBO.class);
			
			return true;
		}

		return false;
	}

}
