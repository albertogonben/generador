package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeProcess;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeProcessRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeProcessBLImpl implements ITypeProcessBL {

	private TypeProcessRepository typeProcessRepository;
	private MapperFacade mapperTypeProcess;

	@Autowired
	public TypeProcessBLImpl(TypeProcessRepository typeProcessRepository) {
		this.typeProcessRepository = typeProcessRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeProcess.class, TypeProcessBO.class).byDefault().register();
		this.mapperTypeProcess = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeProcessBO> getAll() {
		log.debug("TypeProcessBLImpl:getAll [START]");
		
		List<TypeProcessBO> typeProcesss = new ArrayList<TypeProcessBO>();

		List<TypeProcess> typeProcessEntities = typeProcessRepository.findAll();
		for (TypeProcess typeProcessEntity : typeProcessEntities) {
			typeProcesss.add(mapperTypeProcess.map(typeProcessEntity, TypeProcessBO.class));
		}
		log.debug("TypeProcessBLImpl:getAll [END]");
		return typeProcesss;
	}

	@Override
	public TypeProcessBO add(TypeProcessBO typeProcessBO) {
		log.debug("TypeProcessBLImpl:add [START]");
		TypeProcess typeProcessEntity = mapperTypeProcess.map(typeProcessBO, TypeProcess.class);

		Util.getDateUser(typeProcessEntity, "INSERT");

		log.debug("TypeProcessBLImpl:add [END]");
		return mapperTypeProcess.map(typeProcessRepository.save(typeProcessEntity), TypeProcessBO.class);
	}

	@Override
	public TypeProcessBO update(Long typeProcessId, TypeProcessBO typeProcessBO) {
		TypeProcess typeProcessEntity = typeProcessRepository.getOne(typeProcessId);
		if (typeProcessEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeProcessEntity, "UPDATE");
			
			return mapperTypeProcess.map(typeProcessRepository.save(typeProcessEntity), TypeProcessBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeProcessId) {
		TypeProcess typeProcessEntity = typeProcessRepository.getOne(typeProcessId);
		if (typeProcessEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeProcessEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeProcessEntity, "UPDATE");
			
			mapperTypeProcess.map(typeProcessRepository.save(typeProcessEntity), TypeProcessBO.class);
			
			return true;
		}

		return false;
	}

}
