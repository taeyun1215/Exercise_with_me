package dev.ewm.domain.gym;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ewm.global.error.ErrorCode;
import dev.ewm.global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/gym")
public class GymController {
	private final GymService gymService;
	
	@PostMapping("")
	public ResponseEntity<ReturnObject> register(@RequestBody GymDTO gymDto) {
		GymDTO gym = gymService.register(gymDto);
		
		ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(gym)
                .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(returnObject);
	}
	
	@GetMapping("/list") 
	public ResponseEntity<ReturnObject> list() {
		List<GymDTO> list = gymService.getList();
		
		ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(list)
                .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(returnObject);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReturnObject> detail(@Valid @PathVariable("id") Long id) {
		GymDTO gym = null;
		boolean bool = true;
		ErrorCode errorCode = null;
		
		try {
			gym = gymService.getDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
			bool = false;
			errorCode = ErrorCode.NOT_EXISTING_DATA;
		}
		
		ReturnObject returnObject = ReturnObject.builder()
                .success(bool)
                .data(gym)
                .errorCode(errorCode)
                .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(returnObject);
	}
	
	@PutMapping("")
	public ResponseEntity<ReturnObject> modify(@Valid @RequestBody GymModifyRequest gymDto) {
		GymDTO gym = null;
		boolean bool = true;
		ErrorCode errorCode = null;
		
//		try {
//			gym = gymService.modify(gymDto);
//		} catch (Exception e) {
//			e.printStackTrace();
//			bool = false;
//			errorCode = ErrorCode.NOT_EXISTING_DATA;
//		}
		
		ReturnObject returnObject = ReturnObject.builder()
                .success(bool)
                .data(gym)
                .errorCode(errorCode)
                .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(returnObject);
	}
//	@PutMapping("")
//	public ResponseEntity<ReturnObject> modify(@Valid @RequestBody GymDTO gymDto) {
//		GymDTO gym = null;
//		boolean bool = true;
//		ErrorCode errorCode = null;
//		
//		try {
//			gym = gymService.modify(gymDto);
//		} catch (Exception e) {
//			e.printStackTrace();
//			bool = false;
//			errorCode = ErrorCode.NOT_EXISTING_DATA;
//		}
//		
//		ReturnObject returnObject = ReturnObject.builder()
//				.success(bool)
//				.data(gym)
//				.errorCode(errorCode)
//				.build();
//		
//		return ResponseEntity.status(HttpStatus.OK).body(returnObject);
//	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ReturnObject> delete(@PathVariable("id") Long id) {
		boolean bool = gymService.delete(id);
		
		ReturnObject returnObject = ReturnObject.builder()
                .success(bool)
                .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(returnObject);
	}
	
	
}
