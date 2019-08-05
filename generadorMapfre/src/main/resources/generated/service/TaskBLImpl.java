package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.Task;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TaskRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TaskBLImpl implements ITaskBL {

	private TaskRepository taskRepository;
	private MapperFacade mapperTask;

	@Autowired
	public TaskBLImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Task.class, TaskBO.class).byDefault().register();
		this.mapperTask = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TaskBO> getAll() {
		log.debug("TaskBLImpl:getAll [START]");
		
		List<TaskBO> tasks = new ArrayList<TaskBO>();

		List<Task> taskEntities = taskRepository.findAll();
		for (Task taskEntity : taskEntities) {
			tasks.add(mapperTask.map(taskEntity, TaskBO.class));
		}
		log.debug("TaskBLImpl:getAll [END]");
		return tasks;
	}

	@Override
	public TaskBO add(TaskBO taskBO) {
		log.debug("TaskBLImpl:add [START]");
		Task taskEntity = mapperTask.map(taskBO, Task.class);

		Util.getDateUser(taskEntity, "INSERT");

		log.debug("TaskBLImpl:add [END]");
		return mapperTask.map(taskRepository.save(taskEntity), TaskBO.class);
	}

	@Override
	public TaskBO update(Long taskId, TaskBO taskBO) {
		Task taskEntity = taskRepository.getOne(taskId);
		if (taskEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(taskEntity, "UPDATE");
			
			return mapperTask.map(taskRepository.save(taskEntity), TaskBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long taskId) {
		Task taskEntity = taskRepository.getOne(taskId);
		if (taskEntity != null) {
		
			//TODO Baja logica o fisica?
		
			taskEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(taskEntity, "UPDATE");
			
			mapperTask.map(taskRepository.save(taskEntity), TaskBO.class);
			
			return true;
		}

		return false;
	}

}
