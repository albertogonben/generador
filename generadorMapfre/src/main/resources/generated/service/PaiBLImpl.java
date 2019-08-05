package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.Pai;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.PaiRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class PaiBLImpl implements IPaiBL {

	private PaiRepository paiRepository;
	private MapperFacade mapperPai;

	@Autowired
	public PaiBLImpl(PaiRepository paiRepository) {
		this.paiRepository = paiRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Pai.class, PaiBO.class).byDefault().register();
		this.mapperPai = mapperFactory.getMapperFacade();

	}

	@Override
	public List<PaiBO> getAll() {
		log.debug("PaiBLImpl:getAll [START]");
		
		List<PaiBO> pais = new ArrayList<PaiBO>();

		List<Pai> paiEntities = paiRepository.findAll();
		for (Pai paiEntity : paiEntities) {
			pais.add(mapperPai.map(paiEntity, PaiBO.class));
		}
		log.debug("PaiBLImpl:getAll [END]");
		return pais;
	}

	@Override
	public PaiBO add(PaiBO paiBO) {
		log.debug("PaiBLImpl:add [START]");
		Pai paiEntity = mapperPai.map(paiBO, Pai.class);

		Util.getDateUser(paiEntity, "INSERT");

		log.debug("PaiBLImpl:add [END]");
		return mapperPai.map(paiRepository.save(paiEntity), PaiBO.class);
	}

	@Override
	public PaiBO update(Long paiId, PaiBO paiBO) {
		Pai paiEntity = paiRepository.getOne(paiId);
		if (paiEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(paiEntity, "UPDATE");
			
			return mapperPai.map(paiRepository.save(paiEntity), PaiBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long paiId) {
		Pai paiEntity = paiRepository.getOne(paiId);
		if (paiEntity != null) {
		
			//TODO Baja logica o fisica?
		
			paiEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(paiEntity, "UPDATE");
			
			mapperPai.map(paiRepository.save(paiEntity), PaiBO.class);
			
			return true;
		}

		return false;
	}

}
