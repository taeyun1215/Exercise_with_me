package dev.ewm.domain.gym;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<ReturnObject> register(@Validated @RequestBody GymDTO gymDto) {
		gymService.register(gymDto);
		
		ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(gymService.register(gymDto))
                .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(returnObject);
	}
	
	@GetMapping("/list") 
	public ResponseEntity<ReturnObject> list() {
		gymService.getList();
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@GetMapping("/detail/{userId}")
	public ResponseEntity<ReturnObject> detail(@PathVariable("userId") Long userId) {
		gymService.getDetail(userId);
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@PutMapping("/modify")
	public ResponseEntity<ReturnObject> modify(GymDTO gymDto) {
		gymService.modify(gymDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ReturnObject> delete(@PathVariable("id") Long id) {
		gymService.delete(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	
}
