package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeStateNode;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeStateNodeRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeStateNodeBLImpl implements ITypeStateNodeBL {

	private TypeStateNodeRepository typeStateNodeRepository;
	private MapperFacade mapperTypeStateNode;

	@Autowired
	public TypeStateNodeBLImpl(TypeStateNodeRepository typeStateNodeRepository) {
		this.typeStateNodeRepository = typeStateNodeRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeStateNode.class, TypeStateNodeBO.class).byDefault().register();
		this.mapperTypeStateNode = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeStateNodeBO> getAll() {
		log.debug("TypeStateNodeBLImpl:getAll [START]");
		
		List<TypeStateNodeBO> typeStateNodes = new ArrayList<TypeStateNodeBO>();

		List<TypeStateNode> typeStateNodeEntities = typeStateNodeRepository.findAll();
		for (TypeStateNode typeStateNodeEntity : typeStateNodeEntities) {
			typeStateNodes.add(mapperTypeStateNode.map(typeStateNodeEntity, TypeStateNodeBO.class));
		}
		log.debug("TypeStateNodeBLImpl:getAll [END]");
		return typeStateNodes;
	}

	@Override
	public TypeStateNodeBO add(TypeStateNodeBO typeStateNodeBO) {
		log.debug("TypeStateNodeBLImpl:add [START]");
		TypeStateNode typeStateNodeEntity = mapperTypeStateNode.map(typeStateNodeBO, TypeStateNode.class);

		Util.getDateUser(typeStateNodeEntity, "INSERT");

		log.debug("TypeStateNodeBLImpl:add [END]");
		return mapperTypeStateNode.map(typeStateNodeRepository.save(typeStateNodeEntity), TypeStateNodeBO.class);
	}

	@Override
	public TypeStateNodeBO update(Long typeStateNodeId, TypeStateNodeBO typeStateNodeBO) {
		TypeStateNode typeStateNodeEntity = typeStateNodeRepository.getOne(typeStateNodeId);
		if (typeStateNodeEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeStateNodeEntity, "UPDATE");
			
			return mapperTypeStateNode.map(typeStateNodeRepository.save(typeStateNodeEntity), TypeStateNodeBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeStateNodeId) {
		TypeStateNode typeStateNodeEntity = typeStateNodeRepository.getOne(typeStateNodeId);
		if (typeStateNodeEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeStateNodeEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeStateNodeEntity, "UPDATE");
			
			mapperTypeStateNode.map(typeStateNodeRepository.save(typeStateNodeEntity), TypeStateNodeBO.class);
			
			return true;
		}

		return false;
	}

}
