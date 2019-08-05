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
public class UnitBusinessController implements IUnitBusinessController{

	private IUnitBusinessBL unitBusinessBL;
	
	@Autowired
	public UnitBusinessController(IUnitBusinessBL unitBusinessBL) {
		this.unitBusinessBL = unitBusinessBL;
	}
	
	@Override
	public ResponseEntity<List<UnitBusinessBO>> get() {
		try {
			return ResponseEntity.ok().body(unitBusinessBL.getAll());
		} catch (Exception e) {
			log.error("UnitBusinessController:get", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

    @Override
    public ResponseEntity<UnitBusinessBO> add(@Valid @RequestBody UnitBusinessBO input) {
    	try {
			UnitBusinessBO unitBusinessBo = closePeriodBL.add(input);
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
