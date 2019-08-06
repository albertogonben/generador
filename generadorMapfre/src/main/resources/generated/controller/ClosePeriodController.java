package com.mapfre.gaia.amap3;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mapfre.gaia.amap3.exception.CustomException;
import com.mapfre.gaia.amap3.validations.ValidationDate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ClosePeriodController implements IClosePeriodController{

	private IClosePeriodBL closePeriodBL;
	
	@Autowired
	public ClosePeriodController(IClosePeriodBL closePeriodBL) {
		this.closePeriodBL = closePeriodBL;
	}
	
	@Override
	public ResponseEntity<List<ClosePeriodBO>> get() throws CustomException{
		log.debug("ClosePeriodController:get [START]");
		try {
			log.debug("ClosePeriodController:get [END]");
			return ResponseEntity.ok().body(closePeriodBL.getAll());
		} catch (Exception e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		}
	}

    @Override
    public ResponseEntity<ClosePeriodBO> add(@Valid @RequestBody ClosePeriodBO input) throws CustomException{
    	log.debug("ClosePeriodController:add [START]");
    	try {
    		if(ValidationDate.validationStringDate(input.getDateStart()) == false 
    				|| ValidationDate.validationStringDate(input.getDateFinish()) == false) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error parseo de fechas de entrada");
    		}
    	
			ClosePeriodBO closePeriodBo = closePeriodBL.add(input);
			if (closePeriodBo != null) {
				log.debug("ClosePeriodController:add [END]");
				return ResponseEntity.ok().build();
			}
			throw new CustomException(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase());
		} catch (Exception e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		}
    }

    @Override
    public ResponseEntity<ClosePeriodBO> update(@PathVariable Long closePeriodId, @RequestBody ClosePeriodBO input) throws CustomException{
    	log.debug("ClosePeriodController:update [START]");
    	try {
			ClosePeriodBO closePeriodBo = closePeriodBL.update(closePeriodId, input);
			if (closePeriodBo != null) {
				log.debug("ClosePeriodController:update [END]");
			    return ResponseEntity.ok().body(closePeriodBo);
			}
			throw new CustomException(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.getReasonPhrase());
		} catch (Exception e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
    }

    @Override
    public ResponseEntity<ClosePeriodBO> delete(@PathVariable Long closePeriodId) throws CustomException{
        log.debug("ClosePeriodController:delete [START]");
        try {
			boolean closePeriodDeleted = closePeriodBL.delete(closePeriodId);
			if (closePeriodDeleted) {
				log.debug("ClosePeriodController:delete [END]");
			    return ResponseEntity.ok().build();
			}
			throw new CustomException(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		} catch (Exception e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
    }

}
