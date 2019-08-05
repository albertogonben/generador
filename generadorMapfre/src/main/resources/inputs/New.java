package com.mapfre.gaia.amap3.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the NEWS database table.
 * 
 */
@Entity
@Table(name="NEWS")
@NamedQuery(name="New.findAll", query="SELECT n FROM New n")
public class New implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="NEWS_IDNEWSPK_GENERATOR", sequenceName="NEWS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NEWS_IDNEWSPK_GENERATOR")
	@Column(name="ID_NEWS_PK")
	private long idNewsPk;

	@Column(name="CD_NEWS")
	private String cdNews;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_INSERT")
	private Date dateInsert;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_PUBLICATION")
	private Date datePublication;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_UPDATE")
	private Date dateUpdate;

	@Lob
	@Column(name="FILE_DOCUMENT")
	private byte[] fileDocument;

	@Column(name="IS_PRINCIPAL")
	private BigDecimal isPrincipal;

	@Column(name="TXT_NAME")
	private String txtName;

	@Column(name="TXT_NEWS")
	private String txtNews;

	@Column(name="TXT_URL")
	private String txtUrl;

	@Column(name="TYPE_DOCUMENT")
	private String typeDocument;

	@Column(name="USER_INSERT")
	private String userInsert;

	@Column(name="USER_UPDATE")
	private String userUpdate;

	public New() {
	}

	public long getIdNewsPk() {
		return this.idNewsPk;
	}

	public void setIdNewsPk(long idNewsPk) {
		this.idNewsPk = idNewsPk;
	}

	public String getCdNews() {
		return this.cdNews;
	}

	public void setCdNews(String cdNews) {
		this.cdNews = cdNews;
	}

	public Date getDateInsert() {
		return this.dateInsert;
	}

	public void setDateInsert(Date dateInsert) {
		this.dateInsert = dateInsert;
	}

	public Date getDatePublication() {
		return this.datePublication;
	}

	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}

	public Date getDateUpdate() {
		return this.dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public byte[] getFileDocument() {
		return this.fileDocument;
	}

	public void setFileDocument(byte[] fileDocument) {
		this.fileDocument = fileDocument;
	}

	public BigDecimal getIsPrincipal() {
		return this.isPrincipal;
	}

	public void setIsPrincipal(BigDecimal isPrincipal) {
		this.isPrincipal = isPrincipal;
	}

	public String getTxtName() {
		return this.txtName;
	}

	public void setTxtName(String txtName) {
		this.txtName = txtName;
	}

	public String getTxtNews() {
		return this.txtNews;
	}

	public void setTxtNews(String txtNews) {
		this.txtNews = txtNews;
	}

	public String getTxtUrl() {
		return this.txtUrl;
	}

	public void setTxtUrl(String txtUrl) {
		this.txtUrl = txtUrl;
	}

	public String getTypeDocument() {
		return this.typeDocument;
	}

	public void setTypeDocument(String typeDocument) {
		this.typeDocument = typeDocument;
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

}