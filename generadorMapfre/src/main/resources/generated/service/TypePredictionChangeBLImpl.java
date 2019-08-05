package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypePredictionChange;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypePredictionChangeRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypePredictionChangeBLImpl implements ITypePredictionChangeBL {

	private TypePredictionChangeRepository typePredictionChangeRepository;
	private MapperFacade mapperTypePredictionChange;

	@Autowired
	public TypePredictionChangeBLImpl(TypePredictionChangeRepository typePredictionChangeRepository) {
		this.typePredictionChangeRepository = typePredictionChangeRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypePredictionChange.class, TypePredictionChangeBO.class).byDefault().register();
		this.mapperTypePredictionChange = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypePredictionChangeBO> getAll() {
		log.debug("TypePredictionChangeBLImpl:getAll [START]");
		
		List<TypePredictionChangeBO> typePredictionChanges = new ArrayList<TypePredictionChangeBO>();

		List<TypePredictionChange> typePredictionChangeEntities = typePredictionChangeRepository.findAll();
		for (TypePredictionChange typePredictionChangeEntity : typePredictionChangeEntities) {
			typePredictionChanges.add(mapperTypePredictionChange.map(typePredictionChangeEntity, TypePredictionChangeBO.class));
		}
		log.debug("TypePredictionChangeBLImpl:getAll [END]");
		return typePredictionChanges;
	}

	@Override
	public TypePredictionChangeBO add(TypePredictionChangeBO typePredictionChangeBO) {
		log.debug("TypePredictionChangeBLImpl:add [START]");
		TypePredictionChange typePredictionChangeEntity = mapperTypePredictionChange.map(typePredictionChangeBO, TypePredictionChange.class);

		Util.getDateUser(typePredictionChangeEntity, "INSERT");

		log.debug("TypePredictionChangeBLImpl:add [END]");
		return mapperTypePredictionChange.map(typePredictionChangeRepository.save(typePredictionChangeEntity), TypePredictionChangeBO.class);
	}

	@Override
	public TypePredictionChangeBO update(Long typePredictionChangeId, TypePredictionChangeBO typePredictionChangeBO) {
		TypePredictionChange typePredictionChangeEntity = typePredictionChangeRepository.getOne(typePredictionChangeId);
		if (typePredictionChangeEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typePredictionChangeEntity, "UPDATE");
			
			return mapperTypePredictionChange.map(typePredictionChangeRepository.save(typePredictionChangeEntity), TypePredictionChangeBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typePredictionChangeId) {
		TypePredictionChange typePredictionChangeEntity = typePredictionChangeRepository.getOne(typePredictionChangeId);
		if (typePredictionChangeEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typePredictionChangeEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typePredictionChangeEntity, "UPDATE");
			
			mapperTypePredictionChange.map(typePredictionChangeRepository.save(typePredictionChangeEntity), TypePredictionChangeBO.class);
			
			return true;
		}

		return false;
	}

}
