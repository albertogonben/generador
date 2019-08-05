package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeCategoryProfessional;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeCategoryProfessionalRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeCategoryProfessionalBLImpl implements ITypeCategoryProfessionalBL {

	private TypeCategoryProfessionalRepository typeCategoryProfessionalRepository;
	private MapperFacade mapperTypeCategoryProfessional;

	@Autowired
	public TypeCategoryProfessionalBLImpl(TypeCategoryProfessionalRepository typeCategoryProfessionalRepository) {
		this.typeCategoryProfessionalRepository = typeCategoryProfessionalRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeCategoryProfessional.class, TypeCategoryProfessionalBO.class).byDefault().register();
		this.mapperTypeCategoryProfessional = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeCategoryProfessionalBO> getAll() {
		log.debug("TypeCategoryProfessionalBLImpl:getAll [START]");
		
		List<TypeCategoryProfessionalBO> typeCategoryProfessionals = new ArrayList<TypeCategoryProfessionalBO>();

		List<TypeCategoryProfessional> typeCategoryProfessionalEntities = typeCategoryProfessionalRepository.findAll();
		for (TypeCategoryProfessional typeCategoryProfessionalEntity : typeCategoryProfessionalEntities) {
			typeCategoryProfessionals.add(mapperTypeCategoryProfessional.map(typeCategoryProfessionalEntity, TypeCategoryProfessionalBO.class));
		}
		log.debug("TypeCategoryProfessionalBLImpl:getAll [END]");
		return typeCategoryProfessionals;
	}

	@Override
	public TypeCategoryProfessionalBO add(TypeCategoryProfessionalBO typeCategoryProfessionalBO) {
		log.debug("TypeCategoryProfessionalBLImpl:add [START]");
		TypeCategoryProfessional typeCategoryProfessionalEntity = mapperTypeCategoryProfessional.map(typeCategoryProfessionalBO, TypeCategoryProfessional.class);

		Util.getDateUser(typeCategoryProfessionalEntity, "INSERT");

		log.debug("TypeCategoryProfessionalBLImpl:add [END]");
		return mapperTypeCategoryProfessional.map(typeCategoryProfessionalRepository.save(typeCategoryProfessionalEntity), TypeCategoryProfessionalBO.class);
	}

	@Override
	public TypeCategoryProfessionalBO update(Long typeCategoryProfessionalId, TypeCategoryProfessionalBO typeCategoryProfessionalBO) {
		TypeCategoryProfessional typeCategoryProfessionalEntity = typeCategoryProfessionalRepository.getOne(typeCategoryProfessionalId);
		if (typeCategoryProfessionalEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeCategoryProfessionalEntity, "UPDATE");
			
			return mapperTypeCategoryProfessional.map(typeCategoryProfessionalRepository.save(typeCategoryProfessionalEntity), TypeCategoryProfessionalBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeCategoryProfessionalId) {
		TypeCategoryProfessional typeCategoryProfessionalEntity = typeCategoryProfessionalRepository.getOne(typeCategoryProfessionalId);
		if (typeCategoryProfessionalEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeCategoryProfessionalEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeCategoryProfessionalEntity, "UPDATE");
			
			mapperTypeCategoryProfessional.map(typeCategoryProfessionalRepository.save(typeCategoryProfessionalEntity), TypeCategoryProfessionalBO.class);
			
			return true;
		}

		return false;
	}

}
