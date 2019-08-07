package com.mapfre.gaia.amap3.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CLOSE_PERIOD database table.
 * 
 */
@Entity
@Table(name="CLOSE_PERIOD")
@NamedQuery(name="ClosePeriod.findAll", query="SELECT c FROM ClosePeriod c")
public class ClosePeriod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLOSE_PERIOD_IDCLOSEPERIODPK_GENERATOR", sequenceName="CLOSE_PERIOD_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLOSE_PERIOD_IDCLOSEPERIODPK_GENERATOR")
	@Column(name="ID_CLOSE_PERIOD_PK")
	private long idClosePeriodPk;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_FINISH")
	private Date dateFinish;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_INSERT")
	private Date dateInsert;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_START")
	private Date dateStart;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_UPDATE")
	private Date dateUpdate;

	@Column(name="MRK_ACTIVE")
	private BigDecimal mrkActive;

	@Column(name="USER_INSERT")
	private String userInsert;

	@Column(name="USER_UPDATE")
	private String userUpdate;

	//uni-directional many-to-one association to TypeClosePeriod
	@ManyToOne
	@JoinColumn(name="ID_TYPE_CLOSE_PERIOD_FK")
	private New typeClosePeriod;

	public ClosePeriod() {
	}

	public long getIdClosePeriodPk() {
		return this.idClosePeriodPk;
	}

	public void setIdClosePeriodPk(long idClosePeriodPk) {
		this.idClosePeriodPk = idClosePeriodPk;
	}

	public Date getDateFinish() {
		return this.dateFinish;
	}

	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}

	public Date getDateInsert() {
		return this.dateInsert;
	}

	public void setDateInsert(Date dateInsert) {
		this.dateInsert = dateInsert;
	}

	public Date getDateStart() {
		return this.dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateUpdate() {
		return this.dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public BigDecimal getMrkActive() {
		return this.mrkActive;
	}

	public void setMrkActive(BigDecimal mrkActive) {
		this.mrkActive = mrkActive;
	}

	public String getUserInsert() {
		return this.userInsert;
	}

	public void setUserInsert(String userInsert) {
		this.userInsert = userInsert;
	}

	public String getUserUpdate() {
		return this.userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	public New getTypeClosePeriod() {
		return this.typeClosePeriod;
	}

	public void setTypeClosePeriod(New typeClosePeriod) {
		this.typeClosePeriod = typeClosePeriod;
	}

}