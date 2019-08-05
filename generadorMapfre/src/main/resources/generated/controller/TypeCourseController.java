package com.mapfre.gaia.amap3;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TypeCourseController implements ITypeCourseController{

	private ITypeCourseBL typeCourseBL;
	
	@Autowired
	public TypeCourseController(ITypeCourseBL typeCourseBL) {
		this.typeCourseBL = typeCourseBL;
	}
	
	@Override
	public ResponseEntity<List<TypeCourseBO>> get() {
		try {
			return ResponseEntity.ok().body(typeCourseBL.getAll());
		} catch (Exception e) {
			log.error("TypeCourseController:get", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

    @Override
    public ResponseEntity<TypeCourseBO> add(@Valid @RequestBody TypeCourseBO input) {
    	try {
			TypeCourseBO typeCourseBo = closePeriodBL.add(input);
			if (closePeriodBo != null) {
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (Exception e) {
			log.error("ClosePeriodController:add", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    }

    @Override
    public ResponseEntity<ClosePeriodBO> update(@PathVariable Long closePeriodId, @RequestBody ClosePeriodBO input) {
    	try {
			ClosePeriodBO closePeriodBo = closePeriodBL.update(closePeriodId, input);
			if (closePeriodBo != null) {
			    return ResponseEntity.ok().body(closePeriodBo);
			}
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			log.error("ClosePeriodController:update", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    }

    @Override
    public ResponseEntity<ClosePeriodBO> delete(@PathVariable Long closePeriodId) {
        try {
			boolean claseoPeriodDeleted = closePeriodBL.delete(closePeriodId);
			if (claseoPeriodDeleted) {
			    return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("ClosePeriodController:delete", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    }

}
