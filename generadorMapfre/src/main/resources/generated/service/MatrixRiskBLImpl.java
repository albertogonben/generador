package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.MatrixRisk;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.MatrixRiskRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class MatrixRiskBLImpl implements IMatrixRiskBL {

	private MatrixRiskRepository matrixRiskRepository;
	private MapperFacade mapperMatrixRisk;

	@Autowired
	public MatrixRiskBLImpl(MatrixRiskRepository matrixRiskRepository) {
		this.matrixRiskRepository = matrixRiskRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(MatrixRisk.class, MatrixRiskBO.class).byDefault().register();
		this.mapperMatrixRisk = mapperFactory.getMapperFacade();

	}

	@Override
	public List<MatrixRiskBO> getAll() {
		log.debug("MatrixRiskBLImpl:getAll [START]");
		
		List<MatrixRiskBO> matrixRisks = new ArrayList<MatrixRiskBO>();

		List<MatrixRisk> matrixRiskEntities = matrixRiskRepository.findAll();
		for (MatrixRisk matrixRiskEntity : matrixRiskEntities) {
			matrixRisks.add(mapperMatrixRisk.map(matrixRiskEntity, MatrixRiskBO.class));
		}
		log.debug("MatrixRiskBLImpl:getAll [END]");
		return matrixRisks;
	}

	@Override
	public MatrixRiskBO add(MatrixRiskBO matrixRiskBO) {
		log.debug("MatrixRiskBLImpl:add [START]");
		MatrixRisk matrixRiskEntity = mapperMatrixRisk.map(matrixRiskBO, MatrixRisk.class);

		Util.getDateUser(matrixRiskEntity, "INSERT");

		log.debug("MatrixRiskBLImpl:add [END]");
		return mapperMatrixRisk.map(matrixRiskRepository.save(matrixRiskEntity), MatrixRiskBO.class);
	}

	@Override
	public MatrixRiskBO update(Long matrixRiskId, MatrixRiskBO matrixRiskBO) {
		MatrixRisk matrixRiskEntity = matrixRiskRepository.getOne(matrixRiskId);
		if (matrixRiskEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(matrixRiskEntity, "UPDATE");
			
			return mapperMatrixRisk.map(matrixRiskRepository.save(matrixRiskEntity), MatrixRiskBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long matrixRiskId) {
		MatrixRisk matrixRiskEntity = matrixRiskRepository.getOne(matrixRiskId);
		if (matrixRiskEntity != null) {
		
			//TODO Baja logica o fisica?
		
			matrixRiskEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(matrixRiskEntity, "UPDATE");
			
			mapperMatrixRisk.map(matrixRiskRepository.save(matrixRiskEntity), MatrixRiskBO.class);
			
			return true;
		}

		return false;
	}

}
