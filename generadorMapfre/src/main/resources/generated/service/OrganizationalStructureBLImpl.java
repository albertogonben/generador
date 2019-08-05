package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.OrganizationalStructure;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.OrganizationalStructureRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class OrganizationalStructureBLImpl implements IOrganizationalStructureBL {

	private OrganizationalStructureRepository organizationalStructureRepository;
	private MapperFacade mapperOrganizationalStructure;

	@Autowired
	public OrganizationalStructureBLImpl(OrganizationalStructureRepository organizationalStructureRepository) {
		this.organizationalStructureRepository = organizationalStructureRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(OrganizationalStructure.class, OrganizationalStructureBO.class).byDefault().register();
		this.mapperOrganizationalStructure = mapperFactory.getMapperFacade();

	}

	@Override
	public List<OrganizationalStructureBO> getAll() {
		log.debug("OrganizationalStructureBLImpl:getAll [START]");
		
		List<OrganizationalStructureBO> organizationalStructures = new ArrayList<OrganizationalStructureBO>();

		List<OrganizationalStructure> organizationalStructureEntities = organizationalStructureRepository.findAll();
		for (OrganizationalStructure organizationalStructureEntity : organizationalStructureEntities) {
			organizationalStructures.add(mapperOrganizationalStructure.map(organizationalStructureEntity, OrganizationalStructureBO.class));
		}
		log.debug("OrganizationalStructureBLImpl:getAll [END]");
		return organizationalStructures;
	}

	@Override
	public OrganizationalStructureBO add(OrganizationalStructureBO organizationalStructureBO) {
		log.debug("OrganizationalStructureBLImpl:add [START]");
		OrganizationalStructure organizationalStructureEntity = mapperOrganizationalStructure.map(organizationalStructureBO, OrganizationalStructure.class);

		Util.getDateUser(organizationalStructureEntity, "INSERT");

		log.debug("OrganizationalStructureBLImpl:add [END]");
		return mapperOrganizationalStructure.map(organizationalStructureRepository.save(organizationalStructureEntity), OrganizationalStructureBO.class);
	}

	@Override
	public OrganizationalStructureBO update(Long organizationalStructureId, OrganizationalStructureBO organizationalStructureBO) {
		OrganizationalStructure organizationalStructureEntity = organizationalStructureRepository.getOne(organizationalStructureId);
		if (organizationalStructureEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(organizationalStructureEntity, "UPDATE");
			
			return mapperOrganizationalStructure.map(organizationalStructureRepository.save(organizationalStructureEntity), OrganizationalStructureBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long organizationalStructureId) {
		OrganizationalStructure organizationalStructureEntity = organizationalStructureRepository.getOne(organizationalStructureId);
		if (organizationalStructureEntity != null) {
		
			//TODO Baja logica o fisica?
		
			organizationalStructureEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(organizationalStructureEntity, "UPDATE");
			
			mapperOrganizationalStructure.map(organizationalStructureRepository.save(organizationalStructureEntity), OrganizationalStructureBO.class);
			
			return true;
		}

		return false;
	}

}
