package com.ktpl.mitadt.dto;

import java.util.Date;

import com.ktpl.mitadt.entity.HtHealthHistory;

public class HealthHistoryDto {

	private int id;
	private int oxygen;
	private int pulse;
	private Date record;
	private ImageDto imageDto;
	private UserDto userDto;

	public HealthHistoryDto(int id, int oxygen, int pulse, Date record, ImageDto imageDto, UserDto userDto) {
		super();
		this.id = id;
		this.oxygen = oxygen;
		this.pulse = pulse;
		this.record = record;
		this.imageDto = imageDto;
		this.userDto = userDto;
	}

	public HealthHistoryDto() {

	}

	public static HealthHistoryDto getDto(HtHealthHistory entity) {
		HealthHistoryDto dto = new HealthHistoryDto();
		dto.setId(entity.getId());
		dto.setOxygen(entity.getOxygen());
		dto.setPulse(entity.getPulse());
		dto.setRecord(entity.getRecord());
		// dto.setImageDto(e.getHtUser());
		// dto.setUserDto(e.getHtUser());
		return dto;
	}

	public static HtHealthHistory getEntity(HealthHistoryDto dto) {
		HtHealthHistory entity = new HtHealthHistory();
		return copyDtoToEntity(entity, dto);
	}

	public static HtHealthHistory copyDtoToEntity(HtHealthHistory entity, HealthHistoryDto dto) {
		entity.setOxygen(dto.getOxygen());
		entity.setPulse(dto.getPulse());
		entity.setRecord(new Date());

		// entity.setImageDto(dto.getHtUser());

		// entity.setUserDto(dto.getUserDto().getId()));

		return entity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOxygen() {
		return oxygen;
	}

	public void setOxygen(int oxygen) {
		this.oxygen = oxygen;
	}

	public int getPulse() {
		return pulse;
	}

	public void setPulse(int pulse) {
		this.pulse = pulse;
	}

	public Date getRecord() {
		return record;
	}

	public void setRecord(Date record) {
		this.record = record;
	}

	public ImageDto getImageDto() {
		return imageDto;
	}

	public void setImageDto(ImageDto imageDto) {
		this.imageDto = imageDto;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

}
