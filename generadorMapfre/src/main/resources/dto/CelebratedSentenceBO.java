package com.mapfre.gaia.amap3;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * The bussiness class for the CelebratedSentence database table.
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CelebratedSentenceBO implements Serializable {

	private static final long serialVersionUID;
	private long idSentencePk;
	private java.lang.String cdSentence;
	private java.util.Date dateInsert;
	private java.util.Date datePublication;
	private java.util.Date dateUpdate;
	private java.lang.String txtAutor;
	private java.lang.String txtSentence;
	private java.lang.String userInsert;
	private java.lang.String userUpdate;

}