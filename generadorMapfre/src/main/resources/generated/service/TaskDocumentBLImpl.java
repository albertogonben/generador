package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TaskDocument;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TaskDocumentRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TaskDocumentBLImpl implements ITaskDocumentBL {

	private TaskDocumentRepository taskDocumentRepository;
	private MapperFacade mapperTaskDocument;

	@Autowired
	public TaskDocumentBLImpl(TaskDocumentRepository taskDocumentRepository) {
		this.taskDocumentRepository = taskDocumentRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TaskDocument.class, TaskDocumentBO.class).byDefault().register();
		this.mapperTaskDocument = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TaskDocumentBO> getAll() {
		log.debug("TaskDocumentBLImpl:getAll [START]");
		
		List<TaskDocumentBO> taskDocuments = new ArrayList<TaskDocumentBO>();

		List<TaskDocument> taskDocumentEntities = taskDocumentRepository.findAll();
		for (TaskDocument taskDocumentEntity : taskDocumentEntities) {
			taskDocuments.add(mapperTaskDocument.map(taskDocumentEntity, TaskDocumentBO.class));
		}
		log.debug("TaskDocumentBLImpl:getAll [END]");
		return taskDocuments;
	}

	@Override
	public TaskDocumentBO add(TaskDocumentBO taskDocumentBO) {
		log.debug("TaskDocumentBLImpl:add [START]");
		TaskDocument taskDocumentEntity = mapperTaskDocument.map(taskDocumentBO, TaskDocument.class);

		Util.getDateUser(taskDocumentEntity, "INSERT");

		log.debug("TaskDocumentBLImpl:add [END]");
		return mapperTaskDocument.map(taskDocumentRepository.save(taskDocumentEntity), TaskDocumentBO.class);
	}

	@Override
	public TaskDocumentBO update(Long taskDocumentId, TaskDocumentBO taskDocumentBO) {
		TaskDocument taskDocumentEntity = taskDocumentRepository.getOne(taskDocumentId);
		if (taskDocumentEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(taskDocumentEntity, "UPDATE");
			
			return mapperTaskDocument.map(taskDocumentRepository.save(taskDocumentEntity), TaskDocumentBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long taskDocumentId) {
		TaskDocument taskDocumentEntity = taskDocumentRepository.getOne(taskDocumentId);
		if (taskDocumentEntity != null) {
		
			//TODO Baja logica o fisica?
		
			taskDocumentEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(taskDocumentEntity, "UPDATE");
			
			mapperTaskDocument.map(taskDocumentRepository.save(taskDocumentEntity), TaskDocumentBO.class);
			
			return true;
		}

		return false;
	}

}
