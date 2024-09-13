package com.ktpl.mitadt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ktpl.mitadt.dto.HealthHistoryDto;
import com.ktpl.mitadt.dto.UserDto;
import com.ktpl.mitadt.entity.HtHealthHistory;
import com.ktpl.mitadt.entity.HtUser;
import com.ktpl.mitadt.exceptions.HealthHistoryNotFoundException;
import com.ktpl.mitadt.exceptions.UserNotFoundException;
import com.ktpl.mitadt.repository.HealthHistoryRepository;
import com.ktpl.mitadt.repository.UserRepository;

@Service
@Transactional

public class HealthHistoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(HealthHistoryService.class);

	@Autowired
	private HealthHistoryRepository healthHistoryRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Get requested Health History
	 * 
	 * @param id Health history ID
	 * @return DTO of Health History
	 */
	public HealthHistoryDto getHealthHistory(Integer id) {
		LOGGER.info("getHealthHistory().. Start, User Id : " + id);
		HtHealthHistory entity = healthHistoryRepository.getOne(id);
		HealthHistoryDto dto = HealthHistoryDto.getDto(entity);
		LOGGER.info("getHealthHistory().. End");
		return dto;
	}

	/**
	 * Add new Health History
	 * 
	 * @param dto
	 * @return
	 */
	public HealthHistoryDto addHealthHistory(HealthHistoryDto dto) {
		LOGGER.info("addHealthHistory().. Start, HealthHistory Details : " + dto.toString());

		HtHealthHistory entity = healthHistoryRepository.save(HealthHistoryDto.getEntity(dto));

		LOGGER.info("addHealthHistory().. End");
		return HealthHistoryDto.getDto(entity);

	}

	/**
	 * Method to update Health History
	 * 
	 * @param dto Health History DTO
	 * @return Updated Health History DTO Object
	 * @throws HealthHistoryNotFoundException
	 */
	public HealthHistoryDto updateHealthHistory(HealthHistoryDto dto, Integer id)
			throws HealthHistoryNotFoundException {

		if (healthHistoryRepository.existsById(id)) {
			Optional<HtHealthHistory> optEntityList = healthHistoryRepository.findById(id);
			HtHealthHistory entity = optEntityList.get();
			entity = healthHistoryRepository.save(HealthHistoryDto.copyDtoToEntity(entity, dto));
			return HealthHistoryDto.getDto(entity);

		} else {
			throw new HealthHistoryNotFoundException("HEALTHHISTORY_NOT_AVAILABLE", "Health History is not available",
					HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Method to add Health History
	 * 
	 * @param dto Health History DTO
	 * 
	 * @return Updated Health History DTO Object
	 * @throws UserNotFoundException
	 */
	public HealthHistoryDto addOrUpdateHealthHistory(HealthHistoryDto dto) throws UserNotFoundException {

		HtHealthHistory entity = null;
		UserDto userDto = dto.getUserDto();

		// Check if User Exist
		if (userRepository.existsById(userDto.getId())) {

			if (healthHistoryRepository.existsById(dto.getId())) {
				entity = healthHistoryRepository.getOne(dto.getId());
			} else {

				// Get User
				HtUser userEntity = userRepository.getOne(userDto.getId());
				HtHealthHistory healthHistoryEntity = HealthHistoryDto.getEntity(dto);

				healthHistoryEntity.setHtUser(userEntity);
				entity = healthHistoryRepository.save(healthHistoryEntity);
			}
			return HealthHistoryDto.getDto(entity);
		} else {
			throw new UserNotFoundException("USER_NOT_AVAILABLE", "User is not available", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Delete Health History and its related entries.
	 * 
	 * @param id Health History ID
	 * @throws HealthHistoryNotFoundException
	 */
	public void deleteById(Integer id) throws HealthHistoryNotFoundException {

		if (healthHistoryRepository.existsById(id)) {
			healthHistoryRepository.deleteById(id);
		} else {
			throw new HealthHistoryNotFoundException("HEALTH_HISTORY_NOT_AVAILABLE", "Health History is not available",
					HttpStatus.NOT_ACCEPTABLE);
		}

	}

	/**
	 * Get requested Health History List for given User ID
	 * 
	 * @param id Health history ID
	 * @return DTO of Health History
	 * @throws UserNotFoundException
	 */
	public List<HealthHistoryDto> getHealthHistoryListForUser(Integer userId) throws UserNotFoundException {
		LOGGER.info("getHealthHistoryListForUser().. Start, User Id : " + userId);

		if (userRepository.existsById(userId)) {
			// HtUser userEntity = userRepository.getOne(userId);
			List<HtHealthHistory> entityList = healthHistoryRepository.getHealthHistoryForUser(userId);
			List<HealthHistoryDto> dtoList = new ArrayList<HealthHistoryDto>();
			for (HtHealthHistory entity : entityList) {
				dtoList.add(HealthHistoryDto.getDto(entity));
			}
			LOGGER.info("getHealthHistory().. End");
			return dtoList;
		} else {
			throw new UserNotFoundException("USER_NOT_AVAILABLE", "User is not available", HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
