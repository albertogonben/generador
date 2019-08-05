package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeOrganizationalStructure;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeOrganizationalStructureRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeOrganizationalStructureBLImpl implements ITypeOrganizationalStructureBL {

	private TypeOrganizationalStructureRepository typeOrganizationalStructureRepository;
	private MapperFacade mapperTypeOrganizationalStructure;

	@Autowired
	public TypeOrganizationalStructureBLImpl(TypeOrganizationalStructureRepository typeOrganizationalStructureRepository) {
		this.typeOrganizationalStructureRepository = typeOrganizationalStructureRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeOrganizationalStructure.class, TypeOrganizationalStructureBO.class).byDefault().register();
		this.mapperTypeOrganizationalStructure = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeOrganizationalStructureBO> getAll() {
		log.debug("TypeOrganizationalStructureBLImpl:getAll [START]");
		
		List<TypeOrganizationalStructureBO> typeOrganizationalStructures = new ArrayList<TypeOrganizationalStructureBO>();

		List<TypeOrganizationalStructure> typeOrganizationalStructureEntities = typeOrganizationalStructureRepository.findAll();
		for (TypeOrganizationalStructure typeOrganizationalStructureEntity : typeOrganizationalStructureEntities) {
			typeOrganizationalStructures.add(mapperTypeOrganizationalStructure.map(typeOrganizationalStructureEntity, TypeOrganizationalStructureBO.class));
		}
		log.debug("TypeOrganizationalStructureBLImpl:getAll [END]");
		return typeOrganizationalStructures;
	}

	@Override
	public TypeOrganizationalStructureBO add(TypeOrganizationalStructureBO typeOrganizationalStructureBO) {
		log.debug("TypeOrganizationalStructureBLImpl:add [START]");
		TypeOrganizationalStructure typeOrganizationalStructureEntity = mapperTypeOrganizationalStructure.map(typeOrganizationalStructureBO, TypeOrganizationalStructure.class);

		Util.getDateUser(typeOrganizationalStructureEntity, "INSERT");

		log.debug("TypeOrganizationalStructureBLImpl:add [END]");
		return mapperTypeOrganizationalStructure.map(typeOrganizationalStructureRepository.save(typeOrganizationalStructureEntity), TypeOrganizationalStructureBO.class);
	}

	@Override
	public TypeOrganizationalStructureBO update(Long typeOrganizationalStructureId, TypeOrganizationalStructureBO typeOrganizationalStructureBO) {
		TypeOrganizationalStructure typeOrganizationalStructureEntity = typeOrganizationalStructureRepository.getOne(typeOrganizationalStructureId);
		if (typeOrganizationalStructureEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeOrganizationalStructureEntity, "UPDATE");
			
			return mapperTypeOrganizationalStructure.map(typeOrganizationalStructureRepository.save(typeOrganizationalStructureEntity), TypeOrganizationalStructureBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeOrganizationalStructureId) {
		TypeOrganizationalStructure typeOrganizationalStructureEntity = typeOrganizationalStructureRepository.getOne(typeOrganizationalStructureId);
		if (typeOrganizationalStructureEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeOrganizationalStructureEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeOrganizationalStructureEntity, "UPDATE");
			
			mapperTypeOrganizationalStructure.map(typeOrganizationalStructureRepository.save(typeOrganizationalStructureEntity), TypeOrganizationalStructureBO.class);
			
			return true;
		}

		return false;
	}

}
