package com.mapfre.gaia.amap3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.TypeCourse;
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.TypeCourseRepository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class TypeCourseBLImpl implements ITypeCourseBL {

	private TypeCourseRepository typeCourseRepository;
	private MapperFacade mapperTypeCourse;

	@Autowired
	public TypeCourseBLImpl(TypeCourseRepository typeCourseRepository) {
		this.typeCourseRepository = typeCourseRepository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(TypeCourse.class, TypeCourseBO.class).byDefault().register();
		this.mapperTypeCourse = mapperFactory.getMapperFacade();

	}

	@Override
	public List<TypeCourseBO> getAll() {
		log.debug("TypeCourseBLImpl:getAll [START]");
		
		List<TypeCourseBO> typeCourses = new ArrayList<TypeCourseBO>();

		List<TypeCourse> typeCourseEntities = typeCourseRepository.findAll();
		for (TypeCourse typeCourseEntity : typeCourseEntities) {
			typeCourses.add(mapperTypeCourse.map(typeCourseEntity, TypeCourseBO.class));
		}
		log.debug("TypeCourseBLImpl:getAll [END]");
		return typeCourses;
	}

	@Override
	public TypeCourseBO add(TypeCourseBO typeCourseBO) {
		log.debug("TypeCourseBLImpl:add [START]");
		TypeCourse typeCourseEntity = mapperTypeCourse.map(typeCourseBO, TypeCourse.class);

		Util.getDateUser(typeCourseEntity, "INSERT");

		log.debug("TypeCourseBLImpl:add [END]");
		return mapperTypeCourse.map(typeCourseRepository.save(typeCourseEntity), TypeCourseBO.class);
	}

	@Override
	public TypeCourseBO update(Long typeCourseId, TypeCourseBO typeCourseBO) {
		TypeCourse typeCourseEntity = typeCourseRepository.getOne(typeCourseId);
		if (typeCourseEntity != null) {
			
			// TODO Setear campos

			Util.getDateUser(typeCourseEntity, "UPDATE");
			
			return mapperTypeCourse.map(typeCourseRepository.save(typeCourseEntity), TypeCourseBO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long typeCourseId) {
		TypeCourse typeCourseEntity = typeCourseRepository.getOne(typeCourseId);
		if (typeCourseEntity != null) {
		
			//TODO Baja logica o fisica?
		
			typeCourseEntity.setMrkActive(new BigDecimal(0));
			Util.getDateUser(typeCourseEntity, "UPDATE");
			
			mapperTypeCourse.map(typeCourseRepository.save(typeCourseEntity), TypeCourseBO.class);
			
			return true;
		}

		return false;
	}

}
