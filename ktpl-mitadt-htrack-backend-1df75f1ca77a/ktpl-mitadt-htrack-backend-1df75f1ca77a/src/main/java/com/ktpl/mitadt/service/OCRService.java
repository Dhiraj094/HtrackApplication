package com.ktpl.mitadt.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktpl.mitadt.dto.HealthHistoryDto;
import com.ktpl.mitadt.dto.UserDto;
import com.ktpl.mitadt.exceptions.UserNotFoundException;

import net.sourceforge.tess4j.Tesseract;

@Service
@Transactional

public class OCRService {

	@Autowired
	HealthHistoryService healthHistoryService;

	public OCRService() throws IOException {

	}

	public HealthHistoryDto ocrImage(Integer id) throws UserNotFoundException {
		Tesseract ts = new Tesseract();
		ts.setDatapath("");
		ts.setLanguage("eng");
//		try {
		// String text =
		// ts.doOCR(getImage("G:\\development\\codebase\\99-mitadt\\htrack-backend\\images\\Test.jpeg"));

		// User DTO
		UserDto userDto = new UserDto();
		userDto.setId(id);

		// Health History DTP
		HealthHistoryDto healthHistoryDto = new HealthHistoryDto();

		int oxygen = ThreadLocalRandom.current().nextInt(95, 100);
		healthHistoryDto.setOxygen(oxygen);

		int pulse = ThreadLocalRandom.current().nextInt(65, 130);
		healthHistoryDto.setPulse(pulse);

		healthHistoryDto.setUserDto(userDto);

		healthHistoryService.addOrUpdateHealthHistory(healthHistoryDto);

		return healthHistoryDto;

		// System.out.println(text);
//		} catch (TesseractException | IOException e) {
//
//			e.printStackTrace();
//		}
	}

	private BufferedImage getImage(String impPath) throws IOException {
		// read Image
		Mat mat = Imgcodecs.imread(impPath);

		// change image to gray scale
		Mat gray = new Mat();
		Imgproc.cvtColor(mat, gray, Imgproc.COLOR_BGR2GRAY);

		// resize image

		Mat resized = new Mat();
		Size size = new Size(mat.width() * 1.9f, mat.height() * 1.9f);
		Imgproc.resize(gray, resized, size);

		// Convert to buffered Image
		MatOfByte mOf = new MatOfByte();
		byte imageByte[];
		Imgcodecs.imencode(".jpeg", resized, mOf);
		imageByte = mOf.toArray();

		return ImageIO.read(new ByteArrayInputStream(imageByte));

	}

	public static void main(String[] args) throws IOException, UserNotFoundException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		(new OCRService()).ocrImage(1);

	}

}
