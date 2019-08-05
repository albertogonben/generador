package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.ModuleApp;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.ModuleAppRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class ModuleAppBLImpl implements IModuleAppBL {

	private ModuleAppRepository moduleAppRepository;
	private MapperFacade mapperModuleApp;

	@Autowired
	public ModuleAppBLImpl(ModuleAppRepository moduleAppRepository) {
		this.moduleAppRepository = moduleAppRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(ModuleApp.class, ModuleAppBO.class).byDefault().register();
		this.mapperModuleApp = mapperFactory.getMapperFacade();

	}

	@Override
	public List<ModuleAppBO> getAll() {
		log.debug("ModuleAppBLImpl:getAll [START]");
		
		List<ModuleAppBO> moduleApps = new ArrayList<ModuleAppBO>();

		List<ModuleApp> moduleAppEntities = moduleAppRepository.findAll();
		for (ModuleApp moduleAppEntity : moduleAppEntities) {
			moduleApps.add(mapperModuleApp.map(moduleAppEntity, ModuleAppBO.class));
		}
		log.debug("ModuleAppBLImpl:getAll [END]");
		return moduleApps;
	}

	@Override
	public ModuleAppBO add(ModuleAppBO moduleAppBO) {
		log.debug("ModuleAppBLImpl:add [START]");
		ModuleApp moduleAppEntity = mapperModuleApp.map(moduleAppBO, ModuleApp.class);

		Util.getDateUser(moduleAppEntity, "INSERT");

		log.debug("ModuleAppBLImpl:add [END]");
		return mapperModuleApp.map(moduleAppRepository.save(moduleAppEntity), ModuleAppBO.class);
	}

	@Override
	public ModuleAppBO update(Long moduleAppId, ModuleAppBO moduleAppBO) {
		ModuleApp moduleAppEntity = moduleAppRepository.getOne(moduleAppId);
		if (moduleAppEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(moduleAppEntity, "UPDATE");
			
			return mapperModuleApp.map(moduleAppRepository.save(moduleAppEntity), ModuleAppBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long moduleAppId) {
		ModuleApp moduleAppEntity = moduleAppRepository.getOne(moduleAppId);
		if (moduleAppEntity != null) {
		
			//TODO Baja logica o fisica?
		
			moduleAppEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(moduleAppEntity, "UPDATE");
			
			mapperModuleApp.map(moduleAppRepository.save(moduleAppEntity), ModuleAppBO.class);
			
			return true;
		}

		return false;
	}

}
