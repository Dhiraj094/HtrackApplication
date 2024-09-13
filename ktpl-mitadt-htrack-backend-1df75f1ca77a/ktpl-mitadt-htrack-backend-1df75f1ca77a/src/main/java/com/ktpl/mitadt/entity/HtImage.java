package com.ktpl.mitadt.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ht_image database table.
 * 
 */
@Entity
@Table(name="ht_image")
@NamedQuery(name="HtImage.findAll", query="SELECT h FROM HtImage h")
public class HtImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Lob
	private byte[] image;

	//bi-directional many-to-one association to HtHealthHistory
	@OneToMany(mappedBy="htImage")
	private List<HtHealthHistory> htHealthHistories;

	public HtImage() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public List<HtHealthHistory> getHtHealthHistories() {
		return this.htHealthHistories;
	}

	public void setHtHealthHistories(List<HtHealthHistory> htHealthHistories) {
		this.htHealthHistories = htHealthHistories;
	}

	public HtHealthHistory addHtHealthHistory(HtHealthHistory htHealthHistory) {
		getHtHealthHistories().add(htHealthHistory);
		htHealthHistory.setHtImage(this);

		return htHealthHistory;
	}

	public HtHealthHistory removeHtHealthHistory(HtHealthHistory htHealthHistory) {
		getHtHealthHistories().remove(htHealthHistory);
		htHealthHistory.setHtImage(null);

		return htHealthHistory;
	}

}