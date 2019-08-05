package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.Country;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.CountryRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class CountryBLImpl implements ICountryBL {

	private CountryRepository countryRepository;
	private MapperFacade mapperCountry;

	@Autowired
	public CountryBLImpl(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Country.class, CountryBO.class).byDefault().register();
		this.mapperCountry = mapperFactory.getMapperFacade();

	}

	@Override
	public List<CountryBO> getAll() {
		log.debug("CountryBLImpl:getAll [START]");
		
		List<CountryBO> countrys = new ArrayList<CountryBO>();

		List<Country> countryEntities = countryRepository.findAll();
		for (Country countryEntity : countryEntities) {
			countrys.add(mapperCountry.map(countryEntity, CountryBO.class));
		}
		log.debug("CountryBLImpl:getAll [END]");
		return countrys;
	}

	@Override
	public CountryBO add(CountryBO countryBO) {
		log.debug("CountryBLImpl:add [START]");
		Country countryEntity = mapperCountry.map(countryBO, Country.class);

		Util.getDateUser(countryEntity, "INSERT");

		log.debug("CountryBLImpl:add [END]");
		return mapperCountry.map(countryRepository.save(countryEntity), CountryBO.class);
	}

	@Override
	public CountryBO update(Long countryId, CountryBO countryBO) {
		Country countryEntity = countryRepository.getOne(countryId);
		if (countryEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(countryEntity, "UPDATE");
			
			return mapperCountry.map(countryRepository.save(countryEntity), CountryBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long countryId) {
		Country countryEntity = countryRepository.getOne(countryId);
		if (countryEntity != null) {
		
			//TODO Baja logica o fisica?
		
			countryEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(countryEntity, "UPDATE");
			
			mapperCountry.map(countryRepository.save(countryEntity), CountryBO.class);
			
			return true;
		}

		return false;
	}

}
