package com.mapfre.gaia.amap3.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the CELEBRATED_SENTENCES database table.
 * 
 */
@Entity
@Table(name="CELEBRATED_SENTENCES")
@NamedQuery(name="CelebratedSentence.findAll", query="SELECT c FROM CelebratedSentence c")
public class CelebratedSentence implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CELEBRATED_SENTENCES_IDSENTENCEPK_GENERATOR", sequenceName="CELEBRATED_SENTENCES_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CELEBRATED_SENTENCES_IDSENTENCEPK_GENERATOR")
	@Column(name="ID_SENTENCE_PK")
	private long idSentencePk;

	@Column(name="CD_SENTENCE")
	private String cdSentence;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_INSERT")
	private Date dateInsert;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_PUBLICATION")
	private Date datePublication;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_UPDATE")
	private Date dateUpdate;

	@Column(name="TXT_AUTOR")
	private String txtAutor;

	@Column(name="TXT_SENTENCE")
	private String txtSentence;

	@Column(name="USER_INSERT")
	private String userInsert;

	@Column(name="USER_UPDATE")
	private String userUpdate;

	public CelebratedSentence() {
	}

	public long getIdSentencePk() {
		return this.idSentencePk;
	}

	public void setIdSentencePk(long idSentencePk) {
		this.idSentencePk = idSentencePk;
	}

	public String getCdSentence() {
		return this.cdSentence;
	}

	public void setCdSentence(String cdSentence) {
		this.cdSentence = cdSentence;
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

	public String getTxtAutor() {
		return this.txtAutor;
	}

	public void setTxtAutor(String txtAutor) {
		this.txtAutor = txtAutor;
	}

	public String getTxtSentence() {
		return this.txtSentence;
	}

	public void setTxtSentence(String txtSentence) {
		this.txtSentence = txtSentence;
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