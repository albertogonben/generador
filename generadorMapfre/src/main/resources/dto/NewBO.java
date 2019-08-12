package com.mapfre.gaia.amap3;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * The bussiness class for the New database table.
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewBO implements Serializable {

	private static final long serialVersionUID;
	private long idNewsPk;
	private java.lang.String cdNews;
	private java.util.Date dateInsert;
	private java.util.Date datePublication;
	private java.util.Date dateUpdate;
	private byte[] fileDocument;
	private java.math.BigDecimal isPrincipal;
	private java.lang.String txtName;
	private java.lang.String txtNews;
	private java.lang.String txtUrl;
	private java.lang.String typeDocument;
	private java.lang.String userInsert;
	private java.lang.String userUpdate;

}