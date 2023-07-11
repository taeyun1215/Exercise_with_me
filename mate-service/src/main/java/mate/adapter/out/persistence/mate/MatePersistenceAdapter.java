package mate.adapter.out.persistence.mate;

import lombok.RequiredArgsConstructor;
import mate.application.port.out.mate.DeleteMatePort;
import mate.application.port.out.mate.ExistMatePort;
import mate.application.port.out.mate.LoadMatePort;
import mate.application.port.out.mate.SaveMatePort;
import mate.domain.Mate;
import mate.domain.MatePost;
import mate.global.annotation.PersistenceAdapter;

@RequiredArgsConstructor
@PersistenceAdapter
public class MatePersistenceAdapter implements
        SaveMatePort, DeleteMatePort, LoadMatePort,
        ExistMatePort {

    private final MateJpaRepo mateJpaRepo;
    private final MatePersistenceMapper matePersistenceMapper;

    @Override
    public void saveMate(Mate mate, MatePost matePost, Long userId) {
        mateJpaRepo.save(matePersistenceMapper.mapToJpaEntity(mate, matePost, userId));
    }

    @Override
    public void deleteMate(Long mateId) {
        mateJpaRepo.deleteById(mateId);
    }

    @Override
    public Mate loadMate(Long mateId) {
        return matePersistenceMapper.mapToDomainEntity(mateJpaRepo.findById(mateId).get());
    }

    @Override
    public Mate existMate(MatePost matePost, Long userId) {
        return matePersistenceMapper.mapToDomainEntity(mateJpaRepo.findByMatePostIdAndUserId(matePost.getMatePostId(), userId).get());
    }
}
