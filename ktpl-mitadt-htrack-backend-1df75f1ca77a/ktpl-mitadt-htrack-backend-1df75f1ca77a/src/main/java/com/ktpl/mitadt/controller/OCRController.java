package com.ktpl.mitadt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktpl.mitadt.dto.HealthHistoryDto;
import com.ktpl.mitadt.exceptions.UserNotFoundException;
import com.ktpl.mitadt.service.OCRService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class OCRController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OCRController.class);

	@Autowired
	private OCRService ocrService;

	@GetMapping("/ocr/{id}")
	public HealthHistoryDto ocr(@PathVariable Integer id) throws UserNotFoundException {
		LOGGER.info(" ocr() Start");
		return ocrService.ocrImage(id);

	}
}