package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeNode;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeNodeRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeNodeBLImpl implements ITypeNodeBL {

	private TypeNodeRepository typeNodeRepository;
	private MapperFacade mapperTypeNode;

	@Autowired
	public TypeNodeBLImpl(TypeNodeRepository typeNodeRepository) {
		this.typeNodeRepository = typeNodeRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeNode.class, TypeNodeBO.class).byDefault().register();
		this.mapperTypeNode = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeNodeBO> getAll() {
		log.debug("TypeNodeBLImpl:getAll [START]");
		
		List<TypeNodeBO> typeNodes = new ArrayList<TypeNodeBO>();

		List<TypeNode> typeNodeEntities = typeNodeRepository.findAll();
		for (TypeNode typeNodeEntity : typeNodeEntities) {
			typeNodes.add(mapperTypeNode.map(typeNodeEntity, TypeNodeBO.class));
		}
		log.debug("TypeNodeBLImpl:getAll [END]");
		return typeNodes;
	}

	@Override
	public TypeNodeBO add(TypeNodeBO typeNodeBO) {
		log.debug("TypeNodeBLImpl:add [START]");
		TypeNode typeNodeEntity = mapperTypeNode.map(typeNodeBO, TypeNode.class);

		Util.getDateUser(typeNodeEntity, "INSERT");

		log.debug("TypeNodeBLImpl:add [END]");
		return mapperTypeNode.map(typeNodeRepository.save(typeNodeEntity), TypeNodeBO.class);
	}

	@Override
	public TypeNodeBO update(Long typeNodeId, TypeNodeBO typeNodeBO) {
		TypeNode typeNodeEntity = typeNodeRepository.getOne(typeNodeId);
		if (typeNodeEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeNodeEntity, "UPDATE");
			
			return mapperTypeNode.map(typeNodeRepository.save(typeNodeEntity), TypeNodeBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeNodeId) {
		TypeNode typeNodeEntity = typeNodeRepository.getOne(typeNodeId);
		if (typeNodeEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeNodeEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeNodeEntity, "UPDATE");
			
			mapperTypeNode.map(typeNodeRepository.save(typeNodeEntity), TypeNodeBO.class);
			
			return true;
		}

		return false;
	}

}
