package dev.ewm.domain.gym;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/list") 
	public ResponseEntity<ReturnObject> list() {
		gymService.getList();
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@GetMapping("/detail/{userName}")
	public ResponseEntity<ReturnObject> detail(@PathVariable("userName") String userName) {
		gymService.getDetail(userName);
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
