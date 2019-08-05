package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.Currency;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.CurrencyRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class CurrencyBLImpl implements ICurrencyBL {

	private CurrencyRepository currencyRepository;
	private MapperFacade mapperCurrency;

	@Autowired
	public CurrencyBLImpl(CurrencyRepository currencyRepository) {
		this.currencyRepository = currencyRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Currency.class, CurrencyBO.class).byDefault().register();
		this.mapperCurrency = mapperFactory.getMapperFacade();

	}

	@Override
	public List<CurrencyBO> getAll() {
		log.debug("CurrencyBLImpl:getAll [START]");
		
		List<CurrencyBO> currencys = new ArrayList<CurrencyBO>();

		List<Currency> currencyEntities = currencyRepository.findAll();
		for (Currency currencyEntity : currencyEntities) {
			currencys.add(mapperCurrency.map(currencyEntity, CurrencyBO.class));
		}
		log.debug("CurrencyBLImpl:getAll [END]");
		return currencys;
	}

	@Override
	public CurrencyBO add(CurrencyBO currencyBO) {
		log.debug("CurrencyBLImpl:add [START]");
		Currency currencyEntity = mapperCurrency.map(currencyBO, Currency.class);

		Util.getDateUser(currencyEntity, "INSERT");

		log.debug("CurrencyBLImpl:add [END]");
		return mapperCurrency.map(currencyRepository.save(currencyEntity), CurrencyBO.class);
	}

	@Override
	public CurrencyBO update(Long currencyId, CurrencyBO currencyBO) {
		Currency currencyEntity = currencyRepository.getOne(currencyId);
		if (currencyEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(currencyEntity, "UPDATE");
			
			return mapperCurrency.map(currencyRepository.save(currencyEntity), CurrencyBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long currencyId) {
		Currency currencyEntity = currencyRepository.getOne(currencyId);
		if (currencyEntity != null) {
		
			//TODO Baja logica o fisica?
		
			currencyEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(currencyEntity, "UPDATE");
			
			mapperCurrency.map(currencyRepository.save(currencyEntity), CurrencyBO.class);
			
			return true;
		}

		return false;
	}

}
