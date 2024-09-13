package com.ktpl.mitadt.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the ht_user database table.
 * 
 */
@Entity
@Table(name = "ht_user")
@NamedQuery(name = "HtUser.findAll", query = "SELECT h FROM HtUser h")
public class HtUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date birhdate;

	@Column(name = "blood_group")
	private String bloodGroup;

	private String gender;

	private String login;

	private String name;

	private String password;

	// bi-directional many-to-one association to HtHealthHistory
	@OneToMany(mappedBy = "htUser")
	private List<HtHealthHistory> htHealthHistories;

	public HtUser() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBirhdate() {
		return this.birhdate;
	}

	public void setBirhdate(Date birhdate) {
		this.birhdate = birhdate;
	}

	public String getBloodGroup() {
		return this.bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<HtHealthHistory> getHtHealthHistories() {
		return this.htHealthHistories;
	}

	public void setHtHealthHistories(List<HtHealthHistory> htHealthHistories) {
		this.htHealthHistories = htHealthHistories;
	}

	public HtHealthHistory addHtHealthHistory(HtHealthHistory htHealthHistory) {
		getHtHealthHistories().add(htHealthHistory);
		htHealthHistory.setHtUser(this);

		return htHealthHistory;
	}

	public HtHealthHistory removeHtHealthHistory(HtHealthHistory htHealthHistory) {
		getHtHealthHistories().remove(htHealthHistory);
		htHealthHistory.setHtUser(null);

		return htHealthHistory;
	}

}