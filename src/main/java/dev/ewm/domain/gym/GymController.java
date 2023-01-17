package dev.ewm.domain.gym;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ewm.global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gym")
public class GymController {
	private final GymService gymService;
	
	@PostMapping("/register")
	public ResponseEntity<ReturnObject> register(GymDTO gymDto) {
		gymService.register(gymDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
