package dev.ewm.domain.gymReply;

import java.util.List;

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

import dev.ewm.global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/gymReply")
public class GymReplyController {
	private final GymReplyService gymReplyService;
	
	@PostMapping("/")
	public ResponseEntity<ReturnObject> register(@RequestBody GymReplyDto gymReplyDto) {
		gymReplyDto = gymReplyService.register(gymReplyDto);
		
		ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(gymReplyDto)
                .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(returnObject);
	}
	
	@GetMapping("/{gymId}")
	public ResponseEntity<ReturnObject> list(@PathVariable("gymId") Long gymId) {
		List<GymReplyDto> list = gymReplyService.getList(gymId);
		
		ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(list)
                .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(returnObject);
	}
	
	@PutMapping("/") 
	public ResponseEntity<ReturnObject> modify(@RequestBody GymReplyDto gymReplyDto) {
		gymReplyDto = gymReplyService.modify(gymReplyDto);
		
		ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(gymReplyDto)
                .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(returnObject);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ReturnObject> delete(@PathVariable("id") Long id) {
		gymReplyService.delete(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
