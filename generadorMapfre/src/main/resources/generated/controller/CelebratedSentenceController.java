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
public class CelebratedSentenceController implements ICelebratedSentenceController{

	private ICelebratedSentenceBL celebratedSentenceBL;
	
	@Autowired
	public CelebratedSentenceController(ICelebratedSentenceBL celebratedSentenceBL) {
		this.celebratedSentenceBL = celebratedSentenceBL;
	}
	
	@Override
	public ResponseEntity<List<CelebratedSentenceBO>> get() {
		try {
			return ResponseEntity.ok().body(celebratedSentenceBL.getAll());
		} catch (Exception e) {
			log.error("CelebratedSentenceController:get", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

    @Override
    public ResponseEntity<CelebratedSentenceBO> add(@Valid @RequestBody CelebratedSentenceBO input) {
    	try {
			CelebratedSentenceBO celebratedSentenceBo = closePeriodBL.add(input);
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
