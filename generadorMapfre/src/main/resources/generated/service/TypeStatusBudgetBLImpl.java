package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeStatusBudget;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeStatusBudgetRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeStatusBudgetBLImpl implements ITypeStatusBudgetBL {

	private TypeStatusBudgetRepository typeStatusBudgetRepository;
	private MapperFacade mapperTypeStatusBudget;

	@Autowired
	public TypeStatusBudgetBLImpl(TypeStatusBudgetRepository typeStatusBudgetRepository) {
		this.typeStatusBudgetRepository = typeStatusBudgetRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeStatusBudget.class, TypeStatusBudgetBO.class).byDefault().register();
		this.mapperTypeStatusBudget = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeStatusBudgetBO> getAll() {
		log.debug("TypeStatusBudgetBLImpl:getAll [START]");
		
		List<TypeStatusBudgetBO> typeStatusBudgets = new ArrayList<TypeStatusBudgetBO>();

		List<TypeStatusBudget> typeStatusBudgetEntities = typeStatusBudgetRepository.findAll();
		for (TypeStatusBudget typeStatusBudgetEntity : typeStatusBudgetEntities) {
			typeStatusBudgets.add(mapperTypeStatusBudget.map(typeStatusBudgetEntity, TypeStatusBudgetBO.class));
		}
		log.debug("TypeStatusBudgetBLImpl:getAll [END]");
		return typeStatusBudgets;
	}

	@Override
	public TypeStatusBudgetBO add(TypeStatusBudgetBO typeStatusBudgetBO) {
		log.debug("TypeStatusBudgetBLImpl:add [START]");
		TypeStatusBudget typeStatusBudgetEntity = mapperTypeStatusBudget.map(typeStatusBudgetBO, TypeStatusBudget.class);

		Util.getDateUser(typeStatusBudgetEntity, "INSERT");

		log.debug("TypeStatusBudgetBLImpl:add [END]");
		return mapperTypeStatusBudget.map(typeStatusBudgetRepository.save(typeStatusBudgetEntity), TypeStatusBudgetBO.class);
	}

	@Override
	public TypeStatusBudgetBO update(Long typeStatusBudgetId, TypeStatusBudgetBO typeStatusBudgetBO) {
		TypeStatusBudget typeStatusBudgetEntity = typeStatusBudgetRepository.getOne(typeStatusBudgetId);
		if (typeStatusBudgetEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeStatusBudgetEntity, "UPDATE");
			
			return mapperTypeStatusBudget.map(typeStatusBudgetRepository.save(typeStatusBudgetEntity), TypeStatusBudgetBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeStatusBudgetId) {
		TypeStatusBudget typeStatusBudgetEntity = typeStatusBudgetRepository.getOne(typeStatusBudgetId);
		if (typeStatusBudgetEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeStatusBudgetEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeStatusBudgetEntity, "UPDATE");
			
			mapperTypeStatusBudget.map(typeStatusBudgetRepository.save(typeStatusBudgetEntity), TypeStatusBudgetBO.class);
			
			return true;
		}

		return false;
	}

}
