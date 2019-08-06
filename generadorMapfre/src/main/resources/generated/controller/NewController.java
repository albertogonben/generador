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
public class NewController implements INewController{

	private INewBL newBL;
	
	@Autowired
	public NewController(INewBL newBL) {
		this.newBL = newBL;
	}
	
	@Override
	public ResponseEntity<List<NewBO>> get() throws CustomException{
		log.debug("NewController:get [START]");
		try {
			log.debug("NewController:get [END]");
			return ResponseEntity.ok().body(newBL.getAll());
		} catch (Exception e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		}
	}

    @Override
    public ResponseEntity<NewBO> add(@Valid @RequestBody NewBO input) throws CustomException{
    	log.debug("NewController:add [START]");
    	try {
    		if(ValidationDate.validationStringDate(input.getDateStart()) == false 
    				|| ValidationDate.validationStringDate(input.getDateFinish()) == false) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error parseo de fechas de entrada");
    		}
    	
			NewBO newBo = newBL.add(input);
			if (newBo != null) {
				log.debug("NewController:add [END]");
				return ResponseEntity.ok().build();
			}
			throw new CustomException(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase());
		} catch (Exception e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		}
    }

    @Override
    public ResponseEntity<NewBO> update(@PathVariable Long newId, @RequestBody NewBO input) throws CustomException{
    	log.debug("NewController:update [START]");
    	try {
			NewBO newBo = newBL.update(newId, input);
			if (newBo != null) {
				log.debug("NewController:update [END]");
			    return ResponseEntity.ok().body(newBo);
			}
			throw new CustomException(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.getReasonPhrase());
		} catch (Exception e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
    }

    @Override
    public ResponseEntity<NewBO> delete(@PathVariable Long newId) throws CustomException{
        log.debug("NewController:delete [START]");
        try {
			boolean newDeleted = newBL.delete(newId);
			if (newDeleted) {
				log.debug("NewController:delete [END]");
			    return ResponseEntity.ok().build();
			}
			throw new CustomException(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		} catch (Exception e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
    }

}
