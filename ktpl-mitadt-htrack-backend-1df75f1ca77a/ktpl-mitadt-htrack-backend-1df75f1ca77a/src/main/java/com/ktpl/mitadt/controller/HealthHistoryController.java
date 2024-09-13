package com.ktpl.mitadt.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.ktpl.mitadt.dto.HealthHistoryDto;
import com.ktpl.mitadt.exceptions.ApplicationGenericException;
import com.ktpl.mitadt.exceptions.HealthHistoryNotFoundException;
import com.ktpl.mitadt.exceptions.UserNotFoundException;
import com.ktpl.mitadt.service.HealthHistoryService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class HealthHistoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HealthHistoryController.class);

	@Autowired
	private HealthHistoryService healthHistoryService;

	
	/**
	 * Add or update Health History
	 * 
	 * @param wrapperDto Wrapper DTO for User
	 * @param id         User Id in case of update
	 * @return
	 */
	@PutMapping("/healthhistory")
	public HealthHistoryDto addHealthHistory(@RequestBody HealthHistoryDto updateHealthHistoryDto){

		LOGGER.info("addHealthHistory().. Start");

		HealthHistoryDto dto;
		try {
			dto = healthHistoryService.addOrUpdateHealthHistory(updateHealthHistoryDto);
		} catch (ApplicationGenericException ae) {
			throw ae;
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			throw new ApplicationGenericException(e.getErrorCode(),e.getMessage(),
					e.getHttpStatus());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationGenericException("UNKNOWN_ERROR", "Something went wrong",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("addHealthHistory() End:");
		return dto;
	}
	
	/**
	 * Get Health History by ID
	 * 
	 * @param id Health History ID
	 * @return
	 */
	@GetMapping("/healthhistory/{id}")
	public HealthHistoryDto getHealthHistory(@PathVariable Integer id) {
		LOGGER.info("getHealthHistory().. Start");
		HealthHistoryDto dto;
		try {
			dto = healthHistoryService.getHealthHistory(id);
		} catch (ApplicationGenericException ae) {
			throw ae;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationGenericException("UNKNOWN_ERROR", "Something went wrong",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("getHealthHistory() End:");
		return dto;
	}

	/**
	 * Add or update Health History
	 * 
	 * @param wrapperDto Wrapper DTO for User
	 * @param id         User Id in case of update
	 * @return
	 */
	@PutMapping("/healthhistory/{id}")
	public HealthHistoryDto updateHealthHistory(@RequestBody HealthHistoryDto requestedDto,
			@PathVariable Integer id) {

		LOGGER.info("updateHealthHistory().. Start");

		HealthHistoryDto dto;
		try {
			dto = healthHistoryService.updateHealthHistory(requestedDto, id);
		} catch (HealthHistoryNotFoundException e) {
			e.printStackTrace();
			throw new ApplicationGenericException(e.getErrorCode(),e.getMessage(),
					e.getHttpStatus());		
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationGenericException("UNKNOWN_ERROR", "Something went wrong",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("updateHealthHistory() End:");
		return dto;
	}


	
	@DeleteMapping("/healthhistory/{id}")
	@Transactional
	public HttpStatus deleteHealthHistory(@PathVariable Integer id) {
		LOGGER.info("deleteHealthHistory().. Start");
		if (id == null) {
			return HttpStatus.BAD_REQUEST;
		} else {
			try {
				healthHistoryService.deleteById(id);
			} catch (HealthHistoryNotFoundException e) {
				return e.getHttpStatus();
			}
		}
		LOGGER.info("deleteHealthHistory() End:");
		return HttpStatus.ACCEPTED;
	}
	
	/**
	 * Get Health History by ID
	 * 
	 * @param id Health History ID
	 * @return
	 */
	@GetMapping("/healthhistory/list/{userId}")
	public List<HealthHistoryDto> getHealthHistoryForUser(@PathVariable Integer userId) {
		LOGGER.info("getHealthHistoryForUser().. Start");
		List<HealthHistoryDto> dtoList;
		try {
			dtoList = healthHistoryService.getHealthHistoryListForUser(userId);
		} catch (ApplicationGenericException ae) {
			throw ae;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationGenericException("UNKNOWN_ERROR", "Something went wrong",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("getHealthHistoryForUser() End:");
		return dtoList;
	}

}