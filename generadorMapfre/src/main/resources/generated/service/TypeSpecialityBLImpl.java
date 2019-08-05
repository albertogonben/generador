package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeSpeciality;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeSpecialityRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeSpecialityBLImpl implements ITypeSpecialityBL {

	private TypeSpecialityRepository typeSpecialityRepository;
	private MapperFacade mapperTypeSpeciality;

	@Autowired
	public TypeSpecialityBLImpl(TypeSpecialityRepository typeSpecialityRepository) {
		this.typeSpecialityRepository = typeSpecialityRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeSpeciality.class, TypeSpecialityBO.class).byDefault().register();
		this.mapperTypeSpeciality = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeSpecialityBO> getAll() {
		log.debug("TypeSpecialityBLImpl:getAll [START]");
		
		List<TypeSpecialityBO> typeSpecialitys = new ArrayList<TypeSpecialityBO>();

		List<TypeSpeciality> typeSpecialityEntities = typeSpecialityRepository.findAll();
		for (TypeSpeciality typeSpecialityEntity : typeSpecialityEntities) {
			typeSpecialitys.add(mapperTypeSpeciality.map(typeSpecialityEntity, TypeSpecialityBO.class));
		}
		log.debug("TypeSpecialityBLImpl:getAll [END]");
		return typeSpecialitys;
	}

	@Override
	public TypeSpecialityBO add(TypeSpecialityBO typeSpecialityBO) {
		log.debug("TypeSpecialityBLImpl:add [START]");
		TypeSpeciality typeSpecialityEntity = mapperTypeSpeciality.map(typeSpecialityBO, TypeSpeciality.class);

		Util.getDateUser(typeSpecialityEntity, "INSERT");

		log.debug("TypeSpecialityBLImpl:add [END]");
		return mapperTypeSpeciality.map(typeSpecialityRepository.save(typeSpecialityEntity), TypeSpecialityBO.class);
	}

	@Override
	public TypeSpecialityBO update(Long typeSpecialityId, TypeSpecialityBO typeSpecialityBO) {
		TypeSpeciality typeSpecialityEntity = typeSpecialityRepository.getOne(typeSpecialityId);
		if (typeSpecialityEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeSpecialityEntity, "UPDATE");
			
			return mapperTypeSpeciality.map(typeSpecialityRepository.save(typeSpecialityEntity), TypeSpecialityBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeSpecialityId) {
		TypeSpeciality typeSpecialityEntity = typeSpecialityRepository.getOne(typeSpecialityId);
		if (typeSpecialityEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeSpecialityEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeSpecialityEntity, "UPDATE");
			
			mapperTypeSpeciality.map(typeSpecialityRepository.save(typeSpecialityEntity), TypeSpecialityBO.class);
			
			return true;
		}

		return false;
	}

}
