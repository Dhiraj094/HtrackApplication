package com.ktpl.mitadt.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktpl.mitadt.repository.ImageRepository;

@Service
@Transactional

public class ImageService {

	@Autowired
	private ImageRepository imageRepository;

}
