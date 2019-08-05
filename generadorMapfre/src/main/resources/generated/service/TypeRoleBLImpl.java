package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeRole;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeRoleRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeRoleBLImpl implements ITypeRoleBL {

	private TypeRoleRepository typeRoleRepository;
	private MapperFacade mapperTypeRole;

	@Autowired
	public TypeRoleBLImpl(TypeRoleRepository typeRoleRepository) {
		this.typeRoleRepository = typeRoleRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeRole.class, TypeRoleBO.class).byDefault().register();
		this.mapperTypeRole = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeRoleBO> getAll() {
		log.debug("TypeRoleBLImpl:getAll [START]");
		
		List<TypeRoleBO> typeRoles = new ArrayList<TypeRoleBO>();

		List<TypeRole> typeRoleEntities = typeRoleRepository.findAll();
		for (TypeRole typeRoleEntity : typeRoleEntities) {
			typeRoles.add(mapperTypeRole.map(typeRoleEntity, TypeRoleBO.class));
		}
		log.debug("TypeRoleBLImpl:getAll [END]");
		return typeRoles;
	}

	@Override
	public TypeRoleBO add(TypeRoleBO typeRoleBO) {
		log.debug("TypeRoleBLImpl:add [START]");
		TypeRole typeRoleEntity = mapperTypeRole.map(typeRoleBO, TypeRole.class);

		Util.getDateUser(typeRoleEntity, "INSERT");

		log.debug("TypeRoleBLImpl:add [END]");
		return mapperTypeRole.map(typeRoleRepository.save(typeRoleEntity), TypeRoleBO.class);
	}

	@Override
	public TypeRoleBO update(Long typeRoleId, TypeRoleBO typeRoleBO) {
		TypeRole typeRoleEntity = typeRoleRepository.getOne(typeRoleId);
		if (typeRoleEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeRoleEntity, "UPDATE");
			
			return mapperTypeRole.map(typeRoleRepository.save(typeRoleEntity), TypeRoleBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeRoleId) {
		TypeRole typeRoleEntity = typeRoleRepository.getOne(typeRoleId);
		if (typeRoleEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeRoleEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeRoleEntity, "UPDATE");
			
			mapperTypeRole.map(typeRoleRepository.save(typeRoleEntity), TypeRoleBO.class);
			
			return true;
		}

		return false;
	}

}
