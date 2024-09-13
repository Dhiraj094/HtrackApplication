package com.ktpl.mitadt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.ktpl.mitadt.service.ImageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ImageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

	@Autowired
	private ImageService imageService;

}