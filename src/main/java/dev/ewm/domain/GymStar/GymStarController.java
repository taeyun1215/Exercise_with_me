package dev.ewm.domain.gymStar;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ewm.global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gymStar")
public class GymStarController {
	private final GymStarService gymStarService;
	
	@PostMapping("/register")
	public ResponseEntity<ReturnObject> register(@Valid @RequestBody GymStarDTO gymStarDto) {
		GymStar gymStar = gymStarService.register(gymStarDto);
		
		ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(gymStar)
                .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(returnObject);
	}
	
	@PutMapping("/modify") 
	public ResponseEntity<ReturnObject> modify (@Valid @RequestBody GymStarDTO gymStarDto) {
		GymStar gymStar = gymStarService.modify(gymStarDto);
		
		ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(gymStar)
                .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(returnObject);
	}
}
