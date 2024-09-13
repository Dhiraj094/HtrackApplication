package com.ktpl.mitadt.dto;

import java.util.Date;

import com.ktpl.mitadt.entity.HtUser;

public class UserDto {

	private int id;
	private Date birhdate;
	private String bloodGroup;
	private String gender;
	private String name;
	private String login;
	private String password;

	public UserDto() {

	}

	public static UserDto getDto(HtUser entity) {
		UserDto dto = new UserDto();
		dto.setId(entity.getId());
		dto.setBirhdate(entity.getBirhdate());
		dto.setGender(entity.getGender());
		dto.setName(entity.getName());
		dto.setBloodGroup(entity.getBloodGroup());
		dto.setLogin(entity.getLogin());
		return dto;
	}

	public static HtUser getEntity(UserDto dto) {
		HtUser entity = new HtUser();
		return copyDtoToEntity(entity, dto);
	}

	public static HtUser copyDtoToEntity(HtUser entity, UserDto dto) {
		entity.setBirhdate(dto.getBirhdate());
		entity.setGender(dto.getGender());
		entity.setName(dto.getName());
		entity.setBloodGroup(dto.getBloodGroup());
		if(dto.getLogin() != null) {
			entity.setLogin(dto.getLogin());
		}
		if(dto.getPassword() != null) {
			entity.setPassword(dto.getPassword());
		}
		return entity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBirhdate() {
		return birhdate;
	}

	public void setBirhdate(Date birhdate) {
		this.birhdate = birhdate;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
