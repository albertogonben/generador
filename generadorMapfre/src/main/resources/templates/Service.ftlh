package ${package};

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapfre.gaia.amap3.entities.${name};
import com.mapfre.gaia.amap3.mapper.StringDateMapper;
import com.mapfre.gaia.amap3.repositories.${name}Repository;
import com.mapfre.gaia.amap3.utils.Util;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
@Service
@Transactional
public class ${name}BLImpl implements I${name}BL {

	private ${name}Repository ${nameMIN}Repository;
	private MapperFacade mapper${name};

	@Autowired
	public ${name}BLImpl(${name}Repository ${nameMIN}Repository) {
		this.${nameMIN}Repository = ${nameMIN}Repository;

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(${name}.class, ${name}BO.class).byDefault().register();
		this.mapper${name} = mapperFactory.getMapperFacade();

	}

	@Override
	public List<${name}BO> getAll() {
		log.debug("${name}BLImpl:getAll [START]");
		
		List<${name}BO> ${nameMIN}s = new ArrayList<${name}BO>();

		List<${name}> ${nameMIN}Entities = ${nameMIN}Repository.findAll();
		for (${name} ${nameMIN}Entity : ${nameMIN}Entities) {
			${nameMIN}s.add(mapper${name}.map(${nameMIN}Entity, ${name}BO.class));
		}
		log.debug("${name}BLImpl:getAll [END]");
		return ${nameMIN}s;
	}

	@Override
	public ${name}BO add(${name}BO ${nameMIN}BO) {
		log.debug("${name}BLImpl:add [START]");
		${name} ${nameMIN}Entity = mapper${name}.map(${nameMIN}BO, ${name}.class);

		Util.getDateUser(${nameMIN}Entity, "INSERT");

		log.debug("${name}BLImpl:add [END]");
		return mapper${name}.map(${nameMIN}Repository.save(${nameMIN}Entity), ${name}BO.class);
	}

	@Override
	public ${name}BO update(Long ${nameMIN}Id, ${name}BO ${nameMIN}BO) {
		log.debug("${name}BLImpl:update [START]");
		${name} ${nameMIN}Entity = ${nameMIN}Repository.getOne(${nameMIN}Id);
		if (${nameMIN}Entity != null) {
			
			// TODO Setear campos

			

			Util.getDateUser(${nameMIN}Entity, "UPDATE");
			
			log.debug("${name}BLImpl:update [START]");
			return mapper${name}.map(${nameMIN}Repository.save(${nameMIN}Entity), ${name}BO.class);
		}

		return null;
	}

	@Override
	public boolean delete(Long ${nameMIN}Id) {
		log.debug("${name}BLImpl:delete [START]");
		${name} ${nameMIN}Entity = ${nameMIN}Repository.getOne(${nameMIN}Id);
		if (${nameMIN}Entity != null) {
		
			//TODO Baja logica o fisica?
			//${nameMIN}Entity.setMrkActive(new BigDecimal(0));
			
			Util.getDateUser(${nameMIN}Entity, "UPDATE");
			
			mapper${name}.map(${nameMIN}Repository.save(${nameMIN}Entity), ${name}BO.class);
			
			log.debug("${name}BLImpl:delete [END]");
			return true;
		}

		return false;
	}

}
