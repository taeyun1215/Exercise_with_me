package dev.ewm.domain.gym;

import java.util.List;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/gym")
public class GymController {
	private final GymService gymService;
	
	@PostMapping("/register")
	public ResponseEntity<ReturnObject> register(@RequestBody GymDTO gymDto) {
		Gym gym = gymService.register(gymDto);
		
		ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(gym)
                .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(returnObject);
	}
	
	@GetMapping("/list") 
	public ResponseEntity<ReturnObject> list() {
		List<Gym> list = gymService.getList();
		
		ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(list)
                .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(returnObject);
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<ReturnObject> detail(@PathVariable("id") Long id) {
		Gym gym = gymService.getDetail(id);
		
		ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(gym)
                .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(returnObject);
	}
	
	@PutMapping("/modify")
	public ResponseEntity<ReturnObject> modify(@RequestBody GymDTO gymDto) {
		Gym gym = gymService.modify(gymDto);
		
		ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(gym)
                .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(returnObject);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ReturnObject> delete(@PathVariable("id") Long id) {
		gymService.delete(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	
}
