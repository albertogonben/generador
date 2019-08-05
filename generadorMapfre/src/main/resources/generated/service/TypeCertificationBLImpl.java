package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeCertification;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeCertificationRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeCertificationBLImpl implements ITypeCertificationBL {

	private TypeCertificationRepository typeCertificationRepository;
	private MapperFacade mapperTypeCertification;

	@Autowired
	public TypeCertificationBLImpl(TypeCertificationRepository typeCertificationRepository) {
		this.typeCertificationRepository = typeCertificationRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeCertification.class, TypeCertificationBO.class).byDefault().register();
		this.mapperTypeCertification = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeCertificationBO> getAll() {
		log.debug("TypeCertificationBLImpl:getAll [START]");
		
		List<TypeCertificationBO> typeCertifications = new ArrayList<TypeCertificationBO>();

		List<TypeCertification> typeCertificationEntities = typeCertificationRepository.findAll();
		for (TypeCertification typeCertificationEntity : typeCertificationEntities) {
			typeCertifications.add(mapperTypeCertification.map(typeCertificationEntity, TypeCertificationBO.class));
		}
		log.debug("TypeCertificationBLImpl:getAll [END]");
		return typeCertifications;
	}

	@Override
	public TypeCertificationBO add(TypeCertificationBO typeCertificationBO) {
		log.debug("TypeCertificationBLImpl:add [START]");
		TypeCertification typeCertificationEntity = mapperTypeCertification.map(typeCertificationBO, TypeCertification.class);

		Util.getDateUser(typeCertificationEntity, "INSERT");

		log.debug("TypeCertificationBLImpl:add [END]");
		return mapperTypeCertification.map(typeCertificationRepository.save(typeCertificationEntity), TypeCertificationBO.class);
	}

	@Override
	public TypeCertificationBO update(Long typeCertificationId, TypeCertificationBO typeCertificationBO) {
		TypeCertification typeCertificationEntity = typeCertificationRepository.getOne(typeCertificationId);
		if (typeCertificationEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeCertificationEntity, "UPDATE");
			
			return mapperTypeCertification.map(typeCertificationRepository.save(typeCertificationEntity), TypeCertificationBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeCertificationId) {
		TypeCertification typeCertificationEntity = typeCertificationRepository.getOne(typeCertificationId);
		if (typeCertificationEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeCertificationEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeCertificationEntity, "UPDATE");
			
			mapperTypeCertification.map(typeCertificationRepository.save(typeCertificationEntity), TypeCertificationBO.class);
			
			return true;
		}

		return false;
	}

}
