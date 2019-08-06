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
public class CelebratedSentenceController implements ICelebratedSentenceController{

	private ICelebratedSentenceBL celebratedSentenceBL;
	
	@Autowired
	public CelebratedSentenceController(ICelebratedSentenceBL celebratedSentenceBL) {
		this.celebratedSentenceBL = celebratedSentenceBL;
	}
	
	@Override
	public ResponseEntity<List<CelebratedSentenceBO>> get() throws CustomException{
		log.debug("CelebratedSentenceController:get [START]");
		try {
			log.debug("CelebratedSentenceController:get [END]");
			return ResponseEntity.ok().body(celebratedSentenceBL.getAll());
		} catch (Exception e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		}
	}

    @Override
    public ResponseEntity<CelebratedSentenceBO> add(@Valid @RequestBody CelebratedSentenceBO input) throws CustomException{
    	log.debug("CelebratedSentenceController:add [START]");
    	try {
    		if(ValidationDate.validationStringDate(input.getDateStart()) == false 
    				|| ValidationDate.validationStringDate(input.getDateFinish()) == false) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error parseo de fechas de entrada");
    		}
    	
			CelebratedSentenceBO celebratedSentenceBo = celebratedSentenceBL.add(input);
			if (celebratedSentenceBo != null) {
				log.debug("CelebratedSentenceController:add [END]");
				return ResponseEntity.ok().build();
			}
			throw new CustomException(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase());
		} catch (Exception e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		}
    }

    @Override
    public ResponseEntity<CelebratedSentenceBO> update(@PathVariable Long celebratedSentenceId, @RequestBody CelebratedSentenceBO input) throws CustomException{
    	log.debug("CelebratedSentenceController:update [START]");
    	try {
			CelebratedSentenceBO celebratedSentenceBo = celebratedSentenceBL.update(celebratedSentenceId, input);
			if (celebratedSentenceBo != null) {
				log.debug("CelebratedSentenceController:update [END]");
			    return ResponseEntity.ok().body(celebratedSentenceBo);
			}
			throw new CustomException(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.getReasonPhrase());
		} catch (Exception e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
    }

    @Override
    public ResponseEntity<CelebratedSentenceBO> delete(@PathVariable Long celebratedSentenceId) throws CustomException{
        log.debug("CelebratedSentenceController:delete [START]");
        try {
			boolean celebratedSentenceDeleted = celebratedSentenceBL.delete(celebratedSentenceId);
			if (celebratedSentenceDeleted) {
				log.debug("CelebratedSentenceController:delete [END]");
			    return ResponseEntity.ok().build();
			}
			throw new CustomException(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		} catch (Exception e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
    }

}
