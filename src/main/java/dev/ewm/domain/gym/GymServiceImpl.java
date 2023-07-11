package dev.ewm.domain.gym;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GymServiceImpl implements GymService {
	private final GymRepo gymRepo;
	
	@Override
	public GymDTO register(GymDTO gymDto) {
		return gymRepo.save(gymDto.toEntity()).toDto();
	}

	@Override
	public List<GymDTO> getList() {
		List<GymDTO> list = new ArrayList<>();
		
		gymRepo.findAll().forEach(l -> list.add(l.toDto()));

		return list;
	}

	@Override
	public GymDTO getDetail(Long id) throws Exception {
		Optional<Gym> optional = gymRepo.findById(id);
		optional.orElseThrow(() -> new Exception("data is null"));
		
		return optional.get().toDto();
	}

	@Override
	@Transactional
	public GymDTO modify(GymDTO gymDto) throws Exception {
		GymDTO gym = gymRepo.save(gymDto.toEntity()).toDto();
		return gym;
	}

	@Override
	@Transactional
	public void modifyStarScore(Long gymId, Double avgStarScore) throws Exception {
		GymDTO gymDto = getDetail(gymId);
		
		gymDto.setCountingStar(avgStarScore);
		modify(gymDto);
	}

	@Override
	public boolean delete(Long id) {
		boolean bool = true;
		
		try {
			gymRepo.deleteById(id);
			log.info("{} 삭제 완료", id);
		} catch(Exception e) {
			e.printStackTrace();
			bool = false;
		}
		
		return bool;
	}

}
