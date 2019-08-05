package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.Department;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.DepartmentRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class DepartmentBLImpl implements IDepartmentBL {

	private DepartmentRepository departmentRepository;
	private MapperFacade mapperDepartment;

	@Autowired
	public DepartmentBLImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Department.class, DepartmentBO.class).byDefault().register();
		this.mapperDepartment = mapperFactory.getMapperFacade();

	}

	@Override
	public List<DepartmentBO> getAll() {
		log.debug("DepartmentBLImpl:getAll [START]");
		
		List<DepartmentBO> departments = new ArrayList<DepartmentBO>();

		List<Department> departmentEntities = departmentRepository.findAll();
		for (Department departmentEntity : departmentEntities) {
			departments.add(mapperDepartment.map(departmentEntity, DepartmentBO.class));
		}
		log.debug("DepartmentBLImpl:getAll [END]");
		return departments;
	}

	@Override
	public DepartmentBO add(DepartmentBO departmentBO) {
		log.debug("DepartmentBLImpl:add [START]");
		Department departmentEntity = mapperDepartment.map(departmentBO, Department.class);

		Util.getDateUser(departmentEntity, "INSERT");

		log.debug("DepartmentBLImpl:add [END]");
		return mapperDepartment.map(departmentRepository.save(departmentEntity), DepartmentBO.class);
	}

	@Override
	public DepartmentBO update(Long departmentId, DepartmentBO departmentBO) {
		Department departmentEntity = departmentRepository.getOne(departmentId);
		if (departmentEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(departmentEntity, "UPDATE");
			
			return mapperDepartment.map(departmentRepository.save(departmentEntity), DepartmentBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long departmentId) {
		Department departmentEntity = departmentRepository.getOne(departmentId);
		if (departmentEntity != null) {
		
			//TODO Baja logica o fisica?
		
			departmentEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(departmentEntity, "UPDATE");
			
			mapperDepartment.map(departmentRepository.save(departmentEntity), DepartmentBO.class);
			
			return true;
		}

		return false;
	}

}
