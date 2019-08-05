package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.IdeaDocument;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.IdeaDocumentRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class IdeaDocumentBLImpl implements IIdeaDocumentBL {

	private IdeaDocumentRepository ideaDocumentRepository;
	private MapperFacade mapperIdeaDocument;

	@Autowired
	public IdeaDocumentBLImpl(IdeaDocumentRepository ideaDocumentRepository) {
		this.ideaDocumentRepository = ideaDocumentRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(IdeaDocument.class, IdeaDocumentBO.class).byDefault().register();
		this.mapperIdeaDocument = mapperFactory.getMapperFacade();

	}

	@Override
	public List<IdeaDocumentBO> getAll() {
		log.debug("IdeaDocumentBLImpl:getAll [START]");
		
		List<IdeaDocumentBO> ideaDocuments = new ArrayList<IdeaDocumentBO>();

		List<IdeaDocument> ideaDocumentEntities = ideaDocumentRepository.findAll();
		for (IdeaDocument ideaDocumentEntity : ideaDocumentEntities) {
			ideaDocuments.add(mapperIdeaDocument.map(ideaDocumentEntity, IdeaDocumentBO.class));
		}
		log.debug("IdeaDocumentBLImpl:getAll [END]");
		return ideaDocuments;
	}

	@Override
	public IdeaDocumentBO add(IdeaDocumentBO ideaDocumentBO) {
		log.debug("IdeaDocumentBLImpl:add [START]");
		IdeaDocument ideaDocumentEntity = mapperIdeaDocument.map(ideaDocumentBO, IdeaDocument.class);

		Util.getDateUser(ideaDocumentEntity, "INSERT");

		log.debug("IdeaDocumentBLImpl:add [END]");
		return mapperIdeaDocument.map(ideaDocumentRepository.save(ideaDocumentEntity), IdeaDocumentBO.class);
	}

	@Override
	public IdeaDocumentBO update(Long ideaDocumentId, IdeaDocumentBO ideaDocumentBO) {
		IdeaDocument ideaDocumentEntity = ideaDocumentRepository.getOne(ideaDocumentId);
		if (ideaDocumentEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(ideaDocumentEntity, "UPDATE");
			
			return mapperIdeaDocument.map(ideaDocumentRepository.save(ideaDocumentEntity), IdeaDocumentBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long ideaDocumentId) {
		IdeaDocument ideaDocumentEntity = ideaDocumentRepository.getOne(ideaDocumentId);
		if (ideaDocumentEntity != null) {
		
			//TODO Baja logica o fisica?
		
			ideaDocumentEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(ideaDocumentEntity, "UPDATE");
			
			mapperIdeaDocument.map(ideaDocumentRepository.save(ideaDocumentEntity), IdeaDocumentBO.class);
			
			return true;
		}

		return false;
	}

}
