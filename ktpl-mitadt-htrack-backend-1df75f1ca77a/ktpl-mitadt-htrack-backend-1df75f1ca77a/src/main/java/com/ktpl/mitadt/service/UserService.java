package com.ktpl.mitadt.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ktpl.mitadt.dto.UserDto;
import com.ktpl.mitadt.entity.HtUser;
import com.ktpl.mitadt.exceptions.ApplicationGenericException;
import com.ktpl.mitadt.exceptions.UserNotFoundException;
import com.ktpl.mitadt.repository.UserRepository;

@Service
@Transactional

public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	/**
	 * Return requested User
	 * 
	 * @param searchDto : User ID
	 * @return User DTO
	 * @throws UserNotFoundException
	 */
	public UserDto getUser(Integer id) throws UserNotFoundException {
		LOGGER.info("getUser().. Start, User Id : " + id);

		if (userRepository.existsById(id)) {
			HtUser entity = userRepository.getOne(id);
			UserDto dto = UserDto.getDto(entity);
			LOGGER.info("getUser().. End");
			return dto;
		} else {
			throw new UserNotFoundException("USER_NOT_AVAILABLE", "User is not available", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Add new User
	 * 
	 * @param dto
	 * @return
	 * @throws UserNotFoundException
	 */
	public UserDto addUser(UserDto dto) throws UserNotFoundException {
		LOGGER.info("addUser().. Start, User Details : " + dto.toString());

		HtUser entity = userRepository.save(UserDto.getEntity(dto));

//		HtUser entity;
//
//		String loginName = dto.getLogin();
//		List<HtUser> entityList = userRepository.findByLogin(loginName);
//
//		if (entityList != null && entityList.size() > 0) {
//			throw new UserNotFoundException("USER_NAME_INUSEE", "User Name is already in use",
//					HttpStatus.NOT_ACCEPTABLE);
//		} else {
//			entity = userRepository.save(UserDto.getEntity(dto));
//		}

		LOGGER.info("addUser().. End");
		return UserDto.getDto(entity);

	}

	/**
	 * Method to update User
	 * 
	 * @param dto User DTO
	 * 
	 * @return Updated User DTO Object
	 * @throws UserNotFoundException
	 */
	public UserDto updateUser(UserDto dto, Integer id) throws UserNotFoundException {

		HtUser entity = null;

		if (userRepository.existsById(id)) {

//			String loginName = dto.getLogin();
//
//			List<HtUser> entityList = userRepository.getUserByLoginAndNotAvailableinID(loginName, id);
//
//			if (entityList != null && entityList.size() > 0) {
//				throw new UserNotFoundException("USER_NAME_INUSEE", "User Name is already in use",
//						HttpStatus.NOT_ACCEPTABLE);
//			} else {
//				Optional<HtUser> optEntityList = userRepository.findById(id);
//				entity = optEntityList.get();
//			}

			entity = userRepository.save(UserDto.copyDtoToEntity(entity, dto));
			return UserDto.getDto(entity);
		} else {
			// entity = userRepository.save(UserDto.getEntity(dto));
			throw new UserNotFoundException("USER_NOT_AVAILABLE", "User is not available", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Method to add User
	 * 
	 * @param dto User DTO
	 * 
	 * @return Updated User DTO Object
	 */
	public UserDto addOrUpdateUser(UserDto dto) {

		HtUser entity = null;

		if (userRepository.existsById(dto.getId())) {
			entity = userRepository.getOne(dto.getId());
		} else {
			entity = userRepository.save(UserDto.getEntity(dto));
		}

		entity = userRepository.save(UserDto.copyDtoToEntity(entity, dto));
		return UserDto.getDto(entity);
	}

	/**
	 * Delete User and its related entries.
	 * 
	 * @param id User ID
	 */
	public void deleteById(Integer id) throws UserNotFoundException {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
		} else {
			throw new UserNotFoundException("USER_NOT_AVAILABLE", "User is not available", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	public UserDto findByLoginIdAndPassword(String login, String password) {

		HtUser user = userRepository.findByLoginAndPassword(login, password);
		if (user == null) {
			throw new ApplicationGenericException("INVALID_CREDENTIALS", "Invalid Credentials ", HttpStatus.NOT_FOUND);
		}
		// Map Entity to DTO
		UserDto userDto = UserDto.getDto(user);
		return userDto;
	}
}