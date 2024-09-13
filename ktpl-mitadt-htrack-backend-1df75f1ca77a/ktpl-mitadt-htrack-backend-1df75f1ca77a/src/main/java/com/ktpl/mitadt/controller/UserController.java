package com.ktpl.mitadt.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ktpl.mitadt.dto.UserDto;
import com.ktpl.mitadt.exceptions.ApplicationGenericException;
import com.ktpl.mitadt.exceptions.UserNotFoundException;
import com.ktpl.mitadt.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * Add User
	 * 
	 * @param wrapperDto Wrapper DTO for User
	 * @return
	 */
	@PutMapping("/user")
	public UserDto addUser(@RequestBody UserDto updateUserDto) {
		LOGGER.info("addUser() Start:");
		UserDto dto;
		try {
			dto = userService.addUser(updateUserDto);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			throw new ApplicationGenericException(e.getErrorCode(), e.getMessage(), e.getHttpStatus());
		}
		LOGGER.info("addUser() End:");
		return dto;
	}

	/**
	 * Get User by ID
	 * 
	 * @param id User ID
	 * @return
	 */
	@GetMapping("/user/{id}")
	public UserDto getUser(@PathVariable Integer id) {
		LOGGER.info("getUser().. Start");
		UserDto dto;
		try {
			dto = userService.getUser(id);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			throw new ApplicationGenericException(e.getErrorCode(), e.getMessage(), e.getHttpStatus());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationGenericException("UNKNOWN_ERROR", "Something went wrong",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("getUser() End:");
		return dto;

	}

//	/**
//	 * Update User
//	 * 
//	 * @param wrapperDto Wrapper DTO for User
//	 * @return
//	 */
//	// @todo Need to implment this method.
//
	@PutMapping("/user/{id}")
	public UserDto updateUser(@RequestBody UserDto updateUserDto, @PathVariable Integer id) {
		LOGGER.info("updateUser() Start:");
		UserDto dto;
		try {
			dto = userService.updateUser(updateUserDto, id);
		} catch (ApplicationGenericException ae) {
			throw ae;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationGenericException("UNKNOWN_ERROR", "Something went wrong",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("updateUser() End:");
		return dto;
	}

	@DeleteMapping("/user/{id}")
	@Transactional
	public HttpStatus deleteUser(@PathVariable Integer id) {
		LOGGER.info("deleteUser().. Start");
		if (id == null) {
			return HttpStatus.BAD_REQUEST;
		} else {
			try {
				userService.deleteById(id);
			} catch (UserNotFoundException e) {
				return e.getHttpStatus();
			}
		}
		LOGGER.info("deleteUser() End:");
		return HttpStatus.ACCEPTED;
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public UserDto login(@RequestBody UserDto userDto, HttpServletRequest servletRequest) {

		LOGGER.info("Start Authentication.");
		try {
			userDto = userService.findByLoginIdAndPassword(userDto.getLogin(), userDto.getPassword());

		} catch (ApplicationGenericException ae) {
			throw ae;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationGenericException("INTERNAL_SERVER_ERROR", "Internal Server Error",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		LOGGER.info("Valid User. " + userDto.getId());
		return userDto;
	}

}