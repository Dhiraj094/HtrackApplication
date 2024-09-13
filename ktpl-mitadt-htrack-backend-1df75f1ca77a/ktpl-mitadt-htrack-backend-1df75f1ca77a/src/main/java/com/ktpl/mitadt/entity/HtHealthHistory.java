package com.ktpl.mitadt.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ht_health_history database table.
 * 
 */
@Entity
@Table(name="ht_health_history")
@NamedQuery(name="HtHealthHistory.findAll", query="SELECT h FROM HtHealthHistory h")
public class HtHealthHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int oxygen;

	private int pulse;

	@Temporal(TemporalType.TIMESTAMP)
	private Date record;

	//bi-directional many-to-one association to HtImage
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ht_image_id")
	private HtImage htImage;

	//bi-directional many-to-one association to HtUser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ht_user_id")
	private HtUser htUser;

	public HtHealthHistory() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOxygen() {
		return this.oxygen;
	}

	public void setOxygen(int oxygen) {
		this.oxygen = oxygen;
	}

	public int getPulse() {
		return this.pulse;
	}

	public void setPulse(int pulse) {
		this.pulse = pulse;
	}

	public Date getRecord() {
		return this.record;
	}

	public void setRecord(Date record) {
		this.record = record;
	}

	public HtImage getHtImage() {
		return this.htImage;
	}

	public void setHtImage(HtImage htImage) {
		this.htImage = htImage;
	}

	public HtUser getHtUser() {
		return this.htUser;
	}

	public void setHtUser(HtUser htUser) {
		this.htUser = htUser;
	}

}