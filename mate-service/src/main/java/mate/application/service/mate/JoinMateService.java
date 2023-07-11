package mate.application.service.mate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mate.application.port.in.usecase.JoinMateUseCase;
import mate.application.port.out.mate.DeleteMatePort;
import mate.application.port.out.mate.ExistMatePort;
import mate.application.port.out.mate.SaveMatePort;
import mate.application.port.out.matePost.LoadMatePostPort;
import mate.domain.Mate;
import mate.domain.MatePost;
import mate.domain.constant.Type;
import mate.global.annotation.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class JoinMateService implements JoinMateUseCase {

    private final SaveMatePort saveMatePort;
    private final ExistMatePort existMatePort;
    private final DeleteMatePort deleteMatePort;
    private final LoadMatePostPort loadMatePostPort;

    @Override
    public void joinMate(Long matePostId, Long userId) {
        MatePost matePost = loadMatePostPort.loadMatePost(matePostId);
        Optional<Mate> mate = Optional.ofNullable(existMatePort.existMate(matePost, userId));

        mate.ifPresentOrElse(
                existMate -> {
                    deleteMatePort.deleteMate(existMate.getMateId());
                    log.info("운동 그룹에서 삭제됐습니다.");
                },
                () -> {
                    Mate joinMate = Mate.builder()
                            .type(Type.PARTICIPANT)
                            .userId(userId)
                            .matePostId(matePostId)
                            .build();

                    saveMatePort.saveMate(joinMate, matePost, userId);
                    log.info("운동 그룹에 등록됐습니다.");
                }
        );
    }
}