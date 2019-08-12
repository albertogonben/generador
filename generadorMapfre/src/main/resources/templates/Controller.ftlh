package ${package};

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mapfre.gaia.amap3.exception.CustomException;
import com.mapfre.gaia.amap3.validations.Validation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ${name}Controller implements I${name}Controller{

	private I${name}BL ${nameMIN}BL;
	
	@Autowired
	public ${name}Controller(I${name}BL ${nameMIN}BL) {
		this.${nameMIN}BL = ${nameMIN}BL;
	}
	
	@Override
	public ResponseEntity<List<${name}BO>> get() throws CustomException{
		log.debug("${name}Controller:get [START]");
		try {
			log.debug("${name}Controller:get [END]");
			return ResponseEntity.ok().body(${nameMIN}BL.getAll());
		} catch (Exception e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
	}

    @Override
    public ResponseEntity<${name}BO> add(@Valid @RequestBody ${name}BO input) throws CustomException{
    	log.debug("${name}Controller:add [START]");
    	try {
    		if(!Validation.validar(input)) {
    			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    		}
    	
			${name}BO ${nameMIN}Bo = ${nameMIN}BL.add(input);
			if (${nameMIN}Bo != null) {
				log.debug("${name}Controller:add [END]");
				return ResponseEntity.ok().build();
			}
			throw new CustomException(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase());
		} catch (Exception e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		}
    }

    @Override
    public ResponseEntity<${name}BO> update(@PathVariable Long ${nameMIN}Id, @RequestBody ${name}BO input) throws CustomException{
    	log.debug("${name}Controller:update [START]");
    	try {
    		if(!Validation.validar(input)) {
    			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    		}
			${name}BO ${nameMIN}Bo = ${nameMIN}BL.update(${nameMIN}Id, input);
			if (${nameMIN}Bo != null) {
				log.debug("${name}Controller:update [END]");
			    return ResponseEntity.ok().body(${nameMIN}Bo);
			}
			throw new CustomException(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.getReasonPhrase());
		} catch (Exception e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
    }

    @Override
    public ResponseEntity<${name}BO> delete(@PathVariable Long ${nameMIN}Id) throws CustomException{
        log.debug("${name}Controller:delete [START]");
        try {
			boolean ${nameMIN}Deleted = ${nameMIN}BL.delete(${nameMIN}Id);
			if (${nameMIN}Deleted) {
				log.debug("${name}Controller:delete [END]");
			    return ResponseEntity.ok().build();
			}
			throw new CustomException(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		} catch (Exception e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
    }

}
