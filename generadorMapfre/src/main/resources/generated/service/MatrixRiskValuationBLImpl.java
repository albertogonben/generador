package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.MatrixRiskValuation;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.MatrixRiskValuationRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class MatrixRiskValuationBLImpl implements IMatrixRiskValuationBL {

	private MatrixRiskValuationRepository matrixRiskValuationRepository;
	private MapperFacade mapperMatrixRiskValuation;

	@Autowired
	public MatrixRiskValuationBLImpl(MatrixRiskValuationRepository matrixRiskValuationRepository) {
		this.matrixRiskValuationRepository = matrixRiskValuationRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(MatrixRiskValuation.class, MatrixRiskValuationBO.class).byDefault().register();
		this.mapperMatrixRiskValuation = mapperFactory.getMapperFacade();

	}

	@Override
	public List<MatrixRiskValuationBO> getAll() {
		log.debug("MatrixRiskValuationBLImpl:getAll [START]");
		
		List<MatrixRiskValuationBO> matrixRiskValuations = new ArrayList<MatrixRiskValuationBO>();

		List<MatrixRiskValuation> matrixRiskValuationEntities = matrixRiskValuationRepository.findAll();
		for (MatrixRiskValuation matrixRiskValuationEntity : matrixRiskValuationEntities) {
			matrixRiskValuations.add(mapperMatrixRiskValuation.map(matrixRiskValuationEntity, MatrixRiskValuationBO.class));
		}
		log.debug("MatrixRiskValuationBLImpl:getAll [END]");
		return matrixRiskValuations;
	}

	@Override
	public MatrixRiskValuationBO add(MatrixRiskValuationBO matrixRiskValuationBO) {
		log.debug("MatrixRiskValuationBLImpl:add [START]");
		MatrixRiskValuation matrixRiskValuationEntity = mapperMatrixRiskValuation.map(matrixRiskValuationBO, MatrixRiskValuation.class);

		Util.getDateUser(matrixRiskValuationEntity, "INSERT");

		log.debug("MatrixRiskValuationBLImpl:add [END]");
		return mapperMatrixRiskValuation.map(matrixRiskValuationRepository.save(matrixRiskValuationEntity), MatrixRiskValuationBO.class);
	}

	@Override
	public MatrixRiskValuationBO update(Long matrixRiskValuationId, MatrixRiskValuationBO matrixRiskValuationBO) {
		MatrixRiskValuation matrixRiskValuationEntity = matrixRiskValuationRepository.getOne(matrixRiskValuationId);
		if (matrixRiskValuationEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(matrixRiskValuationEntity, "UPDATE");
			
			return mapperMatrixRiskValuation.map(matrixRiskValuationRepository.save(matrixRiskValuationEntity), MatrixRiskValuationBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long matrixRiskValuationId) {
		MatrixRiskValuation matrixRiskValuationEntity = matrixRiskValuationRepository.getOne(matrixRiskValuationId);
		if (matrixRiskValuationEntity != null) {
		
			//TODO Baja logica o fisica?
		
			matrixRiskValuationEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(matrixRiskValuationEntity, "UPDATE");
			
			mapperMatrixRiskValuation.map(matrixRiskValuationRepository.save(matrixRiskValuationEntity), MatrixRiskValuationBO.class);
			
			return true;
		}

		return false;
	}

}
