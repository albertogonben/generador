package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.Budget;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.BudgetRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class BudgetBLImpl implements IBudgetBL {

	private BudgetRepository budgetRepository;
	private MapperFacade mapperBudget;

	@Autowired
	public BudgetBLImpl(BudgetRepository budgetRepository) {
		this.budgetRepository = budgetRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Budget.class, BudgetBO.class).byDefault().register();
		this.mapperBudget = mapperFactory.getMapperFacade();

	}

	@Override
	public List<BudgetBO> getAll() {
		log.debug("BudgetBLImpl:getAll [START]");
		
		List<BudgetBO> budgets = new ArrayList<BudgetBO>();

		List<Budget> budgetEntities = budgetRepository.findAll();
		for (Budget budgetEntity : budgetEntities) {
			budgets.add(mapperBudget.map(budgetEntity, BudgetBO.class));
		}
		log.debug("BudgetBLImpl:getAll [END]");
		return budgets;
	}

	@Override
	public BudgetBO add(BudgetBO budgetBO) {
		log.debug("BudgetBLImpl:add [START]");
		Budget budgetEntity = mapperBudget.map(budgetBO, Budget.class);

		Util.getDateUser(budgetEntity, "INSERT");

		log.debug("BudgetBLImpl:add [END]");
		return mapperBudget.map(budgetRepository.save(budgetEntity), BudgetBO.class);
	}

	@Override
	public BudgetBO update(Long budgetId, BudgetBO budgetBO) {
		Budget budgetEntity = budgetRepository.getOne(budgetId);
		if (budgetEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(budgetEntity, "UPDATE");
			
			return mapperBudget.map(budgetRepository.save(budgetEntity), BudgetBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long budgetId) {
		Budget budgetEntity = budgetRepository.getOne(budgetId);
		if (budgetEntity != null) {
		
			//TODO Baja logica o fisica?
		
			budgetEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(budgetEntity, "UPDATE");
			
			mapperBudget.map(budgetRepository.save(budgetEntity), BudgetBO.class);
			
			return true;
		}

		return false;
	}

}
