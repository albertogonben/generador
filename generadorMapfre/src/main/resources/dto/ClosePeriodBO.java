package com.mapfre.gaia.amap3;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * The bussiness class for the ClosePeriod database table.
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClosePeriodBO implements Serializable {

	private static final long serialVersionUID;
	private long idClosePeriodPk;
	private java.util.Date dateFinish;
	private java.util.Date dateInsert;
	private java.util.Date dateStart;
	private java.util.Date dateUpdate;
	private java.math.BigDecimal mrkActive;
	private java.lang.String userInsert;
	private java.lang.String userUpdate;
	private com.mapfre.gaia.amap3.entities.New typeClosePeriod;

}